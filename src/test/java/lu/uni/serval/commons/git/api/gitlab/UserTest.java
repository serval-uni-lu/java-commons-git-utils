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
