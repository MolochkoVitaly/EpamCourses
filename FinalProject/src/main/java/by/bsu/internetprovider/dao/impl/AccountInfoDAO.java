package by.bsu.internetprovider.dao.impl;


import by.bsu.internetprovider.dao.IAccountInfoDAO;
import by.bsu.internetprovider.entity.AccountInfo;
import by.bsu.internetprovider.entity.ClientAccount;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.pool.ConnectionPool;
import by.bsu.internetprovider.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class AccountInfoDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class AccountInfoDAO implements IAccountInfoDAO {
    /** Field instance  */
    private static AccountInfoDAO instance = AccountInfoDAO.getInstance();

    /** Field INFO_ID  */
    private static final String INFO_ID = "info_id";

    /** Field START_DATE  */
    private static final String START_DATE = "start_date";

    /** Field FINISH_DATE  */
    private static final String FINISH_DATE = "finish_date";

    /** Field TRAFFIC_USED  */
    private static final String TRAFFIC_USED = "traffic_used";

    /** Field TARIFF_ID  */
    private static final String TARIFF_ID ="tariff_id";

    /** Field TARIFF_NAME  */
    private static final String TARIFF_NAME = "tariff_name";

    /** Field DESCRIPTION  */
    private static final String DESCRIPTION = "description";

    /** Field CURRENCY_CODE  */
    private static final String CURRENCY_CODE = "currency_code";

    /** Field MONTH_PAYMENT  */
    private static final String MONTH_PAYMENT = "month_payment";

    /** Field UPLOAD_SPEED  */
    private static final String UPLOAD_SPEED = "upload_speed";

    /** Field DOWNLOAD_SPEED  */
    private static final String DOWNLOAD_SPEED = "download_speed";

    /** Field TRAFFIC_VOLUME  */
    private static final String TRAFFIC_VOLUME = "traffic_volume";

    /** Field IN_ARCHIVE  */
    private static final String IN_ARCHIVE = "in_archive";

    /** Field IS_ACTIVE  */
    private static final String IS_ACTIVE = "is_active";

    /** Field ACCOUNT_ID  */
    private static final String ACCOUNT_ID = "account_id";



    /** Field FIND_ACCOUNT_BY_ACCOUNT_ID  */
    private static final String FIND_ACCOUNT_BY_ACCOUNT_ID =
            "SELECT info_id, account_info.start_date, account_info.finish_date, traffic_used, tariffs.tariff_id," +
            " tariff_name, description, currency_code, month_payment, upload_speed, download_speed, traffic_volume, in_archive" +
            " FROM (account_info JOIN tariffs ON account_info.tariff_id = tariffs.tariff_id) WHERE account_id = ? AND is_active = \"true\"";

    /** Field INSERT_INFO  */
    private static final String INSERT_INFO =
            "INSERT INTO account_info(account_id, tariff_id, start_date, finish_date, traffic_used, is_active) VALUES(?, ?, ?, ?, ?, ?)";

    /** Field FIND_ACTIVE_BY_ACCOUNT_ID  */
    private static final String FIND_ACTIVE_BY_ACCOUNT_ID =
            "SELECT info_id, account_id, tariff_id, start_date, finish_date, traffic_used, is_active" +
             "  FROM account_info WHERE account_id = ? AND is_active = \"true\"";

    /** Field FIND_USED_TARIFF  */
    private static final String FIND_USED_TARIFF =
            "SELECT info_id, account_id, tariff_id, start_date, finish_date, traffic_used, is_active" +
            " FROM account_info WHERE account_id = ? AND tariff_id = ?";

    /** Field UPDATE_USED_TARIFF  */
    private static final String UPDATE_USED_TARIFF =
            "UPDATE account_info SET start_date = ?, finish_date = ?, is_active = \"true\" WHERE account_id = ? AND tariff_id = ?";

    /** Field CHANGE_STATUS  */
    private static final String CHANGE_STATUS =
            "UPDATE account_info SET is_active = \"false\" WHERE info_id = ?";

    /** Field FIND_ACCOUNT_INFO_BY_ID  */
    private static final String FIND_ACCOUNT_INFO_BY_ID =
            "SELECT account_info.start_date, account_info.finish_date, traffic_used, is_active, tariffs.tariff_id, tariff_name," +
            " description, currency_code, month_payment, upload_speed, download_speed, traffic_volume, in_archive " +
            "FROM (account_info JOIN tariffs ON account_info.tariff_id = tariffs.tariff_id) WHERE info_id = ?";


    /**
     * Constructor AccountInfoDAO creates a new AccountInfoDAO instance.
     */
    private AccountInfoDAO() {}

    /**
     * Method getInstance returns the instance of this AccountInfoDAO object.
     *
     * Field instance
     *
     * @return the instance (type AccountInfoDAO) of this AccountInfoDAO object.
     */
    public static AccountInfoDAO getInstance() {
        if (instance == null) {
            instance = new AccountInfoDAO();
        }
        return instance;
    }

    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return AccountInfo
     * @throws DAOException when
     * @see IAccountInfoDAO#findById(Long)
     */
    @Override
    public AccountInfo findById(Long id) throws DAOException {
        AccountInfo info = null;
        Tariff tariff = null;
        ClientAccount account = null;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_ACCOUNT_INFO_BY_ID);
        ) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tariff = new Tariff();
                info = new AccountInfo();
                account = new ClientAccount();
                info.setId(id);
                info.setCreatingDate(resultSet.getString(START_DATE));
                info.setEndDate(resultSet.getString(FINISH_DATE));
                info.setTrafficUsed(resultSet.getDouble(TRAFFIC_USED));
                info.setIsActive(resultSet.getString(IS_ACTIVE));
                tariff.setId(resultSet.getLong(TARIFF_ID));
                tariff.setTariffName(resultSet.getString(TARIFF_NAME));
                tariff.setDescription(resultSet.getString(DESCRIPTION));
                tariff.setCurrencyCode(resultSet.getString(CURRENCY_CODE));
                tariff.setMonthPayment(resultSet.getLong(MONTH_PAYMENT));
                tariff.setUploadSpeed(resultSet.getLong(UPLOAD_SPEED));
                tariff.setDownloadSpeed(resultSet.getLong(DOWNLOAD_SPEED));
                tariff.setTrafficVolume(resultSet.getLong(TRAFFIC_VOLUME));
                tariff.setInArchive(resultSet.getString(IN_ARCHIVE));
                account.setId(Long.parseLong(resultSet.getString(ACCOUNT_ID)));
                info.setClientAccount(account);
                info.setTariff(tariff);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return info;
    }

    /**
     * Method findActiveByAccountId ...
     *
     * @param accountId of type Long
     * @return AccountInfo
     * @throws DAOException when
     */
    public AccountInfo findActiveByAccountId(Long accountId) throws DAOException {
        ResultSet resultSet = null;
        AccountInfo info = null;
        Tariff tariff = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_ACCOUNT_BY_ACCOUNT_ID)
        ) {
            statement.setLong(1, accountId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tariff = new Tariff();
                info = new AccountInfo();
                info.setId(resultSet.getLong(INFO_ID));
                info.getClientAccount().setId(accountId);
                info.setCreatingDate(resultSet.getString(START_DATE));
                info.setEndDate(resultSet.getString(FINISH_DATE));
                info.setTrafficUsed(resultSet.getDouble(TRAFFIC_USED));
                tariff.setId(resultSet.getLong(TARIFF_ID));
                tariff.setTariffName(resultSet.getString(TARIFF_NAME));
                tariff.setDescription(resultSet.getString(DESCRIPTION));
                tariff.setCurrencyCode(resultSet.getString(CURRENCY_CODE));
                tariff.setMonthPayment(resultSet.getLong(MONTH_PAYMENT));
                tariff.setUploadSpeed(resultSet.getLong(UPLOAD_SPEED));
                tariff.setDownloadSpeed(resultSet.getLong(DOWNLOAD_SPEED));
                tariff.setTrafficVolume(resultSet.getLong(TRAFFIC_VOLUME));
                tariff.setInArchive(resultSet.getString(IN_ARCHIVE));
                info.setTariff(tariff);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return info;
    }

    /**
     * Method add ...
     *
     * @param entity of type AccountInfo
     * @return boolean
     * @throws DAOException when
     * @see IAccountInfoDAO#add(AccountInfo)
     */
    @Override
    public boolean add(AccountInfo entity) throws DAOException {
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(INSERT_INFO)
        ) {
            statement.setLong(1, entity.getClientAccount().getId());
            statement.setLong(2, entity.getTariff().getId());
            statement.setString(3, entity.getCreatingDate());
            statement.setString(4, entity.getEndDate());
            statement.setLong(5, 0L);
            statement.setString(6, "true");
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
     * Method checkActiveTariff ...
     *
     * @param accountId of type Long
     * @return boolean
     * @throws DAOException when
     */
    public boolean checkActiveTariff(Long accountId) throws DAOException {
        boolean result;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_ACTIVE_BY_ACCOUNT_ID)
        ) {
            statement.setLong(1, accountId);
            resultSet = statement.executeQuery();
            resultSet.last();
            result = resultSet.getRow() == 0;

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return result;
    }

    /**
     * Method usedPreviously ...
     *
     * @param accountId of type Long
     * @param tariffId of type Long
     * @return boolean
     * @throws DAOException when
     */
    public boolean usedPreviously(Long accountId, Long tariffId) throws DAOException {
        boolean result;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_USED_TARIFF)
        ) {
            statement.setLong(1, accountId);
            statement.setLong(2, tariffId);
            resultSet = statement.executeQuery();
            resultSet.last();
            result = resultSet.getRow() != 0;

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return result;
    }

    /**
     * Method updateUsedPreviously ...
     *
     * @param accountId of type Long
     * @param tariffId of type Long
     * @param startDate of type String
     * @param finishDate of type String
     * @return boolean
     * @throws DAOException when
     */
    public boolean updateUsedPreviously(Long accountId, Long tariffId, String startDate, String finishDate) throws DAOException {
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(UPDATE_USED_TARIFF)
        ) {
            statement.setString(1, startDate);
            statement.setString(2, finishDate);
            statement.setLong(3, accountId);
            statement.setLong(4, tariffId);
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
     * Method changeStatus ...
     *
     * @param infoId of type Long
     * @return boolean
     * @throws DAOException when
     */
    public boolean changeStatus(Long infoId) throws DAOException {
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(CHANGE_STATUS)
        ) {
            statement.setLong(1, infoId);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }
        return true;
    }

}
