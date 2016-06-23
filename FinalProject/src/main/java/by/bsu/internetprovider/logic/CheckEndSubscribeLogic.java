package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.AccountInfoDAO;
import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.entity.AccountInfo;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class CheckEndSubscribeLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class CheckEndSubscribeLogic {
    /**
     * Method checkEnd ...
     *
     * @param clientId of type Long
     * @throws TechnicalException when
     */
    public static void checkEnd(Long clientId) throws TechnicalException {
        AccountInfo usedTariff = null;
        try {
            Long accountId = ClientsAccountDAO.getInstance().findAccountIdByClientId(clientId);
            if ((usedTariff = AccountInfoDAO.getInstance().findActiveByAccountId(accountId)) != null) {
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDate = format.format(date);
                if (currentDate.compareTo(usedTariff.getEndDate()) >= 0 ){
                    AccountInfoDAO.getInstance().changeStatus(usedTariff.getId());
                }
            }
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
    }
}
