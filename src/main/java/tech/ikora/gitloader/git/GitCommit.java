package tech.ikora.gitloader.git;

import java.util.Date;

public class GitCommit {
    private String id;
    private Date date;

    public GitCommit(String id, Date date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }
}
