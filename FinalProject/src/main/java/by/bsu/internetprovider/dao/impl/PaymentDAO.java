package by.bsu.internetprovider.dao.impl;

import by.bsu.internetprovider.dao.IPaymentDAO;
import by.bsu.internetprovider.entity.ClientAccount;
import by.bsu.internetprovider.entity.Payment;
import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.pool.ConnectionPool;
import by.bsu.internetprovider.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Class PaymentDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class PaymentDAO implements IPaymentDAO {
    /** Field instance  */
    private static PaymentDAO instance = PaymentDAO.getInstance();

    /** Field PARAM_ACCOUNT_ID  */
    private static final String PARAM_ACCOUNT_ID = "account_id";

    /** Field PARAM_CLIENT_ID  */
    private static final String PARAM_CLIENT_ID = "client_id";

    /** Field PARAM_CREATING_DATE  */
    private static final String PARAM_CREATING_DATE = "creating_date";

    /** Field PARAM_END_DATE  */
    private static final String PARAM_END_DATE = "end_date";

    /** Field PARAM_BALANCE  */
    private static final String PARAM_BALANCE = "balance";

    /** Field PARAM_FIRST_NAME  */
    private static final String PARAM_FIRST_NAME = "first_name";

    /** Field PARAM_LAST_NAME  */
    private static final String PARAM_LAST_NAME = "last_name";

    /** Field PARAM_EMAIL  */
    private static final String PARAM_EMAIL = "email";

    /** Field PARAM_PASSWORD  */
    private static final String PARAM_PASSWORD = "password";

    /** Field PARAM_PATRONYMIC  */
    private static final String PARAM_PATRONYMIC = "patronymic";

    /** Field PARAM_PHONE_NUMBER  */
    private static final String PARAM_PHONE_NUMBER = "phone_number";

    /** Field PARAM_ADDRESS  */
    private static final String PARAM_ADDRESS = "address";

    /** Field PARAM_PAYMENT_SUM  */
    private static final String PARAM_PAYMENT_SUM = "payment_sum";

    /** Field PARAM_PAYMENT_DATE  */
    private static final String PARAM_PAYMENT_DATE = "payment_date";

    /** Field INSERT_PAYMENT  */
    private static final String INSERT_PAYMENT =
            "INSERT INTO payments(account_id, payment_date, payment_sum) VALUES(?, ?, ?)";

    /** Field FIND_PAYMENT_BY_ID  */
    private static final String FIND_PAYMENT_BY_ID =
            "SELECT clients_account.account_id, creating_date, end_date, balance,  clients.client_id,  last_name," +
            " first_name, patronymic, email, password, phone_number, address, payment_date, payment_sum " +
            "FROM (clients_account JOIN clients ON clients_account.client_id = clients.client_id) JOIN payments ON" +
            " payments.account_id = clients_account.account_id WHERE payment_id = ?";


    /**
     * Constructor PaymentDAO creates a new PaymentDAO instance.
     */
    private PaymentDAO() {}

    /**
     * Method getInstance returns the instance of this PaymentDAO object.
     *
     *
     *
     * @return the instance (type PaymentDAO) of this PaymentDAO object.
     */
    public static PaymentDAO getInstance() {
        if (instance == null){
            instance = new PaymentDAO();
        }
        return instance;
    }

    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return Payment
     * @throws DAOException when
     * @see IPaymentDAO#findById(Long)
     */
    @Override
    public Payment findById(Long id) throws DAOException {
        ClientAccount account = null;
        User user = null;
        Payment payment = null;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_PAYMENT_BY_ID)
        ) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                account = new ClientAccount();
                payment = new Payment();
                user.setId(Long.parseLong(resultSet.getString(PARAM_CLIENT_ID)));
                user.setName(resultSet.getString(PARAM_FIRST_NAME));
                user.setSurname(resultSet.getString(PARAM_LAST_NAME));
                user.setPatronymic(resultSet.getString(PARAM_PATRONYMIC));
                user.setEmail(resultSet.getString(PARAM_EMAIL));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setPhoneNumber(resultSet.getString(PARAM_PHONE_NUMBER));
                user.setAddress(resultSet.getString(PARAM_ADDRESS));
                user.setRole(Role.CLIENT);
                account.setId(resultSet.getLong(PARAM_ACCOUNT_ID));
                account.setUser(user);
                account.setCreatingDate(resultSet.getString(PARAM_CREATING_DATE));
                account.setEndDate(resultSet.getString(PARAM_END_DATE));
                account.setBalance(resultSet.getFloat(PARAM_BALANCE));
                payment.setId(id);
                payment.setAccount(account);
                payment.setPaymentDate(resultSet.getString(PARAM_PAYMENT_DATE));
                payment.setPaymentSum(Long.parseLong(resultSet.getString(PARAM_PAYMENT_SUM)));
            }
        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return payment;
    }

    /**
     * Method add ...
     *
     * @param entity of type Payment
     * @return boolean
     * @throws DAOException when
     * @see IPaymentDAO#add(Payment)
     */
    @Override
    public boolean add(Payment entity) throws DAOException {
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(INSERT_PAYMENT)
        ){
            statement.setLong(1, entity.getAccount().getId());
            statement.setString(2, entity.getPaymentDate());
            statement.setLong(3, entity.getPaymentSum());
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
     * Method main ...
     *
     * @param args of type String[]
     * @throws DAOException when
     */
    public static void main(String[] args) throws DAOException {
        System.out.println(PaymentDAO.getInstance().findById(1l));
    }
}
