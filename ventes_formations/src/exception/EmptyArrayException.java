package exception;

/**
 * Exception for an empty array
 */
public class EmptyArrayException extends Exception {
    public EmptyArrayException() {
        super("Array is empty");
    }
}
