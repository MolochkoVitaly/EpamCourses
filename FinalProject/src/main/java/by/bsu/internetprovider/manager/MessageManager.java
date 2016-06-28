package by.bsu.internetprovider.manager;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enum MessageManager ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public enum MessageManager {
    /** Field EN  */
    EN(ResourceBundle.getBundle("messages", new Locale("en", "EN"))),

    /** Field RU  */
    RU(ResourceBundle.getBundle("messages", new Locale("ru", "RU")));

    /** Field EN_LOCALE  */
    private static final String EN_LOCALE="en_EN";

    /** Field RU_LOCALE  */
    private static final String RU_LOCALE="ru_RU";

    /** Field resourceBundle  */
    private ResourceBundle resourceBundle;

    /** Field LOGIN_IS_NOT_VALID  */
    public static final String LOGIN_IS_NOT_VALID = "login.is.not.valid";

    /** Field PASSWORD_IS_NOT_VALID  */
    public static final String PASSWORD_IS_NOT_VALID = "password.is.not.valid";

    /** Field EMAIL_IS_NOT_VALID  */
    public static final String EMAIL_IS_NOT_VALID = "email.is.not.valid";

    /** Field NAME_IS_NOT_VALID  */
    public static final String NAME_IS_NOT_VALID = "name.is.not.valid";

    /** Field SURNAME_IS_NOT_VALID  */
    public static final String SURNAME_IS_NOT_VALID = "surname.is.not.valid";

    /** Field PATRONYMIC_IS_NOT_VALID  */
    public static final String PATRONYMIC_IS_NOT_VALID = "patronymic.is.not.valid";

    /** Field PHONE_NUMBER_IS_NOT_VALID  */
    public static final String PHONE_NUMBER_IS_NOT_VALID = "phone.number.is.not.valid";

    /** Field ADDRESS_IS_NOT_VALID  */
    public static final String ADDRESS_IS_NOT_VALID = "address.is.not.valid";

    /** Field REGISTRATION_WAS_SUCCESSFUL_MESSAGE  */
    public static final String REGISTRATION_WAS_SUCCESSFUL_MESSAGE = "registration.was.successful";

    /** Field REGISTRATION_WAS_INTERRUPT_MESSAGE  */
    public static final String REGISTRATION_WAS_INTERRUPT_MESSAGE = "registration.was.interrupt";

    /** Field LOGIC_EXCEPTION_ERROR_MESSAGE  */
    public static final String LOGIC_EXCEPTION_ERROR_MESSAGE = "logic.exception.error.message";

    /** Field LOGIN_ERROR_MESSAGE  */
    public static final String LOGIN_ERROR_MESSAGE = "login.error.message";

    /** Field LOGIN_BANNED_MESSAGE  */
    public static final String LOGIN_BANNED_MESSAGE = "login.banned.message";

    /** Field EDIT_EMAIL_IS_USED  */
    public static final String EDIT_EMAIL_IS_USED = "edit.email.is.used";

    /** Field EDIT_PHONE_IS_USED  */
    public static final String EDIT_PHONE_IS_USED = "edit.phone.is.used";

    /** Field SUCCESS_SUBSCRIBE  */
    public static final String SUCCESS_SUBSCRIBE = "tariff.subscribe.success";

    /** Field ERROR_SUBSCRIBE  */
    public static final String ERROR_SUBSCRIBE = "tariff.subscribe.error";

    /** Field INSUFFICIENT_MONEY  */
    public static final String INSUFFICIENT_MONEY = "tariff.subscribe.insufficient-money";

    /** Field SUCCESS_PAYMENT  */
    public static final String SUCCESS_PAYMENT = "office.payment.success";

    /** Field ERROR_PAYMENT  */
    public static final String ERROR_PAYMENT = "office.payment.error";

    /** Field SUCCESS_EDIT_TARIFF  */
    public static final String SUCCESS_EDIT_TARIFF = "tariff.edit.successful";

    /** Field ERROR_EDIT_TARIFF  */
    public static final String ERROR_EDIT_TARIFF = "tariff.edit.error";

    /** Field SUCCESS_ADD_TARIFF  */
    public static final String SUCCESS_ADD_TARIFF = "tariff.add.successful";

    /** Field ERROR_ADD_TARIFF  */
    public static final String ERROR_ADD_TARIFF = "tariff.add.error";

    /** Field ERROR_ADD_REVIEW  */
    public static final String ERROR_ADD_REVIEW = "review.add.error";

    /** Field SUCCESS_ADD_REVIEW  */
    public static final String SUCCESS_ADD_REVIEW = "review.add.success";


    /** Field ERROR_FIND_TARIFFS  */
    public static final String ERROR_FIND_TARIFFS = "tariffs.find.error";

    /** Field ERROR_UPDATE_REVIEWS  */
    public static final String ERROR_UPDATE_REVIEWS = "reviews.update.error";

    /** Field SUCCESS_UPDATE_REVIEWS  */
    public static final String SUCCESS_UPDATE_REVIEWS = "reviews.update.success";

    /** Field TO_ARCHIVE_SUCCESS  */
    public static final String TO_ARCHIVE_SUCCESS = "tariff.to.archive.successful";

    /** Field TO_ARCHIVE_ERROR  */
    public static final String TO_ARCHIVE_ERROR = "tariff.to.archive.error";

    /** Field INCORRECT_SUM  */
    public static final String INCORRECT_SUM = "incorrect.sum";

    /** Field SUBSCRIBE_IS_ALREADY_IN_USE  */
    public static final String SUBSCRIBE_IS_ALREADY_IN_USE = "subscribe.is.already.in.use";

    /** Field UNSUPPORTED_COMMAND  */
    public static final String UNSUPPORTED_COMMAND = "command.unsupported";

    /** Field EMAIL_NOT_FOUND  */
    public static final String EMAIL_NOT_FOUND = "email.not.found";

    /** Field PHONE_NOT_FOUND  */
    public static final String PHONE_NOT_FOUND = "phone.not.fount";

    /** Field SUCCESS_CHANGE_PASSWORD  */
    public static final String SUCCESS_CHANGE_PASSWORD = "change.password";


    /** Field SUCCESS_CHANGE_PERSONAL_DATA  */
    public static final String SUCCESS_CHANGE_PERSONAL_DATA = "change.personal-data";

    /** Field SUCCESS_RESTORE_PASSWORD  */
    public static final String SUCCESS_RESTORE_PASSWORD = "restore.password";

    /** Field LOGIN_IS_NOT_VALID  */
    public static final String TARIFF_NAME_IS_NOT_VALID = "tariff.name.is.not.valid";

    /** Field PASSWORD_IS_NOT_VALID  */
    public static final String TARIFF_DESCRIPTION_IS_NOT_VALID = "tariff.description.is.not.valid";

    /** Field EMAIL_IS_NOT_VALID  */
    public static final String UPLOAD_IS_NOT_VALID = "upload.is.not.valid";

    /** Field NAME_IS_NOT_VALID  */
    public static final String DOWNLOAD_IS_NOT_VALID = "download.is.not.valid";

    /** Field SURNAME_IS_NOT_VALID  */
    public static final String PRICE_IS_NOT_VALID = "price.is.not.valid";

    /** Field PATRONYMIC_IS_NOT_VALID  */
    public static final String VOLUME_IS_NOT_VALID = "volume.is.not.valid";

    /** Field PASSWORDS_NOT_EQUALS  */
    public static final String PASSWORDS_NOT_EQUALS = "passwords.not.equals";

    /**
     * Constructor MessageManager creates a new MessageManager instance.
     *
     * @param resourceBundle of type ResourceBundle
     */
    MessageManager(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    /**
     * Method getManagerByLocale ...
     *
     * @param locale of type String
     * @return MessageManager
     */
    public static MessageManager getManagerByLocale(String locale) {
        MessageManager messageManager = null;
        if (locale != null) {
            switch (locale.trim()) {
                case EN_LOCALE:
                    messageManager =  MessageManager.EN;
                    break;
                case RU_LOCALE:
                    messageManager = MessageManager.RU;
                    break;
                default:
                    messageManager = MessageManager.RU;
            }
        } else {
            messageManager = MessageManager.RU;
        }
        return messageManager;
    }

    /**
     * Method getProperty ...
     *
     * @param key of type String
     * @return String
     */
    public String getProperty(String key) {
        return (String)resourceBundle.getObject(key);
    }
}
