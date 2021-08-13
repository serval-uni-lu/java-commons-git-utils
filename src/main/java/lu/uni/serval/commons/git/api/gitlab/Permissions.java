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

public class Permissions {
    private ProjectAccess projectAccess;
    private GroupAccess groupAccess;

    @JsonGetter("project_access")
    public ProjectAccess getProjectAccess() {
        return projectAccess;
    }

    @JsonSetter("project_access")
    public void setProjectAccess(ProjectAccess projectAccess) {
        this.projectAccess = projectAccess;
    }

    @JsonGetter("group_access")
    public GroupAccess getGroupAccess() {
        return groupAccess;
    }

    @JsonSetter("group_access")
    public void setGroupAccess(GroupAccess groupAccess) {
        this.groupAccess = groupAccess;
    }
}
