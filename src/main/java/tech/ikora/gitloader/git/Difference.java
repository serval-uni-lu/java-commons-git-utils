package tech.ikora.gitloader.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Difference {
    private final Git git;
    private final List<DiffEntry> entries;
    private String formatted;

    public Difference(Git git, List<DiffEntry> entries) {
        this.git = git;
        this.entries = entries;
        this.formatted = null;
    }

    public static Difference none() {
        return new Difference(null, Collections.emptyList());
    }

    public List<DiffEntry> getEntries() {
        return entries;
    }

    public String getFormatted() throws IOException {
        if(git == null){
            formatted = "";
        }

        if(formatted == null){
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            DiffFormatter formatter = new DiffFormatter(outputStream);
            formatter.setRepository(git.getRepository());
            formatter.format(this.entries);

            this.formatted = outputStream.toString().trim();
        }

        return formatted;
    }
}
