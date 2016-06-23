package by.bsu.authorization.entity;


public class Deposit {
    private Bank bank;
    private String id;
    private String depositor;
    private Integer amountOnDeposit;
    private Integer profitability;
    private Integer timeConstraints;

    public Deposit() {
        this.bank = new Bank();
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public Integer getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(Integer amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public Integer getProfitability() {
        return profitability;
    }

    public void setProfitability(Integer profitability) {
        this.profitability = profitability;
    }

    public Integer getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(Integer timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public String toString() {
        return "deposit id: " + id + "\n" + bank.toString() + "depositor: " + depositor + "\n" + "amount on deposit: " + amountOnDeposit +
                "\n" + "profitability: " + profitability + "\n" + "time constraints: " + timeConstraints + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deposit deposit = (Deposit) o;

        if (bank != null ? !bank.equals(deposit.bank) : deposit.bank != null) return false;
        if (id != null ? !id.equals(deposit.id) : deposit.id != null) return false;
        if (depositor != null ? !depositor.equals(deposit.depositor) : deposit.depositor != null) return false;
        if (amountOnDeposit != null ? !amountOnDeposit.equals(deposit.amountOnDeposit) : deposit.amountOnDeposit != null)
            return false;
        if (profitability != null ? !profitability.equals(deposit.profitability) : deposit.profitability != null)
            return false;
        return timeConstraints != null ? timeConstraints.equals(deposit.timeConstraints) : deposit.timeConstraints == null;
    }

    @Override
    public int hashCode() {
        int result = bank != null ? bank.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (depositor != null ? depositor.hashCode() : 0);
        result = 31 * result + (amountOnDeposit != null ? amountOnDeposit.hashCode() : 0);
        result = 31 * result + (profitability != null ? profitability.hashCode() : 0);
        result = 31 * result + (timeConstraints != null ? timeConstraints.hashCode() : 0);
        return result;
    }
}
