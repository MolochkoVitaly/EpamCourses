package by.bsu.authorization.manager;


import java.util.ResourceBundle;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "config";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String CHOICE_PARSER_PAGE_PATH = "CHOICE_PARSER_PAGE_PATH";
    public static final String XML_PARSE_RESULT_PAGE_PATH = "XML_PARSE_RESULT_PAGE_PATH";

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String)resourceBundle.getObject(key);
    }
}
