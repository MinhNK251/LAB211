package exception;

public class NonExistentKeyException extends Exception{
    public NonExistentKeyException (String message) {
        super(message);
    }
}