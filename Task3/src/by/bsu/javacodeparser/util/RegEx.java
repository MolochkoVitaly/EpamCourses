package by.bsu.javacodeparser.util;

public class RegEx {
    public static final String PACKAGE = "package\\s+.+";
    public static final String IMPORT = "import\\s+.+";
    public static final String CLASS = "(public\\s)?(static\\s)?(abstract\\s)?(final\\s)?(class\\s|interface\\s|@interface\\s|enum\\s)" +
            "[A-z<,>\\s]+(\\s+extends\\s+[A-z]+)?(\\s+implements\\s+[A-z,\\s]+)?\\s+" +
            "([{]\\n(.+\\n\\s+)+}\\n[}]|[{]\\n?[}]|[{]\\n(.+\\n)+[}])";
    public static final String FIELD = "[A-z\\s=._0-9(\"\")<>]+;";
    public static final String BLOCK = "\\s+[^A-z](static\\s+)?\\{[^\\{\\}]*\\}";
    public static final String METHOD = "(@Override\\s)?(public\\s|private\\s|protected\\s)?" +
            "(abstract\\s|static\\s|final\\s|synchronized\\s|native\\s){0,}" +
            "(void\\s|int\\s|float\\s|double\\s|short\\s|byte\\s|boolean\\s|long\\s|String\\s|[A-z]+\\s)" +
            "[A-z]+[(]([A-z,\\s]+)?[)](\\s+throws [A-z,\\s]+)?\\s+[{][\\s\\nA-z_:=();.!?]+[}]|" +
            "(@Override\\s)?(public\\s|private\\s|protected\\s)?(abstract\\s|static\\s|final\\s|synchronized\\s|native\\s){0,}" +
            "(void\\s|int\\s|float\\s|double\\s|short\\s|byte\\s|boolean\\s|long\\s|String\\s|[A-z]+\\s)" +
            "[A-z]+[(]([A-z,\\s]+)?[)](\\s+throws [A-z,\\s]+)?;";
    public static final String WORD = "\\S+";
}


