package org.ukwikora.gitloader;

import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class GitEngine {
    private Set<String> urls;
    private String token;
    private String cloneFolder;
    private String defaultBranch;
    private Map<String, String> branches;

    public GitEngine(){
        this.cloneFolder = System.getProperty("java.io.tmpdir");
        this.defaultBranch = "master";
        this.branches = new HashMap<>();
    }

    public GitEngine setUrls(Set<String> urls){
        this.urls = urls;
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

    public Set<String> getUrls() {
        return urls;
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

    abstract public Set<File> cloneProjects(Set<String> projectNames) throws GitAPIException, IOException;
    abstract public Set<File> cloneProjectsFromGroup(String group) throws IOException, GitAPIException;
}
