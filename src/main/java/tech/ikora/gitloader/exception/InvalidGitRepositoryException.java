package tech.ikora.gitloader.exception;

public class InvalidGitRepositoryException extends Exception {
    public InvalidGitRepositoryException(String message){
        super(message);
    }
}
