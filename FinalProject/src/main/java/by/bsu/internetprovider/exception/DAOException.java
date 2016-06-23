package by.bsu.internetprovider.exception;


/**
 * Class DAOException ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class DAOException extends Exception {

    /**
     * Constructor DAOException creates a new DAOException instance.
     */
    public DAOException() {
    }

    /**
     * Constructor DAOException creates a new DAOException instance.
     *
     * @param message of type String
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructor DAOException creates a new DAOException instance.
     *
     * @param message of type String
     * @param cause of type Throwable
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
