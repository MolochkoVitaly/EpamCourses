package by.bsu.internetprovider.dao.impl;

import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.dao.IUserDAO;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.pool.ConnectionPool;
import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.pool.ProxyConnection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Class UserDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class UserDAO implements IUserDAO {
    /** Field LOG  */
    private static final Logger LOG = LogManager.getLogger(UserDAO.class);

    /** Field instance  */
    private static UserDAO instance = UserDAO.getInstance();

    /** Field PARAM_CLIENT_ID  */
    private static final String PARAM_CLIENT_ID = "client_id";

    /** Field PARAM_ADMIN_ID  */
    private static final String PARAM_ADMIN_ID = "admin_id";

    /** Field PARAM_EMAIL  */
    private static final String PARAM_EMAIL = "email";

    /** Field PARAM_PASSWORD  */
    private static final String PARAM_PASSWORD = "password";

    /** Field PARAM_FIRST_NAME  */
    private static final String PARAM_FIRST_NAME = "first_name";

    /** Field PARAM_LAST_NAME  */
    private static final String PARAM_LAST_NAME = "last_name";

    /** Field PARAM_PATRONYMIC  */
    private static final String PARAM_PATRONYMIC = "patronymic";

    /** Field PARAM_PHONE_NUMBER  */
    private static final String PARAM_PHONE_NUMBER = "phone_number";

    /** Field PARAM_ADDRESS  */
    private static final String PARAM_ADDRESS = "address";


    /** Field INSERT_USER  */
    private static final String INSERT_USER =
            "INSERT INTO clients (last_name, first_name, patronymic, `email`, `password`, phone_number, address )" +
            " VALUES(?, ?, ?, ?, ?, ?, ?)";

    /** Field FIND_BY_ID  */
    private static final String FIND_BY_ID =
            "SELECT client_id, last_name, first_name, patronymic, `email`, `password`, phone_number, address" +
             " FROM clients WHERE client_id = ?";

    /** Field FIND_USER_BY_LOGIN_PASSWORD_IN_CLIENTS  */
    private static final String FIND_USER_BY_LOGIN_PASSWORD_IN_CLIENTS =
            "SELECT client_id, last_name, first_name, patronymic, `email`, `password`, phone_number, address" +
            " FROM clients WHERE email = ? AND password = ?";

    /** Field FIND_USER_BY_LOGIN_PASSWORD_IN_ADMINS  */
    private static final String FIND_USER_BY_LOGIN_PASSWORD_IN_ADMINS =
            "SELECT admin_id, last_name, first_name, patronymic, `email`, `password`, phone_number, address" +
             " FROM administrators WHERE email = ? AND password = ?";

    /** Field CHANGE_USER_PASSWORD  */
    private static final String CHANGE_USER_PASSWORD = "UPDATE clients SET password = ? WHERE client_id = ?";

    /** Field CHANGE_ADMIN_PASSWORD  */
    private static final String CHANGE_ADMIN_PASSWORD = "UPDATE administrators SET password = ? WHERE admin_id = ?";

    /** Field RECOVERY_USER_PASSWORD  */
    private static final String RECOVERY_USER_PASSWORD = "UPDATE clients SET password = ? WHERE email = ?";

    /** Field RECOVERY_ADMIN_PASSWORD  */
    private static final String RECOVERY_ADMIN_PASSWORD = "UPDATE administrators SET password = ? WHERE email = ?";

    /** Field EDIT_USER_PERSONAL_DATA  */
    private static final String EDIT_USER_PERSONAL_DATA =
            "UPDATE clients SET last_name = ?, email = ?, phone_number = ?, " +
            " address = ? WHERE client_id = ?";

    /** Field EDIT_ADMIN_PERSONAL_DATA  */
    private static final String EDIT_ADMIN_PERSONAL_DATA =
            "UPDATE administrators SET last_name = ?, email = ?, phone_number = ?, " +
            " address = ? WHERE admin_id = ?";

    /** Field FIND_ALL_PHONES  */
    private static final String FIND_ALL_PHONES =
            "SELECT clients.phone_number FROM clients UNION SELECT administrators.phone_number FROM administrators";

    /** Field FIND_ALL_EMAIL  */
    private static final String FIND_ALL_EMAIL =
            "SELECT clients.email FROM clients UNION SELECT administrators.email FROM administrators";

    /** Field FIND_CLIENT_EMAILS  */
    private static final String FIND_CLIENT_EMAILS =
            "SELECT email FROM clients WHERE email = ?";

    /** Field FIND_ADMIN_EMAILS  */
    private static final String FIND_ADMIN_EMAILS =
            "SELECT email FROM administrators WHERE email = ?";

    /** Field FIND_CLIENT_PHONES  */
    private static final String FIND_CLIENT_PHONES =
            "SELECT phone_number FROM clients WHERE phone_number = ?";

    /** Field FIND_ADMIN_PHONES  */
    private static final String FIND_ADMIN_PHONES =
            "SELECT phone_number FROM administrators WHERE phone_number = ?";

    /**
     * Constructor UserDAO creates a new UserDAO instance.
     */
    private UserDAO() {
    }

    /**
     * Method getInstance returns the instance of this UserDAO object.
     *
     *
     *
     * @return the instance (type UserDAO) of this UserDAO object.
     */
    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }


    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return User
     * @throws DAOException when
     * @see IUserDAO#findById(Long)
     */
    @Override
    public User findById(Long id) throws DAOException {
        User user = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_BY_ID);
        ) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(Long.parseLong(resultSet.getString(PARAM_CLIENT_ID)));
                user.setName(resultSet.getString(PARAM_FIRST_NAME));
                user.setSurname(resultSet.getString(PARAM_LAST_NAME));
                user.setPatronymic(resultSet.getString(PARAM_PATRONYMIC));
                user.setEmail(resultSet.getString(PARAM_EMAIL));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setPhoneNumber(resultSet.getString(PARAM_PHONE_NUMBER));
                user.setAddress(resultSet.getString(PARAM_ADDRESS));
                user.setRole(Role.CLIENT);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return user;
    }

    /**
     * Method add ...
     *
     * @param entity of type User
     * @return boolean
     * @throws DAOException when
     * @see IUserDAO#add(User)
     */
    @Override
    public boolean add(User entity) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(INSERT_USER)
        ) {
            statement.setString(1, entity.getSurname());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getPatronymic());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getPassword());
            statement.setString(6, entity.getPhoneNumber());
            statement.setString(7, entity.getAddress());
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Method findByLoginPasswordInClients ...
     *
     * @param login of type String
     * @param password of type String
     * @return User
     * @throws DAOException when
     */
    public User findByLoginPasswordInClients(String login, String password) throws DAOException {
        User user = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_USER_BY_LOGIN_PASSWORD_IN_CLIENTS);
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(Long.parseLong(resultSet.getString(PARAM_CLIENT_ID)));
                user.setName(resultSet.getString(PARAM_FIRST_NAME));
                user.setSurname(resultSet.getString(PARAM_LAST_NAME));
                user.setPatronymic(resultSet.getString(PARAM_PATRONYMIC));
                user.setEmail(resultSet.getString(PARAM_EMAIL));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setPhoneNumber(resultSet.getString(PARAM_PHONE_NUMBER));
                user.setAddress(resultSet.getString(PARAM_ADDRESS));
                user.setRole(Role.CLIENT);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return user;
    }

    /**
     * Method findByLoginPasswordInAdministrators ...
     *
     * @param login of type String
     * @param password of type String
     * @return User
     * @throws DAOException when
     */
    public User findByLoginPasswordInAdministrators(String login, String password) throws DAOException {
        User user = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_USER_BY_LOGIN_PASSWORD_IN_ADMINS);
        ) {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(Long.parseLong(resultSet.getString(PARAM_ADMIN_ID)));
                user.setName(resultSet.getString(PARAM_FIRST_NAME));
                user.setSurname(resultSet.getString(PARAM_LAST_NAME));
                user.setPatronymic(resultSet.getString(PARAM_PATRONYMIC));
                user.setEmail(resultSet.getString(PARAM_EMAIL));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setPhoneNumber(resultSet.getString(PARAM_PHONE_NUMBER));
                user.setAddress(resultSet.getString(PARAM_ADDRESS));
                user.setRole(Role.ADMIN);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return user;
    }

    /**
     * Method changeUserPassword ...
     *
     * @param user of type User
     * @param newPassword of type String
     * @return boolean
     * @throws DAOException when
     */
    public boolean changeUserPassword(User user, String newPassword) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(
                     user.getRole() == Role.ADMIN ? CHANGE_ADMIN_PASSWORD : CHANGE_USER_PASSWORD)
        ) {
            statement.setString(1, newPassword);
            statement.setLong(2, user.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Method recoveryUserPassword ...
     *
     * @param role of type Role
     * @param email of type String
     * @param newPassword of type String
     * @return boolean
     * @throws DAOException when
     */
    public boolean recoveryUserPassword(Role role, String email, String newPassword) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(
                     role == Role.ADMIN ? RECOVERY_ADMIN_PASSWORD : RECOVERY_USER_PASSWORD)
        ) {
            statement.setString(1, newPassword);
            statement.setString(2, email);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Method editPersonalData ...
     *
     * @param user of type User
     * @return boolean
     * @throws DAOException when
     */
    public boolean editPersonalData(User user) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(
                     user.getRole() == Role.ADMIN ? EDIT_ADMIN_PERSONAL_DATA : EDIT_USER_PERSONAL_DATA)
        ) {
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAddress());
            statement.setLong(5, user.getId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

    /**
     * Method findAllEmail ...
     * @return ArrayList<String>
     * @throws DAOException when
     */
    public ArrayList<String> findAllEmail() throws DAOException {
        ArrayList<String> emailsList = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_ALL_EMAIL)
        ) {
            emailsList = new ArrayList<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                emailsList.add(resultSet.getString(PARAM_EMAIL));
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return emailsList;
    }

    /**
     * Method findAllPhones ...
     * @return ArrayList<String>
     * @throws DAOException when
     */
    public ArrayList<String> findAllPhones() throws DAOException {
        ArrayList<String> phonesList = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_ALL_PHONES)
        ) {
            phonesList = new ArrayList<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                phonesList.add(resultSet.getString(PARAM_PHONE_NUMBER));
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return phonesList;
    }



    /**
     * Method findByEmail ...
     *
     * @param role of type Role
     * @param email of type String
     * @return String
     * @throws DAOException when
     */
    public String findByEmail(Role role, String email) throws DAOException {
        String result = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(
                     role == Role.ADMIN ? FIND_ADMIN_EMAILS :FIND_CLIENT_EMAILS)
        ) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getString(PARAM_EMAIL);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return result;

    }

    /**
     * Method findByMobilePhone ...
     *
     * @param role of type Role
     * @param mobilePhone of type String
     * @return String
     * @throws DAOException when
     */
    public String findByMobilePhone(Role role, String mobilePhone) throws DAOException {
        String result = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(
                     role == Role.ADMIN ? FIND_ADMIN_PHONES :FIND_CLIENT_PHONES)
        ) {
            statement.setString(1, mobilePhone);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getString(PARAM_PHONE_NUMBER);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return result;
    }
}
