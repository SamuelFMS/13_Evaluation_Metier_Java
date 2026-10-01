package exception;

/**
 * Exception for an already taken username
 */
public class UsernameAlreadyExistException extends RuntimeException {
    public UsernameAlreadyExistException() {
        super("this username is already in use");
    }
}
