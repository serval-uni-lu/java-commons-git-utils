import org.ukwikora.gitlabloader.Gitlab;
import org.ukwikora.gitlabloader.Project;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Gitlab gitlab = new Gitlab()
                .setToken("")
                .setUrl("");

        List<Project> projects = null;
        try {
            projects = gitlab.findProjectsByGroupName("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String location = "";
        gitlab.cloneProjects(projects, location, "");
    }
}
