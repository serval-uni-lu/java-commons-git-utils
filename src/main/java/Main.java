import org.ukwikora.gitlabloader.Gitlab;
import org.ukwikora.gitlabloader.Project;

import java.util.List;

public class Main {
    public static void main(String[] args){
        Gitlab gitlab = new Gitlab()
                .setToken("")
                .setUrl("");

        List<Project> projects = gitlab.findProjectsByGroupName("");

        String location = "";
        gitlab.cloneProjects(projects, location, "");
    }
}
