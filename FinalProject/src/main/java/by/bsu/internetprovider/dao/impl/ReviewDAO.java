package by.bsu.internetprovider.dao.impl;


import by.bsu.internetprovider.dao.IReviewDAO;
import by.bsu.internetprovider.entity.Review;
import by.bsu.internetprovider.entity.Role;
import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.ConnectionPoolException;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.pool.ConnectionPool;
import by.bsu.internetprovider.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class ReviewDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ReviewDAO implements IReviewDAO {
    /** Field instance  */
    private static ReviewDAO instance = ReviewDAO.getInstance();

    /** Field PARAM_FIRST_NAME  */
    private static final String PARAM_FIRST_NAME = "first_name";

    /** Field PARAM_LAST_NAME  */
    private static final String PARAM_LAST_NAME = "last_name";

    /** Field PARAM_CLIENT_ID  */
    private static final String PARAM_CLIENT_ID = "client_id";

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

    /** Field PARAM_REVIEW_ID  */
    private static final String PARAM_REVIEW_ID = "review_id";

    /** Field PARAM_TEXT  */
    private static final String PARAM_TEXT = "text";

    /** Field PARAM_TIME  */
    private static final String PARAM_TIME = "time";


    /** Field FIND_ALL_REVIEWS  */
    private static final String FIND_ALL_REVIEWS =
            "SELECT review_id, first_name, last_name, text, time FROM reviews JOIN clients" +
            " ON reviews.client_id = clients.client_id";

    /** Field INSERT_REVIEW_BY_USER  */
    private static final String INSERT_REVIEW_BY_USER = "INSERT INTO reviews(client_id, text, time) VALUES(?, ?, ?) ";

    /** Field DELETE_REVIEW_BY_ADMIN  */
    private static final String DELETE_REVIEW_BY_ADMIN = "DELETE FROM reviews WHERE review_id = ?";

    /** Field FIND_REVIEW_BY_ID  */
    private static final String FIND_REVIEW_BY_ID =
            "SELECT clients.client_id,  last_name, first_name, patronymic, email, password, phone_number," +
            " address, text, time FROM(reviews JOIN clients ON reviews.client_id = clients.client_id) WHERE review_id = ?";


    /**
     * Constructor ReviewDAO creates a new ReviewDAO instance.
     */
    private ReviewDAO() {}


    /**
     * Method getInstance returns the instance of this ReviewDAO object.
     *
     *
     *
     * @return the instance (type ReviewDAO) of this ReviewDAO object.
     */
    public static ReviewDAO getInstance() {
        if (instance == null){
            instance = new ReviewDAO();
        }
        return instance;
    }

    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return Review
     * @throws DAOException when
     * @see IReviewDAO#findById(Long)
     */
    @Override
    public Review findById(Long id) throws DAOException {
        Review review = null;
        User user = null;
        ResultSet resultSet = null;
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(FIND_REVIEW_BY_ID);
        ) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                review = new Review();
                user.setId(Long.parseLong(resultSet.getString(PARAM_CLIENT_ID)));
                user.setName(resultSet.getString(PARAM_FIRST_NAME));
                user.setSurname(resultSet.getString(PARAM_LAST_NAME));
                user.setPatronymic(resultSet.getString(PARAM_PATRONYMIC));
                user.setEmail(resultSet.getString(PARAM_EMAIL));
                user.setPassword(resultSet.getString(PARAM_PASSWORD));
                user.setPhoneNumber(resultSet.getString(PARAM_PHONE_NUMBER));
                user.setAddress(resultSet.getString(PARAM_ADDRESS));
                user.setRole(Role.CLIENT);
                review.setId(id);
                review.setText(resultSet.getString(PARAM_TEXT));
                review.setDate(resultSet.getString(PARAM_TIME));
                review.setUser(user);
            }

        } catch (SQLException ex) {
            throw new DAOException("SQLException in DAO layer!", ex);
        }
        catch (ConnectionPoolException ex){
            throw new DAOException("ConnectionPool exception!", ex);
        }

        return review;
    }

    /**
     * Method add ...
     *
     * @param entity of type Review
     * @return boolean
     * @throws DAOException when
     * @see IReviewDAO#add(Review)
     */
    @Override
    public boolean add(Review entity) throws DAOException {
        try (ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = proxyConnection.prepareStatement(INSERT_REVIEW_BY_USER)
        ) {
            statement.setLong(1, entity.getUser().getId());
            statement.setString(2, entity.getText());
            statement.setString(3, entity.getDate());
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
     * @return ArrayList<Review>
     * @throws DAOException when
     */
    public ArrayList<Review> findAll() throws DAOException {
        ArrayList<Review> reviews = new ArrayList<>();
        Review review = null;
        User user = null;
        ResultSet resultSet = null;
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(FIND_ALL_REVIEWS);
        ) {
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user= new User();
                review = new Review();
                user.setName(resultSet.getString(PARAM_FIRST_NAME));
                user.setSurname(resultSet.getString(PARAM_LAST_NAME));
                review.setId(resultSet.getLong(PARAM_REVIEW_ID));
                review.setText(resultSet.getString(PARAM_TEXT));
                review.setDate(resultSet.getString(PARAM_TIME));
                review.setUser(user);
                reviews.add(review);
            }

        } catch (SQLException e) {
            throw new DAOException("SQLException in DAO layer!", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPool exception!", e);
        }
        return reviews;
    }

    /**
     * Method delete ...
     *
     * @param reviewId of type Long
     * @return boolean
     * @throws DAOException when
     */
    public boolean delete(Long reviewId) throws DAOException {
        try(ProxyConnection proxyConnection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = proxyConnection.prepareStatement(DELETE_REVIEW_BY_ADMIN)
        ) {
            statement.setLong(1, reviewId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("SQLException in DAO layer!", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("ConnectionPool exception!", e);
        }
        return true;
    }
}
