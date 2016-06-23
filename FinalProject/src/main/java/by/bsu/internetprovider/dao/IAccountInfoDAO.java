package by.bsu.internetprovider.dao;


import by.bsu.internetprovider.entity.AccountInfo;
import by.bsu.internetprovider.exception.DAOException;

/**
 * Interface IAccountInfoDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface IAccountInfoDAO extends GenericDAO<Long, AccountInfo> {
    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return AccountInfo
     * @throws DAOException when
     */
    @Override
    AccountInfo findById(Long id) throws DAOException;

    /**
     * Method add ...
     *
     * @param entity of type AccountInfo
     * @return boolean
     * @throws DAOException when
     */
    @Override
    boolean add(AccountInfo entity) throws DAOException;

}
