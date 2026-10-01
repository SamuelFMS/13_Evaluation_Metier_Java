package exception;

/**
 * Exception for an invalid prefix number phone
 */
public class InvalidPhonePrefixException extends RuntimeException {
    public InvalidPhonePrefixException(String message) {
        super(message);
    }
}
