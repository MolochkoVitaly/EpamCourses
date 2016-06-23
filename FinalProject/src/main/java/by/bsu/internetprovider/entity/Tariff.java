package by.bsu.internetprovider.entity;


/**
 * Class Tariff ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class Tariff extends Entity {
    /** Field tariffName  */
    private String tariffName;

    /** Field description  */
    private String description;

    /** Field currencyCode  */
    private String currencyCode;

    /** Field monthPayment  */
    private Long monthPayment;

    /** Field uploadSpeed  */
    private Long uploadSpeed;

    /** Field downloadSpeed  */
    private Long downloadSpeed;

    /** Field trafficVolume  */
    private Long trafficVolume;

    /** Field inArchive  */
    private String inArchive;


    /**
     * Constructor Tariff creates a new Tariff instance.
     */
    public Tariff() {
        currencyCode = "BYR";
        inArchive = "false";
    }

    /**
     * Method getTariffName returns the tariffName of this Tariff object.
     *
     *
     *
     * @return the tariffName (type String) of this Tariff object.
     */
    public String getTariffName() {
        return tariffName;
    }

    /**
     * Method setTariffName sets the tariffName of this Tariff object.
     *
     *
     *
     * @param tariffName the tariffName of this Tariff object.
     *
     */
    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    /**
     * Method getDescription returns the description of this Tariff object.
     *
     *
     *
     * @return the description (type String) of this Tariff object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method setDescription sets the description of this Tariff object.
     *
     *
     *
     * @param description the description of this Tariff object.
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method getCurrencyCode returns the currencyCode of this Tariff object.
     *
     *
     *
     * @return the currencyCode (type String) of this Tariff object.
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Method setCurrencyCode sets the currencyCode of this Tariff object.
     *
     *
     *
     * @param currencyCode the currencyCode of this Tariff object.
     *
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Method getMonthPayment returns the monthPayment of this Tariff object.
     *
     *
     *
     * @return the monthPayment (type Long) of this Tariff object.
     */
    public Long getMonthPayment() {
        return monthPayment;
    }

    /**
     * Method setMonthPayment sets the monthPayment of this Tariff object.
     *
     *
     *
     * @param monthPayment the monthPayment of this Tariff object.
     *
     */
    public void setMonthPayment(Long monthPayment) {
        this.monthPayment = monthPayment;
    }

    /**
     * Method getUploadSpeed returns the uploadSpeed of this Tariff object.
     *
     *
     *
     * @return the uploadSpeed (type Long) of this Tariff object.
     */
    public Long getUploadSpeed() {
        return uploadSpeed;
    }

    /**
     * Method setUploadSpeed sets the uploadSpeed of this Tariff object.
     *
     *
     *
     * @param uploadSpeed the uploadSpeed of this Tariff object.
     *
     */
    public void setUploadSpeed(Long uploadSpeed) {
        this.uploadSpeed = uploadSpeed;
    }

    /**
     * Method getDownloadSpeed returns the downloadSpeed of this Tariff object.
     *
     *
     *
     * @return the downloadSpeed (type Long) of this Tariff object.
     */
    public Long getDownloadSpeed() {
        return downloadSpeed;
    }

    /**
     * Method setDownloadSpeed sets the downloadSpeed of this Tariff object.
     *
     *
     *
     * @param downloadSpeed the downloadSpeed of this Tariff object.
     *
     */
    public void setDownloadSpeed(Long downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    /**
     * Method getTrafficVolume returns the trafficVolume of this Tariff object.
     *
     *
     *
     * @return the trafficVolume (type Long) of this Tariff object.
     */
    public Long getTrafficVolume() {
        return trafficVolume;
    }

    /**
     * Method setTrafficVolume sets the trafficVolume of this Tariff object.
     *
     *
     *
     * @param trafficVolume the trafficVolume of this Tariff object.
     *
     */
    public void setTrafficVolume(Long trafficVolume) {
        this.trafficVolume = trafficVolume;
    }

    /**
     * Method getInArchive returns the inArchive of this Tariff object.
     *
     *
     *
     * @return the inArchive (type String) of this Tariff object.
     */
    public String getInArchive() {
        return inArchive;
    }

    /**
     * Method setInArchive sets the inArchive of this Tariff object.
     *
     *
     *
     * @param inArchive the inArchive of this Tariff object.
     *
     */
    public void setInArchive(String inArchive) {
        this.inArchive = inArchive;
    }

    /**
     * Method getNameAndId returns the nameAndId of this Tariff object.
     *
     *
     *
     * @return the nameAndId (type String) of this Tariff object.
     */
    public String getNameAndId() {
        return "{\"id\":\"" + getId() + "\", \"name\":\"" + tariffName + "\"}";
    }

    /**
     * Method toString ...
     * @return String
     */
    @Override
    public String toString() {
        return "{\"id\":\"" + getId() + "\", \"tariffName\":\"" + tariffName + "\", \"description\":\"" + description +
                "\", \"monthPayment\":\"" + monthPayment + "\", \"uploadSpeed\":\"" + uploadSpeed +
                "\", \"downloadSpeed\":\"" + downloadSpeed + "\", \"trafficVolume\":\"" + trafficVolume + "\"}";
    }
}
