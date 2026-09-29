package exception;

public class InvalidPhonePrefixException extends RuntimeException {
    public InvalidPhonePrefixException(String message) {
        super(message);
    }
}
