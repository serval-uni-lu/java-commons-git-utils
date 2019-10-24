package org.ukwikora.gitloader.git;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class GitUtils {
    public static LocalRepo createLocalRepo(Repository repository) throws IOException {
        LocalRepo localRepo;

        try (RevWalk revWalk = new RevWalk(repository)) {
            final ObjectId head = repository.resolve(Constants.HEAD);
            final RevCommit commit = revWalk.parseCommit(head);

            final String commitId = commit.getName();
            final Date date = commit.getAuthorIdent().getWhen();
            final File location = repository.getDirectory();
            final String remote = repository.getConfig().getString("remote", "origin", "url");

            localRepo = new LocalRepo(location, commitId, date, remote);
        }

        return localRepo;
    }

    public static LocalRepo loadCurrentRepository(String url, String token, File localFolder, String branch)
            throws GitAPIException, IOException {
        if(localFolder.exists()){
            FileUtils.deleteDirectory(localFolder);
        }

        UsernamePasswordCredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", token);

        LocalRepo localRepo;

        try(Git git = Git.cloneRepository()
                .setURI(url)
                .setCredentialsProvider(credentials)
                .setBranch(branch)
                .setDirectory(localFolder)
                .call()) {

            localRepo = GitUtils.createLocalRepo(git.getRepository());
            git.getRepository().close();
        }

        return localRepo;
    }
}
