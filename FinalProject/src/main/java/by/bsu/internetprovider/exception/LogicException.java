package by.bsu.internetprovider.exception;


/**
 * Class LogicException ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class LogicException extends Exception {
    /**
     * Constructor LogicException creates a new LogicException instance.
     */
    public LogicException() {
    }

    /**
     * Constructor LogicException creates a new LogicException instance.
     *
     * @param message of type String
     */
    public LogicException(String message) {
        super(message);
    }

    /**
     * Constructor LogicException creates a new LogicException instance.
     *
     * @param message of type String
     * @param cause of type Throwable
     */
    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor LogicException creates a new LogicException instance.
     *
     * @param cause of type Throwable
     */
    public LogicException(Throwable cause) {
        super(cause);
    }
}
