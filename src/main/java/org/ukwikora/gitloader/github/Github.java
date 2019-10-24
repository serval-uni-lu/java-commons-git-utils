package org.ukwikora.gitloader.github;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.ukwikora.gitloader.GitEngine;
import org.ukwikora.gitloader.git.LocalRepo;

import java.io.IOException;
import java.util.Set;

public class Github extends GitEngine {
    @Override
    public Set<LocalRepo> cloneProjectsFromNames(Set<String> projectNames) throws GitAPIException, IOException {
        return null;
    }

    @Override
    public Set<LocalRepo> cloneProjectsFromGroup(String group) throws IOException, GitAPIException {
        return null;
    }

    @Override
    public Set<LocalRepo> cloneProjectsFromUser(String user) throws IOException, GitAPIException {
        return null;
    }
}
