package by.bsu.javacodeparser.parser;


import by.bsu.javacodeparser.entity.CompositeType;

public class FieldHandler extends AbstractHandler {
    private static FieldHandler fieldParser = new FieldHandler(CompositeType.FIELD);

    public FieldHandler(CompositeType type) {
        super(type);
    }

    public static FieldHandler getFieldParser() {
        return fieldParser;
    }

    @Override
    public void parse(String text) {
        setSuccessor(OperatorHandler.getOperatorParser());
        element.add(successor.chain(text.trim()));
    }
}
