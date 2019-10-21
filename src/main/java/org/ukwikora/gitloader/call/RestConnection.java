package org.ukwikora.gitloader.call;

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
    private static HttpClient client;
    private static ObjectMapper mapper;

    static {
        client = HttpClientBuilder.create().build();
        mapper = new ObjectMapper();
    }

    static public String getRequest(String request, String token) throws IOException {
        HttpGet get = new HttpGet(request);
        get.addHeader("PRIVATE-TOKEN", token);
        HttpResponse response;
        response = client.execute(get);

        return EntityUtils.toString(response.getEntity());
    }

    static public <T> T getObject(String request, String token, Class<T> type) throws IOException {
        String json = RestConnection.getRequest(request, token);

        if(json.isEmpty()) {
            throw  new IOException(String.format("Request %s with token %s returned an empty response", request, token));
        }

        JavaType javaType = mapper.getTypeFactory().constructType(type);
        return mapper.readValue(json, javaType);
    }

    static public <T> Set<T> getObjectList(String request, String token, Class<T> type) throws IOException {
        String json = RestConnection.getRequest(request, token);

        if(json.isEmpty()) {
            throw  new IOException(String.format("Request %s with token %s returned an empty response", request, token));
        }

        JavaType javaType = mapper.getTypeFactory().constructCollectionType(Set.class, type);
        return mapper.readValue(json, javaType);
    }
}
