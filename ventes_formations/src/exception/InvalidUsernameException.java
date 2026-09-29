package exception;

public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException() {
        super("The username is invalid");
    }
}
