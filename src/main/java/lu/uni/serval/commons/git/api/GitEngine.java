package lu.uni.serval.commons.git.api;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
