package by.bsu.javacodeparser.parser;


import by.bsu.javacodeparser.entity.CompositeType;
import by.bsu.javacodeparser.util.RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodHandler extends AbstractHandler {
    private static MethodHandler methodParser = new MethodHandler(CompositeType.METHOD);

    public MethodHandler(CompositeType type) {
        super(type);
    }

    public static MethodHandler getMethodParser() {
        return methodParser;
    }

    @Override
    public void parse(String text) {
        String methodSignature = text.substring(0, text.indexOf('{'));
        setSuccessor(OperatorHandler.getOperatorParser());
        element.add(successor.chain(methodSignature));

        element.add(successor.chain("{"));

        String methodBody = text.substring(text.indexOf('{') + 1);
        Pattern fieldPattern = Pattern.compile(RegEx.FIELD);
        Matcher fieldMatcher = fieldPattern.matcher(methodBody.trim());
        setSuccessor(FieldHandler.getFieldParser());
        while (fieldMatcher.find()) {
            String field = fieldMatcher.group();
            element.add(successor.chain(field.trim()));
        }

        setSuccessor(OperatorHandler.getOperatorParser());
        element.add(successor.chain("}"));
    }
}
