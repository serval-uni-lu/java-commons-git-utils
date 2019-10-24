package org.ukwikora.gitloader;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.ukwikora.gitloader.git.LocalRepo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class GitEngine {
    private String url;
    private String token;
    private String cloneFolder;
    private String defaultBranch;
    private Map<String, String> branches;

    public GitEngine(){
        this.cloneFolder = System.getProperty("java.io.tmpdir");
        this.defaultBranch = "master";
        this.branches = new HashMap<>();
    }

    public GitEngine setUrl(String url){
        this.url = url;
        return this;
    }

    public GitEngine setToken(String token){
        this.token = token;
        return this;
    }

    public GitEngine setCloneFolder(String cloneFolder){
        this.cloneFolder = cloneFolder;
        return this;
    }

    public GitEngine setDefaultBranch(String branch){
        this.defaultBranch = branch;
        return this;
    }

    public GitEngine setBranchForProject(String project, String branch){
        branches.put(project, branch);
        return this;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public String getCloneFolder() {
        return cloneFolder;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public String getBranchForProject(String project){
        return branches.getOrDefault(project, defaultBranch);
    }

    abstract public Set<LocalRepo> cloneProjectsFromNames(Set<String> names) throws GitAPIException, IOException;
    abstract public Set<LocalRepo> cloneProjectsFromGroup(String group) throws IOException, GitAPIException;
    abstract public Set<LocalRepo> cloneProjectsFromUser(String user) throws IOException, GitAPIException;
}
