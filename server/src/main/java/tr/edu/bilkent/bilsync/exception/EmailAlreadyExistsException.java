package tr.edu.bilkent.bilsync.exception;

/**
 * Exception indicating that a user registration with a given email already exists.
 * This exception is thrown when attempting to register a user with an email that is already in use.
 */
public class EmailAlreadyExistsException extends RuntimeException{

    /**
     * Constructs a new EmailAlreadyExistsException with the specified detail message.
     *
     * @param message the detail message.
     */
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
