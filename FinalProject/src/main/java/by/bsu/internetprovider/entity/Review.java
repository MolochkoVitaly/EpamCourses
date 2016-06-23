package by.bsu.internetprovider.entity;



/**
 * Class Review ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class Review extends Entity {
    /** Field user  */
    private User user;

    /** Field text  */
    private String text;

    /** Field date  */
    private String date;

    /**
     * Constructor Review creates a new Review instance.
     */
    public Review() {
        user = new User();
    }

    /**
     * Method getUser returns the user of this Review object.
     *
     *
     *
     * @return the user (type User) of this Review object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Method setUser sets the user of this Review object.
     *
     *
     *
     * @param user the user of this Review object.
     *
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method getText returns the text of this Review object.
     *
     *
     *
     * @return the text (type String) of this Review object.
     */
    public String getText() {
        return text;
    }

    /**
     * Method setText sets the text of this Review object.
     *
     *
     *
     * @param text the text of this Review object.
     *
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Method getDate returns the date of this Review object.
     *
     *
     *
     * @return the date (type String) of this Review object.
     */
    public String getDate() {
        return date;
    }

    /**
     * Method setDate sets the date of this Review object.
     *
     *
     *
     * @param date the date of this Review object.
     *
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Method toString ...
     * @return String
     */
    @Override
    public String toString() {
        return "{\"id\":\"" + getId() + "\", \"userName\":\"" + user.getName() + "\", \"userSurname\":\"" + user.getSurname() +
                "\", \"text\":\"" + text + "\", \"date\":\"" + date + "\"}";
    }
}
