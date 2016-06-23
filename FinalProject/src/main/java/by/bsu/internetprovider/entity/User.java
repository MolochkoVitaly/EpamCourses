package by.bsu.internetprovider.entity;


/**
 * Class User ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class User extends Entity {
    /** Field email  */
    private String email;

    /** Field password  */
    private String password;

    /** Field name  */
    private String name;

    /** Field surname  */
    private String surname;

    /** Field patronymic  */
    private String patronymic;

    /** Field address  */
    private String address;

    /** Field phoneNumber  */
    private String phoneNumber;

    /** Field role  */
    private Role role;

    /**
     * Constructor User creates a new User instance.
     */
    public User() {
    }

    /**
     * Method getEmail returns the email of this User object.
     *
     *
     *
     * @return the email (type String) of this User object.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method setEmail sets the email of this User object.
     *
     *
     *
     * @param email the email of this User object.
     *
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method getPassword returns the password of this User object.
     *
     *
     *
     * @return the password (type String) of this User object.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method setPassword sets the password of this User object.
     *
     *
     *
     * @param password the password of this User object.
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method getName returns the name of this User object.
     *
     *
     *
     * @return the name (type String) of this User object.
     */
    public String getName() {
        return name;
    }

    /**
     * Method setName sets the name of this User object.
     *
     *
     *
     * @param name the name of this User object.
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method getSurname returns the surname of this User object.
     *
     *
     *
     * @return the surname (type String) of this User object.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method setSurname sets the surname of this User object.
     *
     *
     *
     * @param surname the surname of this User object.
     *
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Method getPatronymic returns the patronymic of this User object.
     *
     *
     *
     * @return the patronymic (type String) of this User object.
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Method setPatronymic sets the patronymic of this User object.
     *
     *
     *
     * @param patronymic the patronymic of this User object.
     *
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Method getAddress returns the address of this User object.
     *
     *
     *
     * @return the address (type String) of this User object.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method setAddress sets the address of this User object.
     *
     *
     *
     * @param address the address of this User object.
     *
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method getPhoneNumber returns the phoneNumber of this User object.
     *
     *
     *
     * @return the phoneNumber (type String) of this User object.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method setPhoneNumber sets the phoneNumber of this User object.
     *
     *
     *
     * @param phoneNumber the phoneNumber of this User object.
     *
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Method getRole returns the role of this User object.
     *
     *
     *
     * @return the role (type Role) of this User object.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Method setRole sets the role of this User object.
     *
     *
     *
     * @param role the role of this User object.
     *
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Method clone ...
     * @return Object
     * @throws CloneNotSupportedException when
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Method equals ...
     *
     * @param o of type Object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (patronymic != null ? !patronymic.equals(user.patronymic) : user.patronymic != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(user.phoneNumber) : user.phoneNumber != null) return false;
        return role == user.role;

    }

    /**
     * Method hashCode ...
     * @return int
     */
    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
