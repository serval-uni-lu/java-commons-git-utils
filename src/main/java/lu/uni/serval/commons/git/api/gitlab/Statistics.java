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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Statistics {
    int commitCount;
    int storageSize;
    int repositorySize;
    int wikiSize;
    int lfsObjectSize;
    int jobArtifactSize;
    int packagesSize;
    int snippetsSize;

    @JsonGetter("commit_count")
    public int getCommitCount() {
        return commitCount;
    }

    @JsonSetter("commit_count")
    public void setCommitCount(int commitCount) {
        this.commitCount = commitCount;
    }

    @JsonGetter("storage_size")
    public int getStorageSize() {
        return storageSize;
    }

    @JsonSetter("storage_size")
    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    @JsonGetter("repository_size")
    public int getRepositorySize() {
        return repositorySize;
    }

    @JsonSetter("repository_size")
    public void setRepositorySize(int repositorySize) {
        this.repositorySize = repositorySize;
    }

    @JsonGetter("wiki_size")
    public int getWikiSize() {
        return wikiSize;
    }

    @JsonSetter("wiki_size")
    public void setWikiSize(int wikiSize) {
        this.wikiSize = wikiSize;
    }

    @JsonGetter("lfs_objects_size")
    public int getLfsObjectSize() {
        return lfsObjectSize;
    }

    @JsonSetter("lfs_objects_size")
    public void setLfsObjectSize(int lfsObjectSize) {
        this.lfsObjectSize = lfsObjectSize;
    }

    @JsonGetter("job_artifacts_size")
    public int getJobArtifactSize() {
        return jobArtifactSize;
    }

    @JsonSetter("job_artifacts_size")
    public void setJobArtifactSize(int jobArtifactSize) {
        this.jobArtifactSize = jobArtifactSize;
    }

    @JsonGetter("packages_size")
    public int getPackagesSize() {
        return packagesSize;
    }

    @JsonSetter("packages_size")
    public void setPackagesSize(int packagesSize) {
        this.packagesSize = packagesSize;
    }

    @JsonGetter("snippets_size")
    public int getSnippetsSize() {
        return snippetsSize;
    }

    @JsonSetter("snippets_size")
    public void setSnippetsSize(int snippetsSize) {
        this.snippetsSize = snippetsSize;
    }
}
