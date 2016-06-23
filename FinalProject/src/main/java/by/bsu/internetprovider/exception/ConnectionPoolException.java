package by.bsu.internetprovider.exception;


/**
 * Class ConnectionPoolException ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ConnectionPoolException extends Exception {
    /**
     * Constructor ConnectionPoolException creates a new ConnectionPoolException instance.
     */
    public ConnectionPoolException() {
    }

    /**
     * Constructor ConnectionPoolException creates a new ConnectionPoolException instance.
     *
     * @param message of type String
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Constructor ConnectionPoolException creates a new ConnectionPoolException instance.
     *
     * @param message of type String
     * @param cause of type Throwable
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor ConnectionPoolException creates a new ConnectionPoolException instance.
     *
     * @param cause of type Throwable
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
