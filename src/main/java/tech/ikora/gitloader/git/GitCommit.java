package tech.ikora.gitloader.git;

import org.eclipse.jgit.diff.DiffEntry;

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

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public List<DiffEntry> getDiffEntries() {
        return diffEntries;
    }
}
