package lu.uni.serval.commons.git.api.call;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lu.uni.serval.commons.git.api.MapperFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Set;

public class RestConnection {

    private static final ObjectMapper mapper;

    static {
        mapper = MapperFactory.create();
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
