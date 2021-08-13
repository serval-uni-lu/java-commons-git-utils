package lu.uni.serval.commons.git.api.gitlab;

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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Links {
    private String self;
    private String issues;
    private String mergeRequests;
    private String repoBranches;
    private String labels;
    private String events;
    private String members;

    @JsonGetter("self")
    public String getSelf() {
        return self;
    }

    @JsonSetter("self")
    public void setSelf(String self) {
        this.self = self;
    }

    @JsonGetter("issues")
    public String getIssues() {
        return issues;
    }

    @JsonSetter("issues")
    public void setIssues(String issues) {
        this.issues = issues;
    }

    @JsonGetter("merge_requests")
    public String getMergeRequests() {
        return mergeRequests;
    }

    @JsonSetter("merge_requests")
    public void setMergeRequests(String mergeRequests) {
        this.mergeRequests = mergeRequests;
    }

    @JsonGetter("repo_branches")
    public String getRepoBranches() {
        return repoBranches;
    }

    @JsonSetter("repo_branches")
    public void setRepoBranches(String repoBranches) {
        this.repoBranches = repoBranches;
    }

    @JsonGetter("labels")
    public String getLabels() {
        return labels;
    }

    @JsonSetter("labels")
    public void setLabels(String labels) {
        this.labels = labels;
    }

    @JsonGetter("events")
    public String getEvents() {
        return events;
    }

    @JsonSetter("events")
    public void setEvents(String events) {
        this.events = events;
    }

    @JsonGetter("members")
    public String getMembers() {
        return members;
    }

    @JsonSetter("members")
    public void setMembers(String members) {
        this.members = members;
    }
}
