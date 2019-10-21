package org.ukwikora.gitloader.gitlab;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static String getSingleUser(){
        return "{\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"john_smith\",\n" +
                "    \"email\": \"john@example.com\",\n" +
                "    \"name\": \"John Smith\",\n" +
                "    \"state\": \"active\",\n" +
                "    \"avatar_url\": \"http://localhost:3000/uploads/user/avatar/1/index.jpg\",\n" +
                "    \"web_url\": \"http://localhost:3000/john_smith\",\n" +
                "    \"created_at\": \"2012-05-23T08:00:58Z\",\n" +
                "    \"is_admin\": false,\n" +
                "    \"bio\": null,\n" +
                "    \"location\": null,\n" +
                "    \"skype\": \"\",\n" +
                "    \"linkedin\": \"\",\n" +
                "    \"twitter\": \"\",\n" +
                "    \"website_url\": \"\",\n" +
                "    \"organization\": \"\",\n" +
                "    \"last_sign_in_at\": \"2012-06-01T11:41:01Z\",\n" +
                "    \"confirmed_at\": \"2012-05-23T09:05:22Z\",\n" +
                "    \"theme_id\": 1,\n" +
                "    \"last_activity_on\": \"2012-05-23\",\n" +
                "    \"color_scheme_id\": 2,\n" +
                "    \"projects_limit\": 100,\n" +
                "    \"current_sign_in_at\": \"2012-06-02T06:36:55Z\",\n" +
                "    \"identities\": [\n" +
                "      {\"provider\": \"github\", \"extern_uid\": \"2435223452345\"},\n" +
                "      {\"provider\": \"bitbucket\", \"extern_uid\": \"john.smith\"},\n" +
                "      {\"provider\": \"google_oauth2\", \"extern_uid\": \"8776128412476123468721346\"}\n" +
                "    ],\n" +
                "    \"can_create_group\": true,\n" +
                "    \"can_create_project\": true,\n" +
                "    \"two_factor_enabled\": true,\n" +
                "    \"external\": false,\n" +
                "    \"private_profile\": false\n" +
                "  }";
    }

    @Test
    void testSingleUserParsing() throws IOException {
        String json = getSingleUser();

        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructType(User.class);
        final User user = mapper.readValue(json, javaType);

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals(3, user.getIdentities().size());
    }
}