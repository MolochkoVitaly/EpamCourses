package by.bsu.internetprovider.ajax.logic;

import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import org.json.simple.JSONObject;


/**
 * Class ShowBalanceLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class ShowBalanceLogic {
    /** Field BALANCE  */
    private static final String BALANCE = "balance";

    /**
     * Method showBalance ...
     *
     * @param clientId of type Long
     * @return String
     * @throws TechnicalException when
     */
    public static String showBalance (Long clientId) throws TechnicalException {
        JSONObject jsonObject = new JSONObject();
        Long balance = null;
        try {
            balance = ClientsAccountDAO.getInstance().getAccountBalance(clientId);
            jsonObject.put(BALANCE, balance);
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return jsonObject.toJSONString();
    }
}
