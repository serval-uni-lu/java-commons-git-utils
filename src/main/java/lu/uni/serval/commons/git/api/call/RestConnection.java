package lu.uni.serval.commons.git.api.call;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Set;

public class RestConnection {

    private static ObjectMapper mapper;

    static {

        mapper = new ObjectMapper();
    }

    private RestConnection() {}

    public static String getRequest(String request, String token) throws IOException {
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpGet get = new HttpGet(request);

        get.addHeader("PRIVATE-TOKEN", token);
        final HttpResponse response = client.execute(get);

        return EntityUtils.toString(response.getEntity());
    }

    public static <T> T getObject(String request, String token, Class<T> type) throws IOException {
        String json = RestConnection.getRequest(request, token);

        if(json.isEmpty()) {
            throw  new IOException(String.format("Request %s with token %s returned an empty response", request, token));
        }

        JavaType javaType = mapper.getTypeFactory().constructType(type);
        return mapper.readValue(json, javaType);
    }

    public static <T> Set<T> getObjectList(String request, String token, Class<T> type) throws IOException {
        String json = RestConnection.getRequest(request, token);

        if(json.isEmpty()) {
            throw  new IOException(String.format("Request %s with token %s returned an empty response", request, token));
        }

        JavaType javaType = mapper.getTypeFactory().constructCollectionType(Set.class, type);
        return mapper.readValue(json, javaType);
    }
}
