package by.bsu.javacodeparser.parser;


import by.bsu.javacodeparser.entity.CompositeType;
import by.bsu.javacodeparser.util.RegEx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlockHandler extends AbstractHandler {
    private static BlockHandler blockParser = new BlockHandler(CompositeType.BLOCK);

    public BlockHandler(CompositeType type) {
        super(type);
    }

    public static BlockHandler getBlockParser() {
        return blockParser;
    }

    @Override
    public void parse(String text) {
        String blockSignature = text.substring(0, text.indexOf('{'));
        setSuccessor(OperatorHandler.getOperatorParser());
        element.add(successor.chain(blockSignature));

        element.add(successor.chain("{"));

        String blockBody = text.substring(text.indexOf('{') + 1);
        Pattern blockPattern = Pattern.compile(RegEx.FIELD);
        Matcher blockMatcher = blockPattern.matcher(blockBody);
        setSuccessor(FieldHandler.getFieldParser());
        while (blockMatcher.find()) {
            String fieldBlock = blockMatcher.group();
            blockBody = blockBody.replace(fieldBlock, "");
            element.add(successor.chain(fieldBlock.trim()));
        }
        setSuccessor(OperatorHandler.getOperatorParser());
        element.add(successor.chain("}"));
    }
}
