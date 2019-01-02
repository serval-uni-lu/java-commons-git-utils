package org.gitlabloader.api;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Gitlab {
    private String api;
    private String url;
    private String token;
    private UsernamePasswordCredentialsProvider credentials;

    public Gitlab(){
        api = "/org/gitlabloader/api/v4";
    }

    public Gitlab setUrl(String url) {
        this.url = url;
        return this;
    }

    public Gitlab setToken(String token) {
        this.token = token;
        return this;
    }

    public Gitlab setCredentials(String username, String password) {
        credentials = new UsernamePasswordCredentialsProvider(username, password);
        return this;
    }

    public List<Group> getGroups() {
        String request = url + api + "/groups";
        return RestConnection.getObjectList(request, token, Group.class);
    }

    public Group findGroupByName(String name) {
        List<Group> groups = getGroups();

        for(Group group: groups) {
            if(group.getName().equals(name)){
                return group;
            }
        }

        return null;
    }

    public Group findGroupById(int id){
        String request = url + api + "/groups/" + id;
        return RestConnection.getObject(request, token, Group.class);
    }

    public List<Project> findProjectsByGroupName(String groupName) {
        Group group = findGroupByName(groupName);
        return findProjectsByGroupId(group.getId());
    }

    //TODO: if number of projects exceeds 100, I will have a problem. Build a function that will check for all pages
    public List<Project> findProjectsByGroupId(int groupId){
        String request = url + api + "/groups/" + groupId + "/projects?per_page=100";
        return RestConnection.getObjectList(request, token, Project.class);
    }

    public void cloneProjects(List<Project> projects, String location, String branch) {
        File parent = new File(location);

        if(!parent.isDirectory()){
            return;
        }

        for(Project project: projects) {
            try {
                File destination = new File(parent, project.getName());
                cloneRepository(project.getHttpUrlToRepo(), destination, branch);
            } catch (GitAPIException e) {
                e.printStackTrace();
            }
        }

    }

    private void cloneRepository(String url, File localFolder, String branch) throws GitAPIException {
        if(localFolder.exists()){
            try {
                FileUtils.deleteDirectory(localFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Git.cloneRepository()
                .setURI(url)
                .setCredentialsProvider(credentials)
                .setBranch(branch)
                .setDirectory(localFolder)
                .call();
    }
}
