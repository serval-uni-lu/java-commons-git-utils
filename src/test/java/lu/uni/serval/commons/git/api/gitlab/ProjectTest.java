package lu.uni.serval.commons.git.api.gitlab;

import lu.uni.serval.commons.git.Helpers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProjectTest {
    static Project project1;
    static Project project2;

    @BeforeAll
    static void deserializeProjects() throws IOException, URISyntaxException {
        project1 = Helpers.deserializeJsonFromResources("json/gitlab/project-1.json", Project.class);
        project2 = Helpers.deserializeJsonFromResources("json/gitlab/project-2.json", Project.class);
    }

    @Test
    void testDeserializeJsonGeneratesValidProjects(){
        assertNotNull(project1);
        assertNotNull(project2);
    }

    @Test
    void testDeserializeId() {
        assertEquals(1, project1.getId());
        assertEquals(2, project2.getId());
    }
}
