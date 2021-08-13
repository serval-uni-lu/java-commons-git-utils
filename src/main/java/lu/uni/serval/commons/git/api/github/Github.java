package lu.uni.serval.commons.git.api.github;

/*-
 * #%L
 * GIT Utils
 * %%
 * Copyright (C) 2020 - 2021 University of Luxembourg, Renaud RWEMALIKA
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License")
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

import lu.uni.serval.commons.git.utils.LocalRepository;
import lu.uni.serval.commons.git.api.GitEngine;
import lu.uni.serval.commons.git.exception.InvalidGitRepositoryException;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class Github extends GitEngine {
    @Override
    public Set<LocalRepository> cloneProjectsFromNames(Set<String> projectNames) throws IOException, InvalidGitRepositoryException {
        return Collections.emptySet();
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromGroup(String group) throws IOException, InvalidGitRepositoryException {
        return Collections.emptySet();
    }

    @Override
    public Set<LocalRepository> cloneProjectsFromUser(String user) throws IOException, InvalidGitRepositoryException {
        return Collections.emptySet();
    }
}
