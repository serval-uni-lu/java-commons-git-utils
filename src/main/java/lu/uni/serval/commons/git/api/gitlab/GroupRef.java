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

public class GroupRef {
    private String groupId;
    private String groupName;
    private String groupFullPath;
    private int groupAccessLevel;
    private String expiresAt;

    @JsonGetter("group_id")
    public String getGroupId() {
        return groupId;
    }

    @JsonSetter("group_id")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JsonGetter("group_name")
    public String getGroupName() {
        return groupName;
    }

    @JsonSetter("group_name")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @JsonGetter("group_full_path")
    public String getGroupFullPath() {
        return groupFullPath;
    }

    @JsonSetter("group_full_path")
    public void setGroupFullPath(String groupFullPath) {
        this.groupFullPath = groupFullPath;
    }

    @JsonGetter("group_access_level")
    public int getGroupAccessLevel() {
        return groupAccessLevel;
    }

    @JsonSetter("group_access_level")
    public void setGroupAccessLevel(int groupAccessLevel) {
        this.groupAccessLevel = groupAccessLevel;
    }

    @JsonGetter("expires_at")
    public String getExpiresAt() {
        return expiresAt;
    }

    @JsonSetter("expires_at")
    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }
}
