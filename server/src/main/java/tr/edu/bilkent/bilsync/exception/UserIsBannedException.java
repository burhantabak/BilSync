package tr.edu.bilkent.bilsync.exception;

/**
 * Exception indicating that a user is banned and cannot perform certain actions.
 * This exception is thrown when a banned user attempts to perform restricted actions.
 */
public class UserIsBannedException extends RuntimeException{

    /**
     * Constructs a new UserIsBannedException with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserIsBannedException(String message) {
        super(message);
    }
}
