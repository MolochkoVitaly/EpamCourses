package by.bsu.authorization.entity;


public enum DepositEnum {
    DEPOSITS("deposits"),
    MULTI_DEPOSIT("multi-deposit"),
    ESTIMATED_DEPOSIT("estimated-deposit"),
    BANK("bank"),
    ACCOUNT_ID("account-id"),
    NAME("name"),
    COUNTRY("country"),
    DEPOSITOR("depositor"),
    AMOUNT_ON_DEPOSIT("amount-on-deposit"),
    PROFITABILITY("profitability"),
    TIME_CONSTRAINTS("time-constraints"),
    MIN_BALANCE("min-balance"),
    TYPE_OF_CURRENCY("type-of-currency");

    private String value;

    private DepositEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
