package by.bsu.internetprovider.ajax.logic;


import by.bsu.internetprovider.dao.impl.AccountInfoDAO;
import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.dao.impl.TariffDAO;
import by.bsu.internetprovider.entity.AccountInfo;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.logic.CheckEndSubscribeLogic;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.commons.lang3.time.DateUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static by.bsu.internetprovider.ajax.util.JSONUtil.jsonToId;
import static by.bsu.internetprovider.ajax.util.JSONUtil.stringToJson;


/**
 * Class SubscribeLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class SubscribeLogic {
    /**
     * Method subscribe ...
     *
     * @param requestData of type String
     * @param clientId of type Long
     * @param locale of type String
     * @throws TechnicalException when
     * @throws LogicException when
     */
    public static void subscribe(String requestData, Long clientId, String locale) throws TechnicalException, LogicException {
        try {
            CheckEndSubscribeLogic.checkEnd(clientId);
            JSONObject json = stringToJson(requestData);
            Long accountId = ClientsAccountDAO.getInstance().findAccountIdByClientId(clientId);
            Tariff tariff = TariffDAO.getInstance().findById(jsonToId(json));
            if (checkTariff(accountId)) {
                if (usedPreviously(accountId, tariff.getId())) {
                    update(accountId, clientId, tariff, locale);
                }
                else  {
                    create(accountId, clientId, tariff, locale);
                }
            } else {
                throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(MessageManager.SUBSCRIBE_IS_ALREADY_IN_USE));
            }
        } catch (ParseException e) {
            throw new TechnicalException("ParseException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }
    }

    /**
     * Method checkTariff ...
     *
     * @param accountId of type Long
     * @return boolean
     * @throws DAOException when
     */
    private static boolean checkTariff(Long accountId) throws DAOException {
        return AccountInfoDAO.getInstance().checkActiveTariff(accountId);
    }

    /**
     * Method usedPreviously ...
     *
     * @param accountId of type Long
     * @param tariffId of type Long
     * @return boolean
     * @throws DAOException when
     */
    private static boolean usedPreviously(Long accountId, Long tariffId) throws DAOException {
        return AccountInfoDAO.getInstance().usedPreviously(accountId, tariffId);
    }

    /**
     * Method create ...
     *
     * @param accountId of type Long
     * @param clientId of type Long
     * @param tariff of type Tariff
     * @param locale of type String
     * @throws DAOException when
     * @throws LogicException when
     */
    private static void create(Long accountId, Long clientId, Tariff tariff, String locale) throws DAOException, LogicException {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.getClientAccount().setId(accountId);
        accountInfo.getTariff().setId(tariff.getId());
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        accountInfo.setCreatingDate(format.format(date));
        accountInfo.setEndDate(format.format(DateUtils.addDays(date, 30)));
        Long currentBalance = ClientsAccountDAO.getInstance().getAccountBalance(clientId);
        if (currentBalance - tariff.getMonthPayment() >= 0) {
            AccountInfoDAO.getInstance().add(accountInfo);
            ClientsAccountDAO.getInstance().updateBalance(clientId, currentBalance - tariff.getMonthPayment());
        } else {
            throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(MessageManager.INSUFFICIENT_MONEY));
        }
    }

    /**
     * Method update ...
     *
     * @param accountId of type Long
     * @param clientId of type Long
     * @param tariff of type Tariff
     * @param locale of type String
     * @throws DAOException when
     * @throws LogicException when
     */
    private static void update(Long accountId, Long clientId, Tariff tariff, String locale) throws DAOException, LogicException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String creatingDate = format.format(date);
        String endDate = format.format(DateUtils.addDays(date, 30));
        Long currentBalance = ClientsAccountDAO.getInstance().getAccountBalance(clientId);
        if (currentBalance - tariff.getMonthPayment() >= 0) {
            AccountInfoDAO.getInstance().updateUsedPreviously(accountId, tariff.getId(), creatingDate, endDate);
            ClientsAccountDAO.getInstance().updateBalance(clientId, currentBalance - tariff.getMonthPayment());
        } else {
            throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(MessageManager.INSUFFICIENT_MONEY));
        }
    }
}
