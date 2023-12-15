package tr.edu.bilkent.bilsync.exception;

/**
 * Exception thrown when no records are found in the database.
 */
public class NoRecordFoundException extends RuntimeException {

    /**
     * Constructs a new NoRecordFoundException with the specified error message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public NoRecordFoundException(String message) {
        super(message);
    }
}
