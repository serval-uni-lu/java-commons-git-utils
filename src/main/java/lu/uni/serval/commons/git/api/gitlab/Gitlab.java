package lu.uni.serval.commons.git.api.gitlab;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License")
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
import lu.uni.serval.commons.git.api.GitEngine;
import lu.uni.serval.commons.git.api.call.RestConnection;
import lu.uni.serval.commons.git.exception.InvalidGitRepositoryException;
import lu.uni.serval.commons.git.utils.GitUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Gitlab extends GitEngine {
    private final String api;

    public Gitlab(){
        api = "/api/v4";
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromNames(Set<String> projectNames) throws IOException, InvalidGitRepositoryException {
        final Set<Project> projects = findProjectsByNames(projectNames);
        return cloneAllProjects(projects);
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromGroup(String group) throws IOException, InvalidGitRepositoryException {
        final Set<Project> projects = findProjectsByGroupName(group);
        return cloneAllProjects(projects);
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromUser(String user) throws IOException, InvalidGitRepositoryException {
        final Set<Project> projects = findProjectsByUserName(user);
        return cloneAllProjects(projects);
    }

    private Set<Project> findProjectsByGroupName(String groupName) throws IOException {
        Group group = findGroupByName(groupName);

        if(group == null){
            return Collections.emptySet();
        }

        return findProjectsByGroupId(group.getId());
    }

    private Set<Project> findProjectsByUserName(String userName) throws IOException {
        User user = findUserByName(userName);

        if(user == null){
            return Collections.emptySet();
        }

        return findProjectsByUserId(user.getId());
    }

    //TODO: if number of projects exceeds 100, I will have a problem. Build a function that will check for all pages
    private Set<Project> findProjectsByGroupId(int groupId) throws IOException {
        final String request = getUrl() + api + "/groups/" + groupId + "/projects?per_page=100";
        return RestConnection.getObjectList(request, getToken(), Project.class);
    }

    private Set<Project> findProjectsByUserId(int userId) throws IOException {
        final String request = getUrl() + api + "/users/" + userId + "/projects";
        return RestConnection.getObjectList(request, getToken(), Project.class);
    }
    private Set<LocalRepository> cloneAllProjects(Set<Project> projects) throws IOException, InvalidGitRepositoryException {
        File parent = new File(getCloneFolder());

        if(!parent.isDirectory()){
            return Collections.emptySet();
        }

        Set<LocalRepository> localRepositories = new HashSet<>();

        for(Project project: projects) {
            final File destination = new File(parent, project.getName());
            final String branch = getBranchForProject(project.getName());
            final String url = project.getHttpUrlToRepo();
            final LocalRepository localRepository = GitUtils.loadCurrentRepository(url, getToken(), destination, branch);
            localRepositories.add(localRepository);
        }

        return localRepositories;
    }

    private Set<Project> findProjectsByNames(Set<String> names) throws IOException {
        Set<Project> projects = new HashSet<>(names.size());

        for(String name: names){
            final String request = String.format("%s%s/projects?custom_attributes[path_with_namespace]=%s",
                    getUrl(),
                    api,
                    name);

            final Set<Project> project = RestConnection.getObjectList(request, getToken(), Project.class);

            if(project.size() != 1){
                continue;
            }

            projects.add(project.iterator().next());
        }

        return projects;
    }

    private Group findGroupByName(String name) throws IOException {
        final String request = String.format("%s%s/groups?custom_attributes[name]=%s",
                getUrl(),
                api,
                name);

        final Set<Group> groups = RestConnection.getObjectList(request, getToken(), Group.class);

        if(groups.size() != 1){
            return null;
        }

        return groups.iterator().next();
    }

    private User findUserByName(String userName) throws IOException {
        final String request = String.format("%s%s/users?username=%s",
                getUrl(),
                api,
                userName);

        final Set<User> users = RestConnection.getObjectList(request, getToken(), User.class);

        if(users.size() != 1){
            return null;
        }

        return users.iterator().next();
    }
}
