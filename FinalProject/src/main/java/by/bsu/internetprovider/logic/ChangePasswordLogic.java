package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.UserDAO;
import by.bsu.internetprovider.encryption.MD5;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;

import java.util.regex.Pattern;

/**
 * Class ChangePasswordLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ChangePasswordLogic {
    /** Field PARAM_PASSWORD_VALIDATION  */
    private static final String PARAM_PASSWORD_VALIDATION = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{4,19}$";

    /**
     * Method change ...
     *
     * @param user of type User
     * @param newPassword of type String
     * @param locale of type String
     * @throws TechnicalException when
     * @throws LogicException when
     */
    public static void change(User user, String newPassword, String locale) throws TechnicalException, LogicException {
        try {
            validationPassword(newPassword, locale);
            UserDAO.getInstance().changeUserPassword(user, MD5.encipherPassword(newPassword));
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
    }

    /**
     * Method validationPassword ...
     *
     * @param password of type String
     * @throws LogicException when
     */
    private static void validationPassword(String password, String locale) throws LogicException {
        if (!Pattern.matches(PARAM_PASSWORD_VALIDATION, password)) {
            throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(
                    MessageManager.PASSWORD_IS_NOT_VALID));
        }
    }
}
