package exception;

/**
 * Exception for an invalid Username
 */
public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("The username is invalid");
    }
}
