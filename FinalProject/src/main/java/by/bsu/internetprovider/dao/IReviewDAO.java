package by.bsu.internetprovider.dao;

import by.bsu.internetprovider.entity.Review;
import by.bsu.internetprovider.exception.DAOException;


/**
 * Interface IReviewDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface IReviewDAO extends GenericDAO<Long, Review> {
    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return Review
     * @throws DAOException when
     */
    @Override
    Review findById(Long id) throws DAOException;

    /**
     * Method add ...
     *
     * @param entity of type Review
     * @return boolean
     * @throws DAOException when
     */
    @Override
    boolean add(Review entity) throws DAOException;

}
