package lu.uni.serval.commons.git.api.gitlab;

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
