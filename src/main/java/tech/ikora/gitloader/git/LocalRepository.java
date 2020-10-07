package tech.ikora.gitloader.git;

import org.eclipse.jgit.api.Git;

import java.io.File;

public class LocalRepository {
    private final Git git;
    private final File location;
    private final String remoteUrl;
    private final GitCommit gitCommit;

    public LocalRepository(Git git, File location, String remoteUrl, GitCommit gitCommit) {
        this.git = git;
        this.location = location;
        this.remoteUrl = remoteUrl;
        this.gitCommit = gitCommit;
    }

    public File getLocation() {
        return location;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public GitCommit getGitCommit() {
        return gitCommit;
    }

    public Git getGit() {
        return git;
    }
}
