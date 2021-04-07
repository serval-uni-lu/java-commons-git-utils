package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Namespace {
    private int id;
    private String name;
    private String path;
    private String kind;
    private String fullPath;
    private int parentId;
    private String avatarUrl;
    private String webUrl;

    @JsonGetter("id")
    public int getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

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

    @JsonGetter("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonSetter("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonGetter("web_url")
    public String getWebUrl() {
        return webUrl;
    }

    @JsonSetter("web_url")
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
