package lu.uni.serval.commons.git.api.gitlab;

import lu.uni.serval.commons.git.Helpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    static User user1;
    static User user2;

    @BeforeAll
    static void deserializeProjects() throws IOException, URISyntaxException {
        user1 = Helpers.deserializeJsonFromResources("json/gitlab/user-1.json", User.class);
        user2 = Helpers.deserializeJsonFromResources("json/gitlab/user-2.json", User.class);
    }

    @Test
    void testDeserializeJsonGeneratesValidProjects(){
        assertNotNull(user1);
        assertNotNull(user2);
    }

    @Test
    void testDeserializeId() {
        assertEquals(1, user1.getId());
        assertEquals(2, user2.getId());
    }
}