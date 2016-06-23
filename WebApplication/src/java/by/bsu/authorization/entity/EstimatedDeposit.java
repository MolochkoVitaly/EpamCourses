package by.bsu.authorization.entity;


public class EstimatedDeposit extends Deposit {
    private Integer minBalance;

    public Integer getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(Integer minBalance) {
        this.minBalance = minBalance;
    }

    @Override
    public String toString() {
        return "\n" + "EstimatedDeposit: " + "\n" + super.toString() + "Min balance: " + minBalance + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EstimatedDeposit that = (EstimatedDeposit) o;

        return minBalance != null ? minBalance.equals(that.minBalance) : that.minBalance == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (minBalance != null ? minBalance.hashCode() : 0);
        return result;
    }
}
