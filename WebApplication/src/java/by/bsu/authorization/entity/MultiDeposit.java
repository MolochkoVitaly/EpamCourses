package by.bsu.authorization.entity;


public class MultiDeposit extends Deposit {
    private String typeOfCurrency;

    public String getTypeOfCurrency() {
        if (typeOfCurrency == null) {
            return "USD";
        }
        return typeOfCurrency;
    }

    public void setTypeOfCurrency(String typeOfCurrency) {
        this.typeOfCurrency = typeOfCurrency;
    }

    @Override
    public String toString() {
        return "\n" + "MultiDeposit: " + "\n" + "typeOfCurrency: " + typeOfCurrency + "\n" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MultiDeposit that = (MultiDeposit) o;

        return typeOfCurrency != null ? typeOfCurrency.equals(that.typeOfCurrency) : that.typeOfCurrency == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (typeOfCurrency != null ? typeOfCurrency.hashCode() : 0);
        return result;
    }
}