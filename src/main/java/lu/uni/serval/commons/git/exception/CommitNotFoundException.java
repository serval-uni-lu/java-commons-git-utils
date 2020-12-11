package lu.uni.serval.commons.git.exception;

public class CommitNotFoundException extends Exception {
    public CommitNotFoundException(String message){
        super(message);
    }
}
