import org.gitlabloader.api.Gitlab;
import org.gitlabloader.api.Project;

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
