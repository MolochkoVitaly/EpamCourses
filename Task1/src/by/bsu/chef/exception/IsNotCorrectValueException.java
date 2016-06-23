package by.bsu.chef.exception;


public class IsNotCorrectValueException extends Exception {
    public IsNotCorrectValueException() {
    }

    public IsNotCorrectValueException(String message) {
        super(message);
    }

    public IsNotCorrectValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public IsNotCorrectValueException(Throwable cause) {
        super(cause);
    }

    public IsNotCorrectValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
