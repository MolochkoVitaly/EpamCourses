package by.bsu.internetprovider.entity;


/**
 * Class ClientAccount ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ClientAccount extends Entity {
    /** Field user  */
    private User user;

    /** Field creatingDate  */
    private String creatingDate;

    /** Field endDate  */
    private String endDate;

    /** Field balance  */
    private Float balance;

    /**
     * Constructor ClientAccount creates a new ClientAccount instance.
     */
    public ClientAccount() {
        this.user = new User();
        this.balance = 0F;
    }

    /**
     * Method getUser returns the user of this ClientAccount object.
     *
     *
     *
     * @return the user (type User) of this ClientAccount object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Method setUser sets the user of this ClientAccount object.
     *
     *
     *
     * @param user the user of this ClientAccount object.
     *
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method getCreatingDate returns the creatingDate of this ClientAccount object.
     *
     *
     *
     * @return the creatingDate (type String) of this ClientAccount object.
     */
    public String getCreatingDate() {
        return creatingDate;
    }

    /**
     * Method setCreatingDate sets the creatingDate of this ClientAccount object.
     *
     *
     *
     * @param creatingDate the creatingDate of this ClientAccount object.
     *
     */
    public void setCreatingDate(String creatingDate) {
        this.creatingDate = creatingDate;
    }

    /**
     * Method getEndDate returns the endDate of this ClientAccount object.
     *
     *
     *
     * @return the endDate (type String) of this ClientAccount object.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Method setEndDate sets the endDate of this ClientAccount object.
     *
     *
     *
     * @param endDate the endDate of this ClientAccount object.
     *
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Method getBalance returns the balance of this ClientAccount object.
     *
     *
     *
     * @return the balance (type Float) of this ClientAccount object.
     */
    public Float getBalance() {
        return balance;
    }

    /**
     * Method setBalance sets the balance of this ClientAccount object.
     *
     *
     *
     * @param balance the balance of this ClientAccount object.
     *
     */
    public void setBalance(Float balance) {
        this.balance = balance;
    }


}
