package by.bsu.internetprovider.dao.impl;

import by.bsu.internetprovider.dao.IClientsAccountDAO;
import by.bsu.internetprovider.entity.ClientAccount;
import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.pool.ConnectionPool;
import by.bsu.internetprovider.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Class ClientsAccountDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ClientsAccountDAO implements IClientsAccountDAO {
    /** Field instance  */
    private static ClientsAccountDAO instance = ClientsAccountDAO.getInstance();

    /** Field ACCOUNT_ID  */
    private static final String ACCOUNT_ID = "account_id";

    /** Field CLIENT_ID  */
    private static final String CLIENT_ID = "client_id";

    /** Field CREATING_DATE  */
    private static final String CREATING_DATE = "creating_date";

    /** Field END_DATE  */
    private static final String END_DATE = "end_date";

    /** Field BALANCE  */
    private static final String BALANCE = "balance";

    /** Field INSERT_IN_CLIENT_ACCOUNT  */
    private static final String INSERT_IN_CLIENT_ACCOUNT =
            "INSERT INTO clients_account(client_id, creating_date, balance) VALUES(?, ?, ?)";

    /** Field FIND_BY_CLIENT_ID  */
    private static final String FIND_BY_CLIENT_ID =
            "SELECT * FROM clients_account WHERE client_id = ?";

    /** Field GET_ACCOUNT_BALANCE  */
    private static final String GET_ACCOUNT_BALANCE =
            "SELECT balance FROM clients_account WHERE client_id = ?";

    /** Field UPDATE_ACCOUNT_BALANCE  */
    private static final String UPDATE_ACCOUNT_BALANCE =
            "UPDATE clients_account SET balance = ? WHERE client_id = ?";

    /** Field FIND_ACCOUNT_ID_BY_CLIENT_ID  */
    private static final String FIND_ACCOUNT_ID_BY_CLIENT_ID =
            "SELECT account_id FROM clients_account WHERE client_id = ?";


    /**
     * Constructor ClientsAccountDAO creates a new ClientsAccountDAO instance.
     */
    private ClientsAccountDAO() {}


    /**
     * Method getInstance returns the instance of this ClientsAccountDAO object.
     *
     *
     *
     * @return the instance (type ClientsAccountDAO) of this ClientsAccountDAO object.
     */
    public static ClientsAccountDAO getInstance() {
        if (instance == null) {
            instance = new ClientsAccountDAO();
        }
        return instance;
    }

    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return ClientAccount
     * @throws DAOException when
     * @see IClientsAccountDAO#findById(Long)
     */
    @Override
    public ClientAccount findById(Long id) throws DAOException {
        ClientAccount account = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
        PreparedStatement statement = proxyConnection.prepareStatement(FIND_BY_CLIENT_ID);
        ) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                account = new ClientAccount();
                account.setUser(UserDAO.getInstance().findById(resultSet.getLong(CLIENT_ID)));
                account.setId(resultSet.getLong(ACCOUNT_ID));
                account.setCreatingDate(resultSet.getString(CREATING_DATE));
                account.setEndDate(resultSet.getString(END_DATE));
                account.setBalance(resultSet.getFloat(BALANCE));
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return account;
    }

    /**
     * Method add ...
     *
     * @param entity of type ClientAccount
     * @return boolean
     * @throws DAOException when
     * @see IClientsAccountDAO#add(ClientAccount)
     */
    @Override
    public boolean add(ClientAccount entity) throws DAOException {
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(INSERT_IN_CLIENT_ACCOUNT);
        ) {
            statement.setLong(1, entity.getUser().getId());
            statement.setString(2, entity.getCreatingDate());
            statement.setFloat(3, entity.getBalance());
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
     * Method getAccountBalance ...
     *
     * @param clientId of type Long
     * @return Long
     * @throws DAOException when
     */
    public Long getAccountBalance(Long clientId) throws DAOException {
        Long balance = null;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(GET_ACCOUNT_BALANCE)
        ) {
            statement.setLong(1, clientId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                balance = resultSet.getLong(BALANCE);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return balance;
    }

    /**
     * Method updateBalance ...
     *
     * @param clientId of type Long
     * @param balance of type Long
     * @return boolean
     * @throws DAOException when
     */
    public boolean updateBalance(Long clientId, Long balance) throws DAOException {
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(UPDATE_ACCOUNT_BALANCE)
        ) {
            statement.setLong(1, balance);
            statement.setLong(2, clientId);
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
     * Method findAccountIdByClientId ...
     *
     * @param clientId of type Long
     * @return Long
     * @throws DAOException when
     */
    public Long findAccountIdByClientId(Long clientId) throws DAOException {
        Long accountId = null;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_ACCOUNT_ID_BY_CLIENT_ID)
        ) {
            statement.setLong(1, clientId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accountId = resultSet.getLong(ACCOUNT_ID);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return accountId;
    }

}
