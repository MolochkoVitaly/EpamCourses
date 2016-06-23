package by.bsu.internetprovider.logic;

import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;


/**
 * Class ShowBalanceLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ShowBalanceLogic {

    /**
     * Method showBalance ...
     *
     * @param clientId of type Long
     * @return Long
     * @throws TechnicalException when
     */
    public static Long showBalance(Long clientId) throws TechnicalException {
        Long balance = null;
        try {
            balance = ClientsAccountDAO.getInstance().getAccountBalance(clientId);
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return balance;
    }
}
