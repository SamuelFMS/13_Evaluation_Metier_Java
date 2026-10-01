package exception;

/**
 * Exception for an invalid number phone
 */
public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
