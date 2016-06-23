package by.bsu.internetprovider.logic;


import by.bsu.internetprovider.dao.impl.TariffDAO;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;

import java.util.ArrayList;

/**
 * Class ShowAllTariffsLogic ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class ShowAllTariffsLogic {
    /**
     * Method show ...
     * @return ArrayList<Tariff>
     * @throws TechnicalException when
     */
    public static ArrayList<Tariff> show() throws TechnicalException {
        ArrayList<Tariff> tariffs = null;
        try {
            tariffs = TariffDAO.getInstance().findAll();
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return tariffs;
    }
}
