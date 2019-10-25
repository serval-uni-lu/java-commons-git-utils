package org.ukwikora.gitloader.gitlab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.ukwikora.gitloader.GitEngine;
import org.ukwikora.gitloader.call.RestConnection;
import org.ukwikora.gitloader.git.GitUtils;
import org.ukwikora.gitloader.git.LocalRepo;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Gitlab extends GitEngine {
    private static final Logger logger = LogManager.getLogger(Gitlab.class);
    private final String api;

    public Gitlab(){
        api = "/api/v4";
    }

    @Override
    public Set<LocalRepo> cloneProjectsFromNames(Set<String> projectNames) throws GitAPIException, IOException {
        final Set<Project> projects = findProjectsByNames(projectNames);
        return cloneAllProjects(projects);
    }

    @Override
    public Set<LocalRepo> cloneProjectsFromGroup(String group) throws IOException, GitAPIException {
        final Set<Project> projects = findProjectsByGroupName(group);
        return cloneAllProjects(projects);
    }

    @Override
    public Set<LocalRepo> cloneProjectsFromUser(String user) throws IOException, GitAPIException {
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
    private Set<LocalRepo> cloneAllProjects(Set<Project> projects) throws IOException {
        File parent = new File(getCloneFolder());

        if(!parent.isDirectory()){
            return Collections.emptySet();
        }

        Set<LocalRepo> localRepos = new HashSet<>();

        for(Project project: projects) {
            try{
                final File destination = new File(parent, project.getName());
                final String branch = getBranchForProject(project.getName());
                final String url = project.getHttpUrlToRepo();
                final LocalRepo localRepo = GitUtils.loadCurrentRepository(url, getToken(), destination, branch);
                localRepos.add(localRepo);
            } catch (GitAPIException e){
                logger.error(String.format("Failed to clone '%s': %s",
                        project.getName(),
                        e.getMessage()));
            }
        }

        return localRepos;
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
