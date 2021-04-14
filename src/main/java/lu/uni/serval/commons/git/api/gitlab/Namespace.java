package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Namespace extends GitlabEntity {
    private String path;
    private String kind;
    private String fullPath;
    private int parentId;

    @JsonGetter("path")
    public String getPath() {
        return path;
    }

    @JsonSetter("path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonGetter("kind")
    public String getKind() {
        return kind;
    }

    @JsonSetter("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonGetter("full_path")
    public String getFullPath() {
        return fullPath;
    }

    @JsonSetter("full_path")
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    @JsonGetter("parent_id")
    public int getParentId() {
        return parentId;
    }

    @JsonSetter("parent_id")
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
