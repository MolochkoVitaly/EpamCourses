package by.bsu.authorization.builder.factory;


import by.bsu.authorization.builder.AbstractDepositsBuilder;
import by.bsu.authorization.builder.DepositsDOMBuilder;
import by.bsu.authorization.builder.DepositsSAXBuilder;
import by.bsu.authorization.builder.DepositsStAXBuilder;

public class DepositsBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }

    public static AbstractDepositsBuilder createDepositsBuilder(String typeParer) {
        TypeParser type = TypeParser.valueOf(typeParer.toUpperCase());
        switch (type) {
            case DOM:
                return new DepositsDOMBuilder();
            case SAX:
                return new DepositsSAXBuilder();
            case STAX:
                return new DepositsStAXBuilder();
            default:
                throw new EnumConstantNotPresentException (type.getDeclaringClass(), type.name());
        }
    }
}
