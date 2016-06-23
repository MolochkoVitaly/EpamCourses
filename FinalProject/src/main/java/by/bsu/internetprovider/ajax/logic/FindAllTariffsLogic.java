package by.bsu.internetprovider.ajax.logic;

import by.bsu.internetprovider.dao.impl.TariffDAO;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import org.json.simple.JSONObject;

import java.util.ArrayList;


/**
 * Class FindAllTariffsLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class FindAllTariffsLogic {
    /** Field TARIFFS  */
    private static final String TARIFFS = "tariffs";

    /**
     * Method find ...
     * @return String
     * @throws TechnicalException when
     */
    public static String find() throws TechnicalException {
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> tariffs = null;
        try {
            tariffs = TariffDAO.getInstance().findAllForList();
            jsonObject.put(TARIFFS, tariffs);
        } catch (DAOException e) {
            throw new TechnicalException(e);
        }
        return jsonObject.toJSONString();
    }
}
