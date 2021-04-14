package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public abstract class Access {
    private int accessLevel;
    private int notificationLevel;

    @JsonGetter("access_level")
    public int getAccessLevel() {
        return accessLevel;
    }

    @JsonSetter("access_level")
    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @JsonGetter("notification_level")
    public int getNotificationLevel() {
        return notificationLevel;
    }

    @JsonSetter("notification_level")
    public void setNotificationLevel(int notificationLevel) {
        this.notificationLevel = notificationLevel;
    }
}
