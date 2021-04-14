package lu.uni.serval.commons.git.api.gitlab;

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
