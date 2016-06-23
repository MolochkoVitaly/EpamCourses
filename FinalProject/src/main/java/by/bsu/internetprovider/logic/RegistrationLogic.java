package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.ClientsAccountDAO;
import by.bsu.internetprovider.entity.ClientAccount;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.dao.impl.UserDAO;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Class RegistrationLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class RegistrationLogic {
    /** Field LOG  */
    public static final Logger LOG = Logger.getLogger(RegistrationLogic.class);

    /** Field PARAM_EMAIL_VALIDATION  */
    private static final String PARAM_EMAIL_VALIDATION =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    /** Field PARAM_PASSWORD_VALIDATION  */
    private static final String PARAM_PASSWORD_VALIDATION = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{4,19}$";

    /** Field PARAM_NAME_VALIDATION  */
    private static final String PARAM_NAME_VALIDATION = "^[A-ZА-ЯЁ][a-zа-яё]{2,19}$";

    /** Field PARAM_PHONE_VALIDATION  */
    private static final String PARAM_PHONE_VALIDATION = "(\\+375)(17|25|29|33|44)[0-9]{7}";

    /** Field PARAM_ADDRESS_VALIDATION  */
    private static final String PARAM_ADDRESS_VALIDATION =
            "((Брестская|Витебская|Гомельская|Гродненская|Минская|Могилевская)\\s(область,))" +
                    "(\\s)(г.\\s)([А-ЯЁ][а-яё]{2,})(,\\s)(ул.\\s)([А-ЯЁ][А-ЯЁа-яё\\s-]{2,})(,\\s)(дом\\s)([0-9]{1,})" +
                    "((,\\s)(кв.\\s)[0-9]{1,}){0,}";

    /**
     * Method registrate ...
     *
     * @param user of type User
     * @param password of type String
     * @param passwordAgain of type String
     * @param locale of type String
     * @throws TechnicalException when
     * @throws LogicException when
     */
    public static void registrate(User user, String password,String passwordAgain, String locale) throws TechnicalException, LogicException {
        ClientAccount account = null;
        try {
            validation(user, password, passwordAgain, locale);
            if (checkEmail(user.getEmail())) {
                UserDAO.getInstance().add(user);
                account = new ClientAccount();
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                account.setCreatingDate(format.format(date));
                account.setUser(UserDAO.getInstance().findByLoginPasswordInClients(user.getEmail(), user.getPassword()));
                ClientsAccountDAO.getInstance().add(account);
            } else {
                throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(
                        MessageManager.REGISTRATION_WAS_INTERRUPT_MESSAGE));
            }
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
    }

    /**
     * Method validation ...
     *
     * @param user of type User
     * @param password of type String
     * @param passwordAgain of type String
     * @param locale of type String
     * @throws LogicException when
     */
    private static void validation(User user, String password, String passwordAgain, String locale) throws LogicException {
        validationPassword(password, passwordAgain, locale);
        validationField(PARAM_EMAIL_VALIDATION, MessageManager.EMAIL_IS_NOT_VALID, user.getEmail(), locale);
        validationField(PARAM_NAME_VALIDATION,MessageManager.NAME_IS_NOT_VALID, user.getName(), locale);
        validationField(PARAM_NAME_VALIDATION, MessageManager.SURNAME_IS_NOT_VALID, user.getSurname(), locale);
        validationField(PARAM_NAME_VALIDATION, MessageManager.PATRONYMIC_IS_NOT_VALID, user.getPatronymic(), locale);
        validationField(PARAM_PHONE_VALIDATION, MessageManager.PHONE_NUMBER_IS_NOT_VALID, user.getPhoneNumber(), locale);
        validationField(PARAM_ADDRESS_VALIDATION, MessageManager.ADDRESS_IS_NOT_VALID, user.getAddress(), locale);
    }

    /**
     * Method validationPassword ...
     *
     * @param password of type String
     * @param passwordAgain of type String
     * @param locale of type String
     * @throws LogicException when
     */
    private static void validationPassword(String password, String passwordAgain, String locale) throws LogicException {
        if (!password.equals(passwordAgain) && Pattern.matches(PARAM_PASSWORD_VALIDATION, password)) {
            throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(
                    MessageManager.PASSWORD_IS_NOT_VALID));
        }
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

    /**
     * Method checkEmail ...
     *
     * @param email of type String
     * @return boolean
     * @throws DAOException when
     */
    private static boolean checkEmail(String email) throws DAOException {
        ArrayList<String> emailsList = UserDAO.getInstance().findAllEmail();
        return !emailsList.contains(email.trim());
    }
}
