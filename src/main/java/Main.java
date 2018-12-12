import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("");
        HttpResponse response;

        try{
            response = client.execute(request);
            String json = EntityUtils.toString(response.getEntity());
            System.out.println(json);

            ObjectMapper mapper = new ObjectMapper();
            List<Project> projects = mapper.readValue(json, new TypeReference<List<Project>>(){});

            for(Project project: projects){
                System.out.println(project.getName());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
