package lu.uni.serval.commons.git.api.github;

import lu.uni.serval.commons.git.utils.LocalRepository;
import lu.uni.serval.commons.git.api.GitEngine;
import lu.uni.serval.commons.git.exception.InvalidGitRepositoryException;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class Github extends GitEngine {
    @Override
    public Set<LocalRepository> cloneProjectsFromNames(Set<String> projectNames) throws IOException, InvalidGitRepositoryException {
        return Collections.emptySet();
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromGroup(String group) throws IOException, InvalidGitRepositoryException {
        return Collections.emptySet();
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromUser(String user) throws IOException, InvalidGitRepositoryException {
        return Collections.emptySet();
    }
}
