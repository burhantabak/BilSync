package tr.edu.bilkent.bilsync.exception;

public class UserIsBannedException extends RuntimeException{
    public UserIsBannedException(String message) {
        super(message);
    }
}
