package lu.uni.serval.commons.git.utils;


import java.time.Instant;

public class GitCommit {
    private static final GitCommit none = new GitCommit("", Instant.EPOCH);

    private final String id;
    private final String tag;
    private final Instant date;
    private Difference difference;

    public GitCommit(String id, Instant date) {
        this.id = id;
        this.tag = "";
        this.date = date;
        this.difference = Difference.none();
    }

    public GitCommit(String id, String tag, Instant date){
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

    public Instant getDate() {
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
