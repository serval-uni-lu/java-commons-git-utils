package org.ukwikora.gitloader.git;

import java.io.File;
import java.util.Date;

public class LocalRepo {
    private final File location;
    private final String commitId;
    private final Date date;
    private final String remoteUrl;

    public LocalRepo(File location, String commitId, Date date, String remoteUrl) {
        this.location = location;
        this.commitId = commitId;
        this.date = date;
        this.remoteUrl = remoteUrl;
    }


    public File getLocation() {
        return location;
    }

    public String getCommitId() {
        return commitId;
    }

    public Date getDate() {
        return date;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }
}
