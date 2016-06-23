package by.bsu.internetprovider.manager;


import java.util.ResourceBundle;

/**
 * Class ConfigurationManager ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ConfigurationManager {
    /** Field instance  */
    private static ConfigurationManager instance;

    /** Field resourceBundle  */
    private ResourceBundle resourceBundle;

    /** Field BUNDLE_NAME  */
    private static final String BUNDLE_NAME = "config";

    /** Field ERROR_PAGE_PATH  */
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";

    /** Field LOGIN_PAGE_PATH  */
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";

    /** Field REGISTRATION_PAGE_PATH  */
    public static final String REGISTRATION_PAGE_PATH = "REGISTRATION_PAGE_PATH";

    /** Field MAIN_PAGE_PATH  */
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";

    /** Field USER_PAGE_PATH  */
    public static final String USER_PAGE_PATH = "USER_PAGE_PATH";

    /** Field USER_OFFICE_PAGE_PATH  */
    public static final String USER_OFFICE_PAGE_PATH = "USER_OFFICE_PAGE_PATH";

    /** Field ADMIN_OFFICE_PAGE_PATH  */
    public static final String ADMIN_OFFICE_PAGE_PATH = "ADMIN_OFFICE_PAGE_PATH";

    /** Field TARIFFS_PAGE_PATH  */
    public static final String TARIFFS_PAGE_PATH = "TARIFFS_PAGE_PATH";

    /** Field REVIEWS_PAGE_PATH  */
    public static final String REVIEWS_PAGE_PATH = "REVIEWS_PAGE_PATH";

    /** Field CONTACTS_PAGE_PATH  */
    public static final String CONTACTS_PAGE_PATH = "CONTACTS_PAGE_PATH";

    /** Field ABOUT_US_PAGE_PATH  */
    public static final String ABOUT_US_PAGE_PATH = "ABOUT_US_PAGE_PATH";


    /** Field DATABASE_URL  */
    public static final String DATABASE_URL ="database.url";

    /** Field LOGIN  */
    public static final String LOGIN ="database.login";

    /** Field PASSWORD  */
    public static final String PASSWORD ="database.password";

    /** Field POOL_SIZE  */
    public static final String POOL_SIZE ="database.pool.size";


    /**
     * Method getInstance returns the instance of this ConfigurationManager object.
     *
     *
     *
     * @return the instance (type ConfigurationManager) of this ConfigurationManager object.
     */
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
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
