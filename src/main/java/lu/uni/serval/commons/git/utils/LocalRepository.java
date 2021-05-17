package lu.uni.serval.commons.git.utils;

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

import org.eclipse.jgit.api.Git;

import java.io.File;

public class LocalRepository {
    private final Git git;
    private final File location;
    private final String remoteUrl;
    private final GitCommit gitCommit;

    public LocalRepository(Git git, File location, String remoteUrl, GitCommit gitCommit) {
        this.git = git;
        this.location = location;
        this.remoteUrl = remoteUrl;
        this.gitCommit = gitCommit;
    }

    public File getLocation() {
        return location;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public GitCommit getGitCommit() {
        return gitCommit;
    }

    public Git getGit() {
        return git;
    }
}
