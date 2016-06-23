package by.bsu.internetprovider.entity;


/**
 * Class AccountInfo ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class AccountInfo extends Entity {
    /** Field clientAccount  */
    private ClientAccount clientAccount;

    /** Field tariff  */
    private Tariff tariff;

    /** Field creatingDate  */
    private String creatingDate;

    /** Field endDate  */
    private String endDate;

    /** Field trafficUsed  */
    private Double trafficUsed;

    /** Field isActive  */
    private String isActive;

    /**
     * Constructor AccountInfo creates a new AccountInfo instance.
     */
    public AccountInfo() {
        clientAccount = new ClientAccount();
        tariff = new Tariff();
    }

    /**
     * Method getClientAccount returns the clientAccount of this AccountInfo object.
     *
     *
     *
     * @return the clientAccount (type ClientAccount) of this AccountInfo object.
     */
    public ClientAccount getClientAccount() {
        return clientAccount;
    }

    /**
     * Method setClientAccount sets the clientAccount of this AccountInfo object.
     *
     *
     *
     * @param clientAccount the clientAccount of this AccountInfo object.
     *
     */
    public void setClientAccount(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
    }

    /**
     * Method getTariff returns the tariff of this AccountInfo object.
     *
     *
     *
     * @return the tariff (type Tariff) of this AccountInfo object.
     */
    public Tariff getTariff() {
        return tariff;
    }

    /**
     * Method setTariff sets the tariff of this AccountInfo object.
     *
     *
     *
     * @param tariff the tariff of this AccountInfo object.
     *
     */
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    /**
     * Method getCreatingDate returns the creatingDate of this AccountInfo object.
     *
     *
     *
     * @return the creatingDate (type String) of this AccountInfo object.
     */
    public String getCreatingDate() {
        return creatingDate;
    }

    /**
     * Method setCreatingDate sets the creatingDate of this AccountInfo object.
     *
     *
     *
     * @param creatingDate the creatingDate of this AccountInfo object.
     *
     */
    public void setCreatingDate(String creatingDate) {
        this.creatingDate = creatingDate;
    }

    /**
     * Method getEndDate returns the endDate of this AccountInfo object.
     *
     *
     *
     * @return the endDate (type String) of this AccountInfo object.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Method setEndDate sets the endDate of this AccountInfo object.
     *
     *
     *
     * @param endDate the endDate of this AccountInfo object.
     *
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Method getTrafficUsed returns the trafficUsed of this AccountInfo object.
     *
     *
     *
     * @return the trafficUsed (type Double) of this AccountInfo object.
     */
    public Double getTrafficUsed() {
        return trafficUsed;
    }

    /**
     * Method setTrafficUsed sets the trafficUsed of this AccountInfo object.
     *
     *
     *
     * @param trafficUsed the trafficUsed of this AccountInfo object.
     *
     */
    public void setTrafficUsed(Double trafficUsed) {
        this.trafficUsed = trafficUsed;
    }


    /**
     * Method getIsActive returns the isActive of this AccountInfo object.
     *
     * Field isActive
     *
     * @return the isActive (type String) of this AccountInfo object.
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Method setIsActive sets the isActive of this AccountInfo object.
     *
     * Field isActive
     *
     * @param isActive the isActive of this AccountInfo object.
     *
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
