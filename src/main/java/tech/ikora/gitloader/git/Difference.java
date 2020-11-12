package tech.ikora.gitloader.git;

import org.eclipse.jgit.diff.DiffEntry;

import java.util.Collections;
import java.util.List;

public class Difference {
    private final List<DiffEntry> entries;
    private final String formatted;

    public Difference(List<DiffEntry> entries, String formatted) {
        this.entries = entries;
        this.formatted = formatted;
    }

    public static Difference none() {
        return new Difference(Collections.emptyList(), "");
    }

    public List<DiffEntry> getEntries() {
        return entries;
    }

    public String getFormatted() {
        return formatted;
    }
}
