package by.bsu.internetprovider.dao;

import by.bsu.internetprovider.entity.Payment;
import by.bsu.internetprovider.exception.DAOException;


/**
 * Interface IPaymentDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface IPaymentDAO extends GenericDAO<Long, Payment> {
    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return Payment
     * @throws DAOException when
     */
    @Override
    Payment findById(Long id) throws DAOException;

    /**
     * Method add ...
     *
     * @param entity of type Payment
     * @return boolean
     * @throws DAOException when
     */
    @Override
    boolean add(Payment entity) throws DAOException;
}
