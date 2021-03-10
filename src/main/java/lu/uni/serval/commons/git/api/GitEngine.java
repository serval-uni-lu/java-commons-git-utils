package lu.uni.serval.commons.git.api;

import lu.uni.serval.commons.git.utils.LocalRepository;
import lu.uni.serval.commons.git.exception.InvalidGitRepositoryException;

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

    protected GitEngine(){
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

    public abstract Set<LocalRepository> cloneProjectsFromNames(Set<String> names) throws IOException, InvalidGitRepositoryException;
    public abstract Set<LocalRepository> cloneProjectsFromGroup(String group) throws IOException, InvalidGitRepositoryException;
    public abstract Set<LocalRepository> cloneProjectsFromUser(String user) throws IOException, InvalidGitRepositoryException;
}
