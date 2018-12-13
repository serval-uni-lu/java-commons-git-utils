import java.util.List;

public class Main {
    public static void main(String[] args){
        Gitlab gitlab = new Gitlab()
                .setToken("")
                .setUrl("");

        List<Project> projects = gitlab.findProjectByGroupName("Testfactory");

        for(Project project: projects) {
            System.out.println(project.getName());
        }
    }
}
