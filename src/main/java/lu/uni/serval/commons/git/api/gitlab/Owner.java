package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.Instant;

public class Owner extends GitlabEntity {
    private String username;
    private String state;
    private String avatarUrl;
    private Instant createAt;

    @JsonGetter("username")
    public String getUsername() {
        return username;
    }

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonSetter("state")
    public String getState() {
        return state;
    }

    @JsonSetter("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonGetter("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonSetter("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @JsonGetter("created_at")
    public Instant getCreateAt() {
        return createAt;
    }

    @JsonSetter("created_at")
    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }
}
