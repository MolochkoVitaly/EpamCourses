package by.bsu.internetprovider.ajax.logic;

import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.dao.impl.PaymentDAO;
import by.bsu.internetprovider.entity.ClientAccount;
import by.bsu.internetprovider.entity.Payment;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static by.bsu.internetprovider.ajax.util.JSONUtil.jsonToSum;
import static by.bsu.internetprovider.ajax.util.JSONUtil.stringToJson;


/**
 * Class ReplenishBalanceLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class ReplenishBalanceLogic {
    /** Field PARAM_SUM_VALIDATION  */
    private static final String PARAM_SUM_VALIDATION = "[1-9][0-9]{0,5}";

    /**
     * Method replenishBalance ...
     *
     * @param user of type User
     * @param requestData of type String
     * @param locale of type String
     * @throws TechnicalException when
     * @throws LogicException when
     */
    public static void replenishBalance(User user, String requestData, String locale) throws TechnicalException, LogicException {
        Long validSum = null;
        try {
            JSONObject json = stringToJson(requestData);
            validation(jsonToSum(json), locale);
            validSum = Long.parseLong(jsonToSum(json));
            ClientAccount account = new ClientAccount();
            account.setId(ClientsAccountDAO.getInstance().findAccountIdByClientId(user.getId()));
            Payment payment = new Payment();
            payment.setAccount(account);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            payment.setPaymentDate(format.format(date));
            payment.setPaymentSum(validSum);
            PaymentDAO.getInstance().add(payment);
            Long currentBalance = ClientsAccountDAO.getInstance().getAccountBalance(user.getId());
            ClientsAccountDAO.getInstance().updateBalance(user.getId(), currentBalance + validSum);
        } catch (ParseException e) {
            throw new TechnicalException("ParseException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }
    }


    /**
     * Method validation ...
     *
     * @param sum of type String
     * @param locale of type String
     * @throws LogicException when
     */
    private static void validation(String sum, String locale) throws LogicException {
        validationField(PARAM_SUM_VALIDATION, MessageManager.INCORRECT_SUM, sum, locale);
    }

    /**
     * Method validationField ...
     *
     * @param pattern of type String
     * @param errorMessage of type String
     * @param field of type String
     * @param locale of type String
     * @throws LogicException when
     */
    private static void validationField(String pattern, String errorMessage, String field, String locale) throws LogicException {
        if (!Pattern.matches(pattern, field)) {
            throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(errorMessage));
        }
    }
}

