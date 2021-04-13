package lu.uni.serval.commons.git.api.gitlab;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.time.Instant;

public class ContainerExpirationPolicy {
    private String cadence;
    private boolean enabled;
    String keepN;
    String olderThan;
    String nameRegex;
    String nameRegexDelete;
    String nameRegexKeep;
    Instant nextRunAt;

    @JsonGetter("cadence")
    public String getCadence() {
        return cadence;
    }

    @JsonSetter("cadence")
    public void setCadence(String cadence) {
        this.cadence = cadence;
    }

    @JsonGetter("enabled")
    public boolean isEnabled() {
        return enabled;
    }

    @JsonSetter("enabled")
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonGetter("keep_n")
    public String getKeepN() {
        return keepN;
    }

    @JsonSetter("keep_n")
    public void setKeepN(String keepN) {
        this.keepN = keepN;
    }

    @JsonGetter("older_than")
    public String getOlderThan() {
        return olderThan;
    }

    @JsonSetter("older_than")
    public void setOlderThan(String olderThan) {
        this.olderThan = olderThan;
    }

    @JsonGetter("name_regex")
    public String getNameRegex() {
        return nameRegex;
    }

    @JsonSetter("name_regex")
    public void setNameRegex(String nameRegex) {
        this.nameRegex = nameRegex;
    }

    @JsonGetter("name_regex_delete")
    public String getNameRegexDelete() {
        return nameRegexDelete;
    }

    @JsonSetter("name_regex_delete")
    public void setNameRegexDelete(String nameRegexDelete) {
        this.nameRegexDelete = nameRegexDelete;
    }

    @JsonGetter("name_regex_keep")
    public String getNameRegexKeep() {
        return nameRegexKeep;
    }

    @JsonSetter("name_regex_keep")
    public void setNameRegexKeep(String nameRegexKeep) {
        this.nameRegexKeep = nameRegexKeep;
    }

    @JsonGetter("next_run_at")
    public Instant getNextRunAt() {
        return nextRunAt;
    }

    @JsonSetter("next_run_at")
    public void setNextRunAt(Instant nextRunAt) {
        this.nextRunAt = nextRunAt;
    }
}
