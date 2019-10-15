package org.ukwikora.gitloader.gitlab;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.apache.commons.io.FileUtils;
import org.ukwikora.gitloader.GitEngine;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Gitlab extends GitEngine {
    private String api;

    public Gitlab(){
        api = "/api/v4";
    }

    @Override
    public Set<File> cloneProjects(Set<String> projectNames) throws GitAPIException, IOException {
        final Set<Project> projects = findProjectByNames(projectNames);
        return cloneAllProjects(projects);
    }

    @Override
    public Set<File> cloneProjectsFromGroup(String group) throws IOException, GitAPIException {
        final Set<Project> projects = findProjectsByGroupName(group);
        return cloneAllProjects(projects);
    }

    private Set<Project> findProjectByNames(Set<String> projectNames) throws IOException {
        final Set<Project> projects = getProjects();

        projects.removeIf(project -> {
            String name = project.getName();
            return !projectNames.contains(name);
        });

        return projects;
    }

    private Set<Project> findProjectsByGroupName(String groupName) throws IOException {
        Group group = findGroupByName(groupName);

        if(group == null){
            return Collections.emptySet();
        }

        return findProjectsByGroupId(group.getId());
    }

    //TODO: if number of projects exceeds 100, I will have a problem. Build a function that will check for all pages
    private Set<Project> findProjectsByGroupId(int groupId) throws IOException {
        final String request = getSingleUrl() + api + "/groups/" + groupId + "/projects?per_page=100";
        return RestConnection.getObjectList(request, getToken(), Project.class);
    }

    private Set<File> cloneAllProjects(Set<Project> projects) throws GitAPIException, IOException {
        File parent = new File(getCloneFolder());

        if(!parent.isDirectory()){
            return Collections.emptySet();
        }

        Set<File> locations = new HashSet<>();

        for(Project project: projects) {
            File destination = new File(parent, project.getName());
            locations.add(destination);

            String branch = getBranchForProject(project.getName());
            cloneRepository(project.getHttpUrlToRepo(), destination, branch);
        }

        return locations;
    }

    private void cloneRepository(String url, File localFolder, String branch) throws GitAPIException, IOException {
        if(localFolder.exists()){
            FileUtils.deleteDirectory(localFolder);
        }

        UsernamePasswordCredentialsProvider credentials =
                new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", getToken());

        Git.cloneRepository()
                .setURI(url)
                .setCredentialsProvider(credentials)
                .setBranch(branch)
                .setDirectory(localFolder)
                .call();
    }

    private String getSingleUrl() throws IOException {
        if(getUrls().size() != 1){
            throw new IOException(String.format("Expected a single url, got %s instead",
                    getUrls().size()));
        }

        return getUrls().iterator().next();
    }

    private Set<Group> getGroups() throws IOException {
        final String request = getSingleUrl() + api + "/groups";
        return RestConnection.getObjectList(request, getToken(), Group.class);
    }

    private Set<Project> getProjects() throws IOException {
        String request = getSingleUrl() + api + "/projects";
        return RestConnection.getObjectList(request, getToken(), Project.class);
    }

    private Group findGroupByName(String name) throws IOException {
        final Set<Group> groups = getGroups();

        for(Group group: groups) {
            if(group.getName().equals(name)){
                return group;
            }
        }

        return null;
    }
}
