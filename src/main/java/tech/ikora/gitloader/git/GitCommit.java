package tech.ikora.gitloader.git;

import org.eclipse.jgit.diff.DiffEntry;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GitCommit {
    private final String id;
    private final Date date;
    private final List<DiffEntry> diffEntries;

    public GitCommit(String id, Date date, List<DiffEntry> diffEntries) {
        this.id = id;
        this.date = date;
        this.diffEntries = diffEntries;
    }

    public static GitCommit none(){
        return new GitCommit("", Date.from(Instant.EPOCH), Collections.emptyList());
    }

    public String getId() {
        return id;
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
}
