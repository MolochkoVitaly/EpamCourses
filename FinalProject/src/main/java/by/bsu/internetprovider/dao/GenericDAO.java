package by.bsu.internetprovider.dao;


import by.bsu.internetprovider.exception.DAOException;

/**
 * Interface GenericDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface GenericDAO <K, T> {
    /**
     * Method findById ...
     *
     * @param id of type K
     * @return T
     * @throws DAOException when
     */
    T findById(K id) throws DAOException;
    /**
     * Method add ...
     *
     * @param entity of type T
     * @return boolean
     * @throws DAOException when
     */
    boolean add(T entity) throws DAOException;
}
