package by.bsu.javacodeparser.parser;


import by.bsu.javacodeparser.entity.CompositeType;
import by.bsu.javacodeparser.util.RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassHandler extends AbstractHandler {
    private static ClassHandler classParser = new ClassHandler(CompositeType.CLASS);

    public ClassHandler(CompositeType type) {
        super(type);
    }

    public static ClassHandler getClassParser() {
        return classParser;
    }

    @Override
    public void parse(String text) {
        String classSignature = text.substring(0, text.indexOf('{'));
        setSuccessor(OperatorHandler.getOperatorParser());
        element.add(successor.chain(classSignature.trim()));

        element.add(successor.chain("{"));

        text = text.substring(text.indexOf('{') + 1);
        String fieldText = text;
        Pattern pattern = Pattern.compile(RegEx.METHOD + "|" + RegEx.BLOCK);
        Matcher matcher = pattern.matcher(fieldText.trim());
        while (matcher.find()) {
            String group = matcher.group();
            fieldText = fieldText.replace(group, "");
        }
        Pattern fieldPattern = Pattern.compile(RegEx.FIELD);
        Matcher fieldMatcher = fieldPattern.matcher(fieldText.trim());
        setSuccessor(FieldHandler.getFieldParser());
        while (fieldMatcher.find()) {
            String field = fieldMatcher.group();
            element.add(successor.chain(field.trim()));
        }

        String blockText = text;
        pattern = Pattern.compile(RegEx.METHOD);
        matcher = pattern.matcher(blockText.trim());
        while (matcher.find()) {
            String group = matcher.group();
            blockText = blockText.replace(group, "");
        }
        Pattern blockPattern = Pattern.compile(RegEx.BLOCK);
        Matcher blockMatcher = blockPattern.matcher(blockText.trim());
        setSuccessor(BlockHandler.getBlockParser());
        while (blockMatcher.find()) {
            String block = blockMatcher.group();
            //block_text = block_text.replace(block, "");
            element.add(successor.chain(block.trim()));
        }

        Pattern methodPattern = Pattern.compile(RegEx.METHOD);
        Matcher methodMatcher = methodPattern.matcher(text.trim());
        setSuccessor(MethodHandler.getMethodParser());
        while (methodMatcher.find()) {
            String method = methodMatcher.group();
            text = text.replace(method, "");
            element.add(successor.chain(method.trim()));
        }

        setSuccessor(OperatorHandler.getOperatorParser());
        element.add(successor.chain("}"));
    }
}
