package tech.ikora.gitloader.git;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.EmptyTreeIterator;
import tech.ikora.gitloader.exception.CommitNotFoundException;
import tech.ikora.gitloader.exception.InvalidGitRepositoryException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitUtils {
    private static final String EMPTY_BRANCH = "ikora-empty-branch";
    private static final Pattern pattern = Pattern.compile("(https://)?(github\\.com|bitbucket\\.org|gitlab\\.com)/(.*)/(.*)\\.git", Pattern.CASE_INSENSITIVE);

    public static LocalRepository createLocalRepository(Git git) throws GitAPIException, IOException {
        LocalRepository localRepository;

        try (RevWalk revWalk = new RevWalk(git.getRepository())) {
            final ObjectId head = git.getRepository().resolve(Constants.HEAD);

            if(head == null){
                throw new RefNotFoundException(String.format("No branch '%s' for project '%s'",
                        git.getRepository().getBranch(),
                        git.getRepository().getDirectory()));
            }

            final RevCommit commit = revWalk.parseCommit(head);
            final RevCommit previousCommit = getPreviousCommit(git, commit);
            final Difference difference = getDifference(git, previousCommit, commit);

            final File location = git.getRepository().getDirectory().getParentFile();
            final String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
            final GitCommit gitCommit = new GitCommit(commit.getName(), commit.getAuthorIdent().getWhen(), difference);

            localRepository = new LocalRepository(git, location, remote, gitCommit);
        }

        return localRepository;
    }

    public static LocalRepository loadCurrentRepository(String url, String token, File localFolder, String branch)
            throws IOException, InvalidGitRepositoryException {
        if(localFolder.exists()){
            FileUtils.deleteDirectory(localFolder);
        }

        LocalRepository localRepository;

        UsernamePasswordCredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", token);

        String branchId = String.format("refs/heads/%s", branch);

        try{
            Git git = Git.cloneRepository()
                    .setURI(url)
                    .setCredentialsProvider(credentials)
                    .setBranchesToClone(Collections.singleton(branchId))
                    .setBranch(branchId)
                    .setDirectory(localFolder)
                    .call();

            localRepository = GitUtils.createLocalRepository(git);
        }
        catch (GitAPIException e){
            throw new InvalidGitRepositoryException(String.format("Failed to load repository %s: %s",
                    url, e.getMessage()));
        }

        return localRepository;
    }

    public static List<GitCommit> getCommits(Git git, Date start, Date end, String branch) {
        try {
            final List<GitCommit> commits = new ArrayList<>();
            final Iterable<RevCommit> revCommits;
            final ObjectId masterId = resolveBranch(git, "master");
            final ObjectId branchId = resolveBranch(git, branch);

            if(branch.equals("master") || branchId == null || masterId == null){
                revCommits = git.log().call();
            }
            else{
                revCommits = git.log().addRange(masterId, branchId).call();
            }

            RevCommit previous = null;
            for (RevCommit commit : revCommits) {
                Instant instant = Instant.ofEpochSecond(commit.getCommitTime());
                Date commitDate = Date.from(instant);

                if(isInInterval(commitDate, start, end)){
                    Difference difference = getDifference(git, previous, commit);
                    commits.add(new GitCommit(commit.getName(), commitDate, difference));
                }

                previous = commit;
            }

            commits.sort(Comparator.comparing(GitCommit::getDate));

            return commits;
        }
        catch (GitAPIException | IOException e) {
            return Collections.emptyList();
        }
    }

    public static List<GitCommit> getVersions(Git git, Date start, Date end) {
        try {
            final List<GitCommit> commits = new ArrayList<>();

            RevCommit previous = null;
            for (Ref ref:  git.tagList().call()) {
                try (RevWalk revWalk = new RevWalk(git.getRepository())) {
                    final RevCommit commit = revWalk.parseCommit(ref.getObjectId());
                    final String fullName = ref.getName();
                    final String name = fullName.replaceFirst("refs/tags/", "");

                    Instant instant = Instant.ofEpochSecond(commit.getCommitTime());
                    Date commitDate = Date.from(instant);

                    if(isInInterval(commitDate, start, end)){
                        Difference difference = getDifference(git, previous, commit);
                        commits.add(new GitCommit(commit.getName(), name, commitDate, difference));
                    }

                    previous = commit;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return commits;
        } catch (GitAPIException e) {
            return Collections.emptyList();
        }
    }

    public static Date getCommitDate(Git git, ObjectId commitId) throws IOException {
        try(RevWalk revWalk = new RevWalk(git.getRepository())){
            RevCommit revCommit = revWalk.parseCommit(commitId);
            return revCommit.getAuthorIdent().getWhen();
        }
    }

    private static ObjectId resolveBranch(Git git, String branch) throws GitAPIException {
            for(Ref ref: git.branchList().call()){
                if(ref.getName().endsWith(branch)){
                    return ref.getObjectId();
                }
            }

        return null;
    }

    private static boolean isInInterval(Date date, Date startDate, Date endDate){
        if(startDate != null && startDate.after(date)){
            return false;
        }

        return endDate == null || !endDate.before(date);
    }

    public static Optional<GitCommit> getMostRecentCommit(Git git, Date date, String branch){
        GitCommit mostRecentCommit = null;

        List<GitCommit> commits =  getCommits(git, null, date, branch);

        for (GitCommit commit: commits) {
            if(commit.getDate().after(date)){
                break;
            }

            mostRecentCommit = commit;
        }

        return Optional.ofNullable(mostRecentCommit);
    }

    public static Ref checkout(Git git, Date date, String branch) throws CommitNotFoundException, GitAPIException, IOException {
        final Optional<GitCommit> mostRecentCommit = getMostRecentCommit(git, date, branch);

        if(!mostRecentCommit.isPresent()){
            throw new CommitNotFoundException(String.format("No commit for branch '%s' found before %s",
                    branch, date.toString()));
        }

        return checkout(git, mostRecentCommit.get().getId());
    }

    public static Ref checkout(Git git, String commitId) throws GitAPIException, IOException {
        if(commitId.isEmpty()){
            final Ref ref = git.checkout()
                    .setCreateBranch(!isBranchExist(git, EMPTY_BRANCH))
                    .setName(EMPTY_BRANCH)
                    .call();

            git.rm().addFilepattern("*").call();
            FileUtils.forceDelete(new File(git.getRepository().getDirectory(), "index"));
            git.clean().setForce(true).setCleanDirectories(true).call();

            return ref;
        }

        return git.checkout()
            .setCreateBranch(!isBranchExist(git, commitId))
            .setName(commitId)
            .setStartPoint(commitId)
            .call();
    }

    public static boolean isBranchExist(Git git, String branch) throws GitAPIException {
        return git.branchList().call()
                .stream()
                .map(Ref::getName)
                .anyMatch(b -> b.endsWith(branch));
    }

    public static List<GitCommit> filterCommitsByFrequency(List<GitCommit> commits, Frequency frequency) {
        if(frequency == Frequency.UNIQUE || frequency == Frequency.VERSION){
            return commits;
        }

        List<GitCommit> filtered = new ArrayList<>(commits.size());
        ListIterator<GitCommit> iterator = commits.listIterator(commits.size());

        Date previousDate = null;
        while (iterator.hasPrevious()){
            GitCommit commit = iterator.previous();
            Date commitDate = commit.getDate();

            if(!isSameFrequencyBucket(previousDate, commitDate, frequency)){
                filtered.add(commit);
            }

            previousDate = commitDate;
        }

        Collections.reverse(filtered);

        return filtered;
    }

    public static String extractProjectName(String url) throws InvalidGitRepositoryException {
        Matcher matcher = pattern.matcher(url);
        if(!matcher.matches()){
            throw new InvalidGitRepositoryException(url);
        }

        return String.format("%s-%s", matcher.group(3), matcher.group(4));
    }

    private static RevCommit getPreviousCommit(Git git, RevCommit commit)  throws  IOException {
        try (RevWalk walk = new RevWalk(git.getRepository())) {
            walk.markStart(commit);
            int count = 0;
            for (RevCommit rev : walk) {
                if (count == 1) {
                    return rev;
                }
                count++;
            }
            walk.dispose();
        }

        return null;
    }

    private static Difference getDifference(Git git, RevCommit commit1, RevCommit commit2) throws IOException, GitAPIException {
        final AbstractTreeIterator oldTreeIterator = getTreeIterator(git, commit1);
        final AbstractTreeIterator newTreeIterator = getTreeIterator(git, commit2);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        final List<DiffEntry> entries = git.diff()
                .setOldTree(oldTreeIterator)
                .setNewTree(newTreeIterator)
                .setOutputStream(outputStream)
                .call();

        return new Difference(entries, outputStream.toString());
    }

    private static AbstractTreeIterator getTreeIterator(Git git, RevCommit commit) throws IOException {
        if(commit == null){
            return new EmptyTreeIterator();
        }

        try( ObjectReader reader = git.getRepository().newObjectReader() ) {
            return new CanonicalTreeParser(null, reader, commit.getTree().getId());
        }
    }

    private static boolean isSameFrequencyBucket(Date date1, Date date2, Frequency frequency) {
        if(date1 == null || date2 == null){
            return false;
        }

        switch (frequency) {
            case DAILY: return isSameDay(date1, date2);
            case WEEKLY: return isSameWeek(date1, date2);
            case MONTHLY: return isSameMonth(date1, date2);
            case YEARLY: return isSameYear(date1, date2);
        }

        return false;
    }

    private static boolean isSameDay(Date date1, Date date2){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date1);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar.get(Calendar.YEAR);

        calendar.setTime(date2);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);
        int year2 = calendar.get(Calendar.YEAR);

        return day1 == day2 && year1 == year2;
    }

    private static boolean isSameWeek(Date date1, Date date2){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date1);
        int week1 = calendar.get(Calendar.WEEK_OF_YEAR);
        int year1 = calendar.get(Calendar.YEAR);

        calendar.setTime(date2);
        int week2 = calendar.get(Calendar.WEEK_OF_YEAR);
        int year2 = calendar.get(Calendar.YEAR);

        return week1 == week2 && year1 == year2;
    }

    private static boolean isSameMonth(Date date1, Date date2){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date1);
        int month1 = calendar.get(Calendar.MONTH);
        int year1 = calendar.get(Calendar.YEAR);

        calendar.setTime(date2);
        int month2 = calendar.get(Calendar.MONTH);
        int year2 = calendar.get(Calendar.YEAR);

        return month1 == month2 && year1 == year2;
    }

    private static boolean isSameYear(Date date1, Date date2){
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);

        return year1 == year2;
    }
}
