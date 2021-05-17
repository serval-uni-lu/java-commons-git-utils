package lu.uni.serval.commons.git.api.gitlab;

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
