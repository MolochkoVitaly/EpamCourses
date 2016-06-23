package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.UserDAO;
import by.bsu.internetprovider.encryption.MD5;
import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.ConfigurationManager;
import by.bsu.internetprovider.manager.MessageManager;
import by.bsu.internetprovider.notification.mail.WrapperNotificationMail;
import by.bsu.internetprovider.notification.sms.WrapperNotificationSMS;
import com.twilio.sdk.TwilioRestException;

import javax.mail.MessagingException;

/**
 * Class RestorePasswordLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class RestorePasswordLogic {
    /** Field DEFAULT_PASSWORD  */
    private static final String DEFAULT_PASSWORD = "nI65Epj84Wf";

    /**
     * Method restoreBySMS ...
     *
     * @param phoneNumber of type String
     * @param locale of type String
     * @throws LogicException when
     * @throws TechnicalException when
     */
    public static void restoreBySMS(String phoneNumber, String locale) throws LogicException, TechnicalException {
        try {
            if (isContainSms(Role.ADMIN, phoneNumber)){
                smsNotification(Role.ADMIN, phoneNumber);
            } else if (isContainSms(Role.CLIENT, phoneNumber)) {
                smsNotification(Role.CLIENT, phoneNumber);
            } else {
                throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(
                        MessageManager.PHONE_NOT_FOUND));
            }
        } catch (TwilioRestException e) {
            throw new TechnicalException("MessagingException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }
    }

    /**
     * Method restoreByEmail ...
     *
     * @param email of type String
     * @param locale of type String
     * @throws TechnicalException when
     * @throws LogicException when
     */
    public static void restoreByEmail(String email, String locale) throws TechnicalException, LogicException {
        try {
            if (isContainEmail(Role.ADMIN, email)){
                emailNotification(Role.ADMIN, email);
            } else if (isContainEmail(Role.CLIENT, email)) {
                emailNotification(Role.CLIENT, email);
            } else {
                throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(
                        MessageManager.EMAIL_NOT_FOUND));
            }
        } catch (MessagingException e) {
            throw new TechnicalException("MessagingException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }
    }

    /**
     * Method isContainEmail ...
     *
     * @param role of type Role
     * @param email of type String
     * @return boolean
     * @throws DAOException when
     */
    private static boolean isContainEmail(Role role, String email) throws DAOException {
        return email.equals(UserDAO.getInstance().findByEmail(role, email));
    }

    /**
     * Method isContainSms ...
     *
     * @param role of type Role
     * @param mobilePhone of type String
     * @return boolean
     * @throws DAOException when
     */
    private static boolean isContainSms(Role role, String mobilePhone) throws DAOException {
        return mobilePhone.equals(UserDAO.getInstance().findByMobilePhone(role, mobilePhone));
    }

    /**
     * Method emailNotification ...
     *
     * @param role of type Role
     * @param email of type String
     * @throws DAOException when
     * @throws MessagingException when
     */
    private static void emailNotification(Role role, String email) throws DAOException, MessagingException {
        new WrapperNotificationMail().send(email, "This is your new password - " + DEFAULT_PASSWORD + ". \n " +
                "We recommend that you change your password after the first login" + "\n " +
                "EpamTelecom team.");
        UserDAO.getInstance().recoveryUserPassword(role, email, MD5.encipherPassword(DEFAULT_PASSWORD));
    }

    /**
     * Method smsNotification ...
     *
     * @param role of type Role
     * @param mobilePhone of type String
     * @throws DAOException when
     * @throws TwilioRestException when
     */
    private static void smsNotification(Role role, String mobilePhone) throws DAOException, TwilioRestException {
        new WrapperNotificationSMS().send(mobilePhone, "This is your new password - " + DEFAULT_PASSWORD + ". \n " +
                "We recommend that you change your password after the first login" + "\n " +
                "EpamTelecom team.");
        UserDAO.getInstance().recoveryUserPassword(role, mobilePhone, MD5.encipherPassword(DEFAULT_PASSWORD));
    }
}
