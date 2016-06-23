package by.bsu.internetprovider.dao;


import by.bsu.internetprovider.entity.User;
import by.bsu.internetprovider.exception.DAOException;

/**
 * Interface IUserDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface IUserDAO extends GenericDAO<Long, User>  {
    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return User
     * @throws DAOException when
     */
    @Override
    User findById(Long id) throws DAOException;

    /**
     * Method add ...
     *
     * @param entity of type User
     * @return boolean
     * @throws DAOException when
     */
    @Override
    boolean add(User entity) throws DAOException;
}
