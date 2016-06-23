package by.bsu.internetprovider.dao;

import by.bsu.internetprovider.entity.ClientAccount;
import by.bsu.internetprovider.exception.DAOException;


/**
 * Interface IClientsAccountDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface IClientsAccountDAO extends GenericDAO<Long, ClientAccount> {
    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return ClientAccount
     * @throws DAOException when
     */
    @Override
    ClientAccount findById(Long id) throws DAOException;

    /**
     * Method add ...
     *
     * @param entity of type ClientAccount
     * @return boolean
     * @throws DAOException when
     */
    @Override
    boolean add(ClientAccount entity) throws DAOException;
}
