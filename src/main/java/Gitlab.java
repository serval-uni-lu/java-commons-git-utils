import java.util.List;

public class Gitlab {
    private String api;
    private String url;
    private String token;

    Gitlab(){
        api = "api/v4/";
    }

    public Gitlab setUrl(String url) {
        this.url = url;
        return this;
    }

    public Gitlab setToken(String token) {
        this.token = token;
        return this;
    }

    public List<Group> getGroups() {
        String request = url + api + "/groups";
        return RestConnection.getObjectList(request, token);
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

    public List<Project> findProjectByGroupName(String groupName) {
        Group group = findGroupByName(groupName);
        return findProjectByGroupId(group.getId());
    }

    public List<Project> findProjectByGroupId(int groupId){
        String request = url + api + "/groups/" + groupId + "/projects";
        return RestConnection.getObjectList(request, token);
    }
}
