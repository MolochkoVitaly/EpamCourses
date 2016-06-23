package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.AccountInfoDAO;
import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.entity.AccountInfo;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class ShowUsedTariffsLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ShowUsedTariffsLogic {
    /**
     * Method show ...
     *
     * @param clientId of type Long
     * @return AccountInfo
     * @throws TechnicalException when
     */
    public static AccountInfo show(Long clientId) throws TechnicalException {
        AccountInfo usedTariff = null;
        try {
            CheckEndSubscribeLogic.checkEnd(clientId);
            Long accountId = ClientsAccountDAO.getInstance().findAccountIdByClientId(clientId);
            usedTariff = AccountInfoDAO.getInstance().findActiveByAccountId(accountId);
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return usedTariff;
    }
}
