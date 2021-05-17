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

class GroupTest {
    static Group group1;
    static Group group2;
    static Group group3;

    @BeforeAll
    static void deserializeProjects() throws IOException, URISyntaxException {
        group1 = Helpers.deserializeJsonFromResources("json/gitlab/group-1.json", Group.class);
        group2 = Helpers.deserializeJsonFromResources("json/gitlab/group-2.json", Group.class);
        group3 = Helpers.deserializeJsonFromResources("json/gitlab/group-3.json", Group.class);
    }

    @Test
    void testDeserializeJsonGeneratesValidProjects(){
        assertNotNull(group1);
        assertNotNull(group2);
        assertNotNull(group3);
    }

    @Test
    void testDeserializeId() {
        assertEquals(1, group1.getId());
        assertEquals(2, group2.getId());
        assertEquals(3, group3.getId());
    }
}
