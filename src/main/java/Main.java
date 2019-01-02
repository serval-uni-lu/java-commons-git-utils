import api.Gitlab;
import api.Project;

import java.util.List;

public class Main {
    public static void main(String[] args){
        Gitlab gitlab = new Gitlab()
                .setToken("")
                .setUrl("")
                .setCredentials("", "");

        List<Project> projects = gitlab.findProjectsByGroupName("");

        String location = "";
        gitlab.cloneProjects(projects, location, "");
    }
}
