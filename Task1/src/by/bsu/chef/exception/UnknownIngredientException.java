package by.bsu.chef.exception;


public class UnknownIngredientException extends Exception {
    public UnknownIngredientException() {
    }

    public UnknownIngredientException(String message) {
        super(message);
    }

    public UnknownIngredientException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownIngredientException(Throwable cause) {
        super(cause);
    }

    public UnknownIngredientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

