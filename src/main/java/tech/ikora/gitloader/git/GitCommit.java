package tech.ikora.gitloader.git;

import org.eclipse.jgit.diff.DiffEntry;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GitCommit {
    private final static GitCommit none = new GitCommit("", Date.from(Instant.EPOCH), Collections.emptyList());

    private final String id;
    private final String tag;
    private final Date date;
    private final List<DiffEntry> diffEntries;

    public GitCommit(String id, Date date, List<DiffEntry> diffEntries) {
        this.id = id;
        this.tag = "";
        this.date = date;
        this.diffEntries = diffEntries;
    }

    public GitCommit(String id, String tag, Date date, List<DiffEntry> diffEntries){
        this.id = id;
        this.tag = tag;
        this.date = date;
        this.diffEntries = diffEntries;
    }

    public static GitCommit none(){
        return none;
    }

    public String getId() {
        return id;
    }

    public String getTag(){
        return tag;
    }

    public Date getDate() {
        return date;
    }

    public List<DiffEntry> getDiffEntries() {
        return diffEntries;
    }

    public boolean isValid(){
        return !getId().isEmpty();
    }

    public boolean hasTag(){
        return !getTag().isEmpty();
    }
}
