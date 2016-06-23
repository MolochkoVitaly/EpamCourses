package by.bsu.internetprovider.entity;


/**
 * Class Payment ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class Payment extends Entity {
    /** Field account  */
    private ClientAccount account;

    /** Field paymentDate  */
    private String paymentDate;

    /** Field paymentSum  */
    private Long paymentSum;

    /**
     * Constructor Payment creates a new Payment instance.
     */
    public Payment() {
        account = new ClientAccount();
    }

    /**
     * Method getAccount returns the account of this Payment object.
     *
     *
     *
     * @return the account (type ClientAccount) of this Payment object.
     */
    public ClientAccount getAccount() {
        return account;
    }

    /**
     * Method setAccount sets the account of this Payment object.
     *
     *
     *
     * @param account the account of this Payment object.
     *
     */
    public void setAccount(ClientAccount account) {
        this.account = account;
    }

    /**
     * Method getPaymentDate returns the paymentDate of this Payment object.
     *
     *
     *
     * @return the paymentDate (type String) of this Payment object.
     */
    public String getPaymentDate() {
        return paymentDate;
    }

    /**
     * Method setPaymentDate sets the paymentDate of this Payment object.
     *
     *
     *
     * @param paymentDate the paymentDate of this Payment object.
     *
     */
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Method getPaymentSum returns the paymentSum of this Payment object.
     *
     *
     *
     * @return the paymentSum (type Long) of this Payment object.
     */
    public Long getPaymentSum() {
        return paymentSum;
    }

    /**
     * Method setPaymentSum sets the paymentSum of this Payment object.
     *
     *
     *
     * @param paymentSum the paymentSum of this Payment object.
     *
     */
    public void setPaymentSum(Long paymentSum) {
        this.paymentSum = paymentSum;
    }
}
