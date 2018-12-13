import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class RestConnection {
    private static HttpClient client;
    private static ObjectMapper mapper;

    static {
        client = HttpClientBuilder.create().build();
        mapper = new ObjectMapper();
    }

    static public String getRequest(String request, String token) {
        HttpGet get = new HttpGet(request);
        get.addHeader("PRIVATE-TOKEN", token);
        HttpResponse response;

        String json;

        try{
            response = client.execute(get);
            json = EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            json = "";
        }

        return json;
    }

    static public <T> T getObject(String request, String token, Class<T> type) {
        String json = RestConnection.getRequest(request, token);

        if(json.isEmpty()) {
            return null;
        }

        T object;

        try {
            JavaType javaType = mapper.getTypeFactory().constructType(type);
            object = mapper.readValue(json, javaType);
        } catch (IOException e) {
            object = null;
        }

        return object;
    }

    static public <T> List<T> getObjectList(String request, String token, Class<T> type) {
        String json = RestConnection.getRequest(request, token);

        if(json.isEmpty()) {
            return null;
        }

        List<T> objects;

        try {
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, type);
            objects = mapper.readValue(json, javaType);
        } catch (IOException e) {
            objects = null;
        }

        return objects;
    }
}
