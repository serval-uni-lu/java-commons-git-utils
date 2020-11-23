package tech.ikora.gitloader.git;


import java.time.Instant;
import java.util.Date;

public class GitCommit {
    private final static GitCommit none = new GitCommit("", Date.from(Instant.EPOCH));

    private final String id;
    private final String tag;
    private final Date date;
    private Difference difference;

    public GitCommit(String id, Date date) {
        this.id = id;
        this.tag = "";
        this.date = date;
        this.difference = Difference.none();
    }

    public GitCommit(String id, String tag, Date date){
        this.id = id;
        this.tag = tag;
        this.date = date;
        this.difference = Difference.none();
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

    public void setDifference(Difference difference){
        this.difference = difference;
    }

    public Difference getDifference() {
        return difference;
    }

    public boolean isValid(){
        return !getId().isEmpty();
    }

    public boolean hasTag(){
        return !getTag().isEmpty();
    }
}
