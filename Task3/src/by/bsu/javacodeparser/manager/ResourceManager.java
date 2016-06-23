package by.bsu.javacodeparser.manager;


import java.util.ResourceBundle;

public class ResourceManager {
    private static ResourceManager instance;
    private ResourceBundle resourceBundle;

    private static final String BUNDLE_NAME = "file_name";
    public static final String TEXT_IN_FILE_NAME = "TEXT_IN_FILE_NAME";
    public static final String TEXT_OUT_FILE_NAME = "TEXT_OUT_FILE_NAME";
    public static final String SORTED_OPERATORS_OUT_FILE_NAME = "SORTED_OPERATORS_OUT_FILE_NAME";
    public static final String SORTED_LEXEMES_OUT_FILE_NAME = "SORTED_LEXEMES_OUT_FILE_NAME";
    public static final String CONVERTED_LEXEMES_OUT_FILE_NAME = "CONVERTED_LEXEMES_OUT_FILE_NAME";
    public static final String DIR_NAME = "DIR_NAME";
    public static final String FILE_WITH_KEYS = "FILE_WITH_KEYS";


    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String)resourceBundle.getObject(key);
    }
}
