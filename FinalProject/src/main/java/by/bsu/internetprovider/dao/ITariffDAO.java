package by.bsu.internetprovider.dao;

import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;

/**
 * Interface ITariffDAO ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public interface ITariffDAO extends GenericDAO<Long, Tariff> {
    /**
     * Method findById ...
     *
     * @param id of type Long
     * @return Tariff
     * @throws DAOException when
     */
    @Override
    Tariff findById(Long id) throws DAOException;

    /**
     * Method add ...
     *
     * @param entity of type Tariff
     * @return boolean
     * @throws DAOException when
     */
    @Override
    boolean add(Tariff entity) throws DAOException;
}
