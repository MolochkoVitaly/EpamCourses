package by.bsu.internetprovider.dao.impl;


import by.bsu.internetprovider.dao.ITariffDAO;
import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.pool.ConnectionPool;
import by.bsu.internetprovider.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class TariffDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class TariffDAO implements ITariffDAO {
    /** Field instance  */
    private static TariffDAO instance = TariffDAO.getInstance();

    /** Field PARAM_TARIFF_ID  */
    private static final String PARAM_TARIFF_ID = "tariff_id";

    /** Field PARAM_TARIFF_NAME  */
    private static final String PARAM_TARIFF_NAME = "tariff_name";

    /** Field PARAM_DESCRIPTION  */
    private static final String PARAM_DESCRIPTION = "description";

    /** Field PARAM_CURRENCY_CODE  */
    private static final String PARAM_CURRENCY_CODE = "currency_code";

    /** Field PARAM_MONTH_PAYMENT  */
    private static final String PARAM_MONTH_PAYMENT = "month_payment";

    /** Field PARAM_UPLOAD_SPEED  */
    private static final String PARAM_UPLOAD_SPEED ="upload_speed";

    /** Field PARAM_DOWNLOAD_SPEED  */
    private static final String PARAM_DOWNLOAD_SPEED ="download_speed";

    /** Field PARAM_TRAFFIC_VOLUME  */
    private static final String PARAM_TRAFFIC_VOLUME ="traffic_volume";

    /** Field PARAM_IN_ARCHIVE  */
    private static final String PARAM_IN_ARCHIVE = "in_archive";

    /** Field FIND_ALL_TARIFFS  */
    private static final String FIND_ALL_TARIFFS =
            "SELECT * FROM tariffs WHERE in_archive =\"false\"";

    /** Field INSERT_TARIFF  */
    private static final String INSERT_TARIFF =
            "INSERT INTO tariffs(tariff_name, description, currency_code, month_payment, upload_speed, download_speed," +
                       " traffic_volume, in_archive) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    /** Field FIND_TARIFF_BY_ID  */
    private static final String FIND_TARIFF_BY_ID =
            "SELECT * FROM tariffs WHERE tariff_id = ?";

    /** Field EDIT_TARIFF  */
    private static final String EDIT_TARIFF =
            "UPDATE tariffs SET tariff_name = ?, description = ?, month_payment = ?, upload_speed = ?, download_speed = ?," +
            " traffic_volume = ? WHERE tariff_id = ?";

    /** Field TRANSFER_TO_ARCHIVE  */
    private static final String TRANSFER_TO_ARCHIVE =
            "UPDATE tariffs SET in_archive = \"true\" WHERE tariff_id = ?";


    /**
     * Constructor TariffDAO creates a new TariffDAO instance.
     */
    private TariffDAO() {}

    /**
     * Method getInstance returns the instance of this TariffDAO object.
     *
     *
     *
     * @return the instance (type TariffDAO) of this TariffDAO object.
     */
    public static TariffDAO getInstance() {
        if (instance == null) {
            instance = new TariffDAO();
        }
        return instance;
    }

    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return Tariff
     * @throws DAOException when
     * @see ITariffDAO#findById(Long)
     */
    @Override
    public Tariff findById(Long id) throws DAOException {
        Tariff tariff = null;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_TARIFF_BY_ID);
        ) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tariff = new Tariff();
                tariff.setId(Long.parseLong(resultSet.getString(PARAM_TARIFF_ID)));
                tariff.setTariffName(resultSet.getString(PARAM_TARIFF_NAME));
                tariff.setDescription(resultSet.getString(PARAM_DESCRIPTION));
                tariff.setCurrencyCode(resultSet.getString(PARAM_CURRENCY_CODE));
                tariff.setMonthPayment(Long.parseLong(resultSet.getString(PARAM_MONTH_PAYMENT)));
                tariff.setUploadSpeed(Long.parseLong(resultSet.getString(PARAM_UPLOAD_SPEED)));
                tariff.setDownloadSpeed(Long.parseLong(resultSet.getString(PARAM_DOWNLOAD_SPEED)));
                tariff.setTrafficVolume(Long.parseLong(resultSet.getString(PARAM_TRAFFIC_VOLUME)));
                tariff.setInArchive(resultSet.getString(PARAM_IN_ARCHIVE));
            }

        } catch (SQLException e) {
            throw new DAOException("SQLException in DAO layer!", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPool exception!", e);
        }
        return tariff;
    }

    /**
     * Method transferToArchive ...
     *
     * @param id of type Long
     * @return boolean
     * @throws DAOException when
     */
    public boolean transferToArchive(Long id) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(TRANSFER_TO_ARCHIVE)
        ) {
            statement.setLong(1, id);
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
     * Method editTariff ...
     *
     * @param tariff of type Tariff
     * @return boolean
     * @throws DAOException when
     */
    public boolean editTariff(Tariff tariff) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(EDIT_TARIFF)
        ) {
            statement.setString(1, tariff.getTariffName());
            statement.setString(2, tariff.getDescription());
            statement.setFloat(3, tariff.getMonthPayment());
            statement.setLong(4, tariff.getUploadSpeed());
            statement.setLong(5, tariff.getDownloadSpeed());
            statement.setLong(6, tariff.getTrafficVolume());
            statement.setLong(7, tariff.getId());
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
     * Method findAll ...
     * @return ArrayList<Tariff>
     * @throws DAOException when
     */
    public ArrayList<Tariff> findAll() throws DAOException {
        ArrayList<Tariff> tariffs = new ArrayList<>();
        Tariff tariff = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_ALL_TARIFFS)
        ) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tariff = new Tariff();
                tariff.setId(Long.parseLong(resultSet.getString(PARAM_TARIFF_ID)));
                tariff.setTariffName(resultSet.getString(PARAM_TARIFF_NAME));
                tariff.setDescription(resultSet.getString(PARAM_DESCRIPTION));
                tariff.setCurrencyCode(resultSet.getString(PARAM_CURRENCY_CODE));
                tariff.setMonthPayment(Long.parseLong(resultSet.getString(PARAM_MONTH_PAYMENT)));
                tariff.setUploadSpeed(Long.parseLong(resultSet.getString(PARAM_UPLOAD_SPEED)));
                tariff.setDownloadSpeed(Long.parseLong(resultSet.getString(PARAM_DOWNLOAD_SPEED)));
                tariff.setTrafficVolume(Long.parseLong(resultSet.getString(PARAM_TRAFFIC_VOLUME)));
                tariff.setInArchive(resultSet.getString(PARAM_IN_ARCHIVE));
                tariffs.add(tariff);
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException in DAO layer!", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPool exception!", e);
        }
        return tariffs;
    }

    /**
     * Method findAllForList ...
     * @return ArrayList<String>
     * @throws DAOException when
     */
    public ArrayList<String> findAllForList() throws DAOException {
        ArrayList<String> tariffs = new ArrayList<>();
        Tariff tariff = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_ALL_TARIFFS)
        ) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tariff = new Tariff();
                tariff.setId(Long.parseLong(resultSet.getString(PARAM_TARIFF_ID)));
                tariff.setTariffName(resultSet.getString(PARAM_TARIFF_NAME));
                tariffs.add(tariff.getNameAndId());
            }
        } catch (SQLException e) {
            throw new DAOException("SQLException in DAO layer!", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPool exception!", e);
        }
        return tariffs;
    }

    /**
     * Method add ...
     *
     * @param entity of type Tariff
     * @return boolean
     * @throws DAOException when
     * @see ITariffDAO#add(Tariff)
     */
    @Override
    public boolean add(Tariff entity) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(INSERT_TARIFF)
        ) {
            statement.setString(1, entity.getTariffName());
            statement.setString(2, entity.getDescription());
            statement.setString(3, entity.getCurrencyCode());
            statement.setFloat(4, entity.getMonthPayment());
            statement.setLong(5, entity.getUploadSpeed());
            statement.setLong(6, entity.getDownloadSpeed());
            statement.setLong(7, entity.getTrafficVolume());
            statement.setString(8, entity.getInArchive());
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
