package by.bsu.internetprovider.exception;


/**
 * Class TechnicalException ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class TechnicalException extends Exception {
    /**
     * Constructor TechnicalException creates a new TechnicalException instance.
     */
    public TechnicalException() {
    }

    /**
     * Constructor TechnicalException creates a new TechnicalException instance.
     *
     * @param message of type String
     */
    public TechnicalException(String message) {
        super(message);
    }

    /**
     * Constructor TechnicalException creates a new TechnicalException instance.
     *
     * @param message of type String
     * @param cause of type Throwable
     */
    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor TechnicalException creates a new TechnicalException instance.
     *
     * @param cause of type Throwable
     */
    public TechnicalException(Throwable cause) {
        super(cause);
    }
}
