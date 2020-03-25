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
import org.eclipse.jgit.treewalk.FileTreeIterator;
import org.eclipse.jgit.util.io.NullOutputStream;
import tech.ikora.gitloader.exception.CommitNotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class GitUtils {
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
            final List<DiffEntry> diffEntries = getDiff(git, previousCommit, commit);

            final File location = git.getRepository().getDirectory().getParentFile();
            final String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
            final GitCommit gitCommit = new GitCommit(commit.getName(), commit.getAuthorIdent().getWhen(), diffEntries);

            localRepository = new LocalRepository(git, location, remote, gitCommit);
        }

        return localRepository;
    }

    public static LocalRepository loadCurrentRepository(String url, String token, File localFolder, String branch)
            throws GitAPIException, IOException {
        if(localFolder.exists()){
            FileUtils.deleteDirectory(localFolder);
        }

        UsernamePasswordCredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", token);

        LocalRepository localRepository;

        Git git = Git.cloneRepository()
                .setURI(url)
                .setCredentialsProvider(credentials)
                .setBranch(branch)
                .setDirectory(localFolder)
                .call();

        localRepository = GitUtils.createLocalRepository(git);

        return localRepository;
    }

    public static List<GitCommit> getCommits(Git git, Date start, Date end, String branch) {
        List<GitCommit> commits = new ArrayList<>();

        try {
            Iterable<RevCommit> revCommits;
            ObjectId masterId = git.getRepository().resolve("remotes/origin/master");

            if(branch.equals("master") || masterId == null){
                revCommits = git.log().call();
            }
            else{
                ObjectId branchId = git.getRepository().resolve("remotes/origin/" + branch);
                revCommits = git.log().addRange(masterId, branchId).call();
            }

            RevCommit previousCommit = null;
            for (RevCommit commit : revCommits) {
                Instant instant = Instant.ofEpochSecond(commit.getCommitTime());
                Date commitDate = Date.from(instant);

                if(isInInterval(commitDate, start, end)){
                    List<DiffEntry> diffEntries = getDiff(git, previousCommit, commit);
                    commits.add(new GitCommit(commit.getName(), commitDate, diffEntries));
                }

                previousCommit = commit;
            }

            commits.sort(Comparator.comparing(GitCommit::getDate));

            return commits;
        }
        catch (GitAPIException | IOException e) {
            return Collections.emptyList();
        }
    }

    public static Date getCommitDate(Git git, ObjectId commitId) throws GitAPIException, IOException {
        RevWalk revWalk = new RevWalk(git.getRepository());
        RevCommit revCommit = revWalk.parseCommit(commitId);

        return revCommit.getAuthorIdent().getWhen();
    }

    private static boolean isInInterval(Date date, Date startDate, Date endDate){
        if(startDate != null && startDate.after(date)){
            return false;
        }

        if(endDate != null && endDate.before(date)){
            return false;
        }

        return true;
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

    public static Ref checkout(Git git, Date date, String branch) throws CommitNotFoundException, GitAPIException {
        final Optional<GitCommit> mostRecentCommit = getMostRecentCommit(git, date, branch);

        if(!mostRecentCommit.isPresent()){
            throw new CommitNotFoundException(String.format("No commit for branch '%s' found before %s",
                    branch, date.toString()));
        }

        return checkout(git, mostRecentCommit.get().getId());
    }

    public static Ref checkout(Git git, String commitId) throws GitAPIException {
        boolean createBranch = git.branchList().call()
                .stream()
                .map(Ref::getName)
                .collect(Collectors.toSet())
                .contains("ref/heads/" + commitId);

        return git.checkout()
                .setCreateBranch(createBranch)
                .setName(commitId)
                .setStartPoint(commitId)
                .call();
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

    private static List<DiffEntry> getDiff(Git git, RevCommit commit1, RevCommit commit2) throws IOException, GitAPIException {
        AbstractTreeIterator oldTreeIterator = getTreeIterator(git, commit1);
        AbstractTreeIterator newTreeIterator = getTreeIterator(git, commit2);

        OutputStream outputStream = NullOutputStream.INSTANCE;
        try( DiffFormatter formatter = new DiffFormatter( outputStream ) ) {
            formatter.setRepository( git.getRepository() );
             return formatter.scan( oldTreeIterator, newTreeIterator );
        }
    }

    private static AbstractTreeIterator getTreeIterator(Git git, RevCommit commit) throws IOException {
        if(commit == null){
            return new EmptyTreeIterator();
        }

        try( ObjectReader reader = git.getRepository().newObjectReader() ) {
            return new CanonicalTreeParser(null, reader, commit.getTree().getId());
        }
    }
}
