package by.bsu.javacodeparser.parser;


import by.bsu.javacodeparser.entity.CompositeType;
import by.bsu.javacodeparser.util.RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFileHandler extends AbstractHandler {
    private static JavaFileHandler fileParser = new JavaFileHandler(CompositeType.FILE);

    public JavaFileHandler(CompositeType type) {
        super(type);
    }

    public static JavaFileHandler getFileParser() {
        return fileParser;
    }

    @Override
    public void parse(String text) {
        Pattern filePattern = Pattern.compile(RegEx.CLASS + "|" + RegEx.PACKAGE + "|" + RegEx.IMPORT);
        Pattern importPackagePattern = Pattern.compile(RegEx.PACKAGE + "|" + RegEx.IMPORT);
        Matcher fileMatcher = filePattern.matcher(text);
        while (fileMatcher.find()) {
            String group = fileMatcher.group();
            Matcher matcher = importPackagePattern.matcher(group.trim());
            if (matcher.matches()) {
                setSuccessor(PackageAndImportHandler.getPackageImportParser());
                element.add(successor.chain(matcher.group().trim()));
            }
            else {
                setSuccessor(ClassHandler.getClassParser());
                element.add(successor.chain(group.trim()));
            }
        }
    }
}
