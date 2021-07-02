package lu.uni.serval.commons.git.utils;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ResetCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.InvalidObjectIdException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.WindowCacheConfig;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.EmptyTreeIterator;
import lu.uni.serval.commons.git.exception.CommitNotFoundException;
import lu.uni.serval.commons.git.exception.InvalidGitRepositoryException;
import org.eclipse.jgit.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.IsoFields;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitUtils {
    private static final String EMPTY_BRANCH = "ikora-empty-branch";
    private static final Pattern pattern = Pattern.compile("(https://)?(github\\.com|bitbucket\\.org|gitlab\\.com)/(.*)/(.*)\\.git", Pattern.CASE_INSENSITIVE);

    private GitUtils() {}

    public static LocalRepository createLocalRepository(Git git) throws GitAPIException, IOException {
        LocalRepository localRepository;

        try (RevWalk revWalk = new RevWalk(git.getRepository())) {
            final ObjectId head = git.getRepository().resolve(Constants.HEAD);

            if(head == null){
                throw new RefNotFoundException(String.format("No branch '%s' for project '%s'",
                        git.getRepository().getBranch(),
                        git.getRepository().getDirectory()));
            }

            final RevCommit revCommit = revWalk.parseCommit(head);
            final RevCommit previousCommit = getPreviousCommit(git, revCommit);
            final Difference difference = getDifference(git, previousCommit, revCommit);

            final File location = git.getRepository().getDirectory().getParentFile();
            final String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
            final GitCommit gitCommit = new GitCommit(revCommit.getName(), Instant.ofEpochSecond(revCommit.getCommitTime()));
            gitCommit.setDifference(difference);

            localRepository = new LocalRepository(git, location, remote, gitCommit);
        }

        return localRepository;
    }

    public static LocalRepository loadCurrentRepository(String url, String token, File localFolder, String branch)
            throws IOException, InvalidGitRepositoryException {
        if(localFolder.exists()){
            FileUtils.delete(localFolder, FileUtils.RECURSIVE);
        }

        LocalRepository localRepository;

        UsernamePasswordCredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", token);

        try{
            Git git = Git.cloneRepository()
                    .setURI(url)
                    .setCredentialsProvider(credentials)
                    .setBranchesToClone(branch == null ? Collections.emptyList() : Collections.singleton(branch))
                    .setBranch(branch)
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

    public static Optional<GitCommit> getCommitById(Git git, String commitId) throws IOException {
        try {
            final RevCommit revCommit = getRevCommit(git, commitId);
            return Optional.of(new GitCommit(revCommit.getName(), Instant.ofEpochSecond(revCommit.getCommitTime())));
        } catch (InvalidObjectIdException | GitAPIException e) {
            return Optional.empty();
        }
    }

    public static List<GitCommit> getCommits(Git git, Instant start, Instant end, String branch) {
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

            for (RevCommit revCommit : revCommits) {
                final Instant commitDate = Instant.ofEpochSecond(revCommit.getCommitTime());

                if(isInInterval(commitDate, start, end)){
                    commits.add(new GitCommit(revCommit.getName(), commitDate));
                }
            }

            commits.sort(Comparator.comparing(GitCommit::getDate));
            return commits;
        }
        catch (GitAPIException | IOException e) {
            return Collections.emptyList();
        }
    }

    public static List<GitCommit> getTags(Git git, Instant start, Instant end) throws GitAPIException, IOException {
        final List<GitCommit> commits = new ArrayList<>();

        for (Ref ref:  git.tagList().call()) {
            try (RevWalk revWalk = new RevWalk(git.getRepository())) {
                final RevCommit revCommit = revWalk.parseCommit(ref.getObjectId());
                final String fullName = ref.getName();
                final String name = fullName.replaceFirst("refs/tags/", "");
                final Instant commitDate = Instant.ofEpochSecond(revCommit.getCommitTime());

                if(isInInterval(commitDate, start, end)){
                    commits.add(new GitCommit(revCommit.getName(), name, commitDate));
                }
            }
        }

        return commits;
    }

    public static Instant getCommitDate(Git git, String commitId) throws IOException, GitAPIException {
        final RevCommit revCommit = getRevCommit(git, commitId);
        return Instant.ofEpochSecond(revCommit.getCommitTime());
    }

    public static Ref checkout(Git git, Instant date, String branch) throws CommitNotFoundException, GitAPIException, IOException {
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
            FileUtils.delete(new File(git.getRepository().getDirectory(), "index"), FileUtils.RECURSIVE);
            git.clean().setForce(true).setCleanDirectories(true).call();

            return ref;
        }

        git.clean().call();
        git.reset().setMode(ResetCommand.ResetType.HARD).call();

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
        if(frequency == Frequency.UNIQUE || frequency == Frequency.RELEASE || frequency == Frequency.LATEST){
            return commits;
        }

        List<GitCommit> filtered = new ArrayList<>(commits.size());
        ListIterator<GitCommit> iterator = commits.listIterator(commits.size());

        Instant previousDate = null;
        while (iterator.hasPrevious()){
            GitCommit commit = iterator.previous();
            Instant commitDate = commit.getDate();

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

    public static Difference getDifference(Git git, String commitId1, String commitId2) throws IOException, GitAPIException {
        final RevCommit commit1 = GitUtils.getRevCommit(git, commitId1);
        final RevCommit commit2 = GitUtils.getRevCommit(git, commitId2);

        return getDifference(git, commit1, commit2);
    }

    public static void close(Git git, boolean deleteLocalFolder) throws IOException {
        final File directory = git.getRepository().getDirectory();

        git.close();

        WindowCacheConfig config = new WindowCacheConfig();
        config.install();

        if(deleteLocalFolder && directory.exists()){
            FileUtils.delete(directory, FileUtils.RECURSIVE);
        }
    }

    static RevCommit getRevCommit(Git git, String commitId) throws IOException, GitAPIException {
        try(RevWalk revWalk = new RevWalk(git.getRepository())){
            for (Ref ref:  git.tagList().call()) {
                final String shortName = ref.getName().replaceFirst("^refs/tags/", "");
                final String mediumName = ref.getName().replaceFirst("^refs/", "");
                final String longName = ref.getName();

                if(commitId.equals(shortName) || commitId.equals(mediumName) || commitId.equals(longName)){
                    return revWalk.parseCommit(ref.getObjectId());
                }
            }

            return revWalk.parseCommit(ObjectId.fromString(commitId));
        }
    }

    static void setDifferences(Git git, List<GitCommit> commits) throws IOException, GitAPIException {
        RevCommit previous = null;
        for(GitCommit current: commits){
            final RevCommit commit = getRevCommit(git, current.getId());
            final Difference difference = getDifference(git, previous, commit);

            current.setDifference(difference);

            previous = getRevCommit(git, current.getId());
        }
    }

    static ObjectId resolveBranch(Git git, String branch) throws GitAPIException {
        for(Ref ref: git.branchList().call()){
            if(ref.getName().endsWith(branch)){
                return ref.getObjectId();
            }
        }

        return null;
    }

    static boolean isInInterval(Instant date, Instant startDate, Instant endDate){
        if(startDate != null && startDate.isAfter(date)){
            return false;
        }

        return endDate == null || endDate.isAfter(date);
    }

    static Optional<GitCommit> getMostRecentCommit(Git git, Instant date, String branch){
        GitCommit mostRecentCommit = null;

        List<GitCommit> commits =  getCommits(git, null, date, branch);

        for (GitCommit commit: commits) {
            if(commit.getDate().isAfter(date)){
                break;
            }

            mostRecentCommit = commit;
        }

        return Optional.ofNullable(mostRecentCommit);
    }

    static RevCommit getPreviousCommit(Git git, RevCommit commit)  throws  IOException {
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

    static Difference getDifference(Git git, RevCommit previous, RevCommit commit) throws IOException, GitAPIException {
        if(previous == null || commit == null){
            return Difference.none();
        }

        final AbstractTreeIterator oldTreeIterator = getTreeIterator(git, previous);
        final AbstractTreeIterator newTreeIterator = getTreeIterator(git, commit);

        final List<DiffEntry> entries = git.diff()
                .setOldTree(oldTreeIterator)
                .setNewTree(newTreeIterator)
                .call();

        return new Difference(git, entries);
    }

    static AbstractTreeIterator getTreeIterator(Git git, RevCommit commit) throws IOException {
        if(commit == null){
            return new EmptyTreeIterator();
        }

        try( ObjectReader reader = git.getRepository().newObjectReader() ) {
            return new CanonicalTreeParser(null, reader, commit.getTree().getId());
        }
    }

    static boolean isSameFrequencyBucket(Instant date1, Instant date2, Frequency frequency) {
        if(date1 == null || date2 == null){
            return false;
        }

        switch (frequency) {
            case DAILY: return isSameDay(date1, date2);
            case WEEKLY: return isSameWeek(date1, date2);
            case MONTHLY: return isSameMonth(date1, date2);
            case YEARLY: return isSameYear(date1, date2);
            default: return false;
        }
    }

    static boolean isSameDay(Instant date1, Instant date2){
        int day1 = date1.atOffset(ZoneOffset.UTC).getDayOfYear();
        int year1 = date1.atOffset(ZoneOffset.UTC).getYear();

        int day2 = date2.atOffset(ZoneOffset.UTC).getDayOfYear();
        int year2 = date2.atOffset(ZoneOffset.UTC).getYear();

        return day1 == day2 && year1 == year2;
    }

    static boolean isSameWeek(Instant date1, Instant date2){
        int week1 = date1.atOffset(ZoneOffset.UTC).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year1 = date1.atOffset(ZoneOffset.UTC).getYear();

        int week2 = date2.atOffset(ZoneOffset.UTC).get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int year2 = date2.atOffset(ZoneOffset.UTC).getYear();

        return week1 == week2 && year1 == year2;
    }

    static boolean isSameMonth(Instant date1, Instant date2){
        int month1 = date1.atOffset(ZoneOffset.UTC).getMonthValue();
        int year1 = date1.atOffset(ZoneOffset.UTC).getYear();

        int month2 = date2.atOffset(ZoneOffset.UTC).getMonthValue();
        int year2 = date2.atOffset(ZoneOffset.UTC).getYear();

        return month1 == month2 && year1 == year2;
    }

    static boolean isSameYear(Instant date1, Instant date2){
        int year1 = date1.atOffset(ZoneOffset.UTC).getYear();
        int year2 = date2.atOffset(ZoneOffset.UTC).getYear();

        return year1 == year2;
    }
}
