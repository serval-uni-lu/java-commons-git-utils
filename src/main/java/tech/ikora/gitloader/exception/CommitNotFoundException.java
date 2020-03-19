package tech.ikora.gitloader.exception;

public class CommitNotFoundException extends Exception {
    public CommitNotFoundException(String message){
        super(message);
    }
}
