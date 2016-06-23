package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.UserDAO;
import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Class EditPersonalDataLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class EditPersonalDataLogic {
    /** Field PARAM_EMAIL_VALIDATION  */
    private static final String PARAM_EMAIL_VALIDATION =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}";

    /** Field PARAM_PASSWORD_VALIDATION  */
    private static final String PARAM_PASSWORD_VALIDATION = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{4,19}$";

    /** Field PARAM_SURNAME_VALIDATION  */
    private static final String PARAM_SURNAME_VALIDATION = "^[A-ZА-ЯЁ][a-zа-яё]{2,19}$";

    /** Field PARAM_PHONE_VALIDATION  */
    private static final String PARAM_PHONE_VALIDATION = "(\\+375)(17|25|29|33|44)[0-9]{7}";

    /** Field PARAM_ADDRESS_VALIDATION  */
    private static final String PARAM_ADDRESS_VALIDATION =
            "(РБ,)(\\s)((Брестская|Витебская|Гомельская|Гродненская|Минская|Могилевская)\\s(область,))" +
            "(\\s)(г.\\s)([А-ЯЁ][а-яё]{2,})(,\\s)(ул.\\s)([А-ЯЁ][А-ЯЁа-яё\\s-]{2,})(,\\s)(дом\\s)([0-9]{1,})" +
            "((,\\s)(кв.\\s)[0-9]{1,}){0,}";


    /**
     * Method editData ...
     *
     * @param current of type User
     * @param newData of type User
     * @param locale of type String
     * @throws LogicException when
     * @throws TechnicalException when
     */
    public static void editData(User current, User newData, String locale) throws LogicException, TechnicalException {
        newData.setId(current.getId());
        if (!isOldData(current, newData)) {
            try {
                validation(newData, locale);

                if (current.getEmail().equals(newData.getEmail()) || checkEmail(newData.getEmail())) {
                    UserDAO.getInstance().editPersonalData(newData);
                    current.setSurname(newData.getSurname());
                    current.setEmail(newData.getEmail());
                    current.setPhoneNumber(newData.getPhoneNumber());
                    current.setAddress(newData.getAddress());
                }
                else {
                    throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(
                            MessageManager.EDIT_EMAIL_IS_USED));
                }
            } catch (DAOException e) {
                throw new TechnicalException(e);
            }
        }
    }

    /**
     * Method isOldData ...
     *
     * @param current of type User
     * @param newData of type User
     * @return boolean
     */
    private static boolean isOldData(User current, User newData) {
        return current.getSurname().equals(newData.getSurname()) && current.getEmail().equals(newData.getEmail()) &&
               current.getPhoneNumber().equals(newData.getPhoneNumber())&&
               current.getAddress().equals(newData.getAddress());
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

    /**
     * Method validation ...
     *
     * @param user of type User
     * @param locale of type String
     * @throws LogicException when
     */
    private static void validation(User user, String locale) throws LogicException {
        validationField(PARAM_EMAIL_VALIDATION, MessageManager.EMAIL_IS_NOT_VALID, user.getEmail(), locale);
        validationField(PARAM_SURNAME_VALIDATION, MessageManager.NAME_IS_NOT_VALID, user.getSurname(), locale);
        validationField(PARAM_PHONE_VALIDATION, MessageManager.PHONE_NUMBER_IS_NOT_VALID, user.getPhoneNumber(), locale);
        validationField(PARAM_ADDRESS_VALIDATION, MessageManager.ADDRESS_IS_NOT_VALID, user.getAddress(), locale);
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


