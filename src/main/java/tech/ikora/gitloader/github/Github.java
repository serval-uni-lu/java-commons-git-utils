package tech.ikora.gitloader.github;

import org.eclipse.jgit.api.errors.GitAPIException;
import tech.ikora.gitloader.GitEngine;
import tech.ikora.gitloader.git.LocalRepository;

import java.io.IOException;
import java.util.Set;

public class Github extends GitEngine {
    @Override
    public Set<LocalRepository> cloneProjectsFromNames(Set<String> projectNames) throws GitAPIException, IOException {
        return null;
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromGroup(String group) throws IOException, GitAPIException {
        return null;
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromUser(String user) throws IOException, GitAPIException {
        return null;
    }
}
