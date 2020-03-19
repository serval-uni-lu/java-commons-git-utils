package tech.ikora.gitloader.git;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import tech.ikora.gitloader.exception.CommitNotFoundException;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

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

            final File location = git.getRepository().getDirectory().getParentFile();
            final String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
            final GitCommit gitCommit = new GitCommit(commit.getName(), commit.getAuthorIdent().getWhen());

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

            for (RevCommit revCommit : revCommits) {
                Instant instant = Instant.ofEpochSecond(revCommit.getCommitTime());
                Date commitDate = Date.from(instant);

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

    private static Date getCommitDate(Git git, ObjectId commitId) throws GitAPIException, IOException {
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
        return git.checkout()
                .setCreateBranch(true)
                .setName(commitId)
                .setStartPoint(commitId)
                .call();
    }
}
