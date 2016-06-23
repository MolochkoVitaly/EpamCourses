package by.bsu.internetprovider.ajax.logic;


import by.bsu.internetprovider.dao.impl.TariffDAO;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import static by.bsu.internetprovider.ajax.util.JSONUtil.stringToJson;
import static by.bsu.internetprovider.ajax.util.JSONUtil.jsonToId;

/**
 * Class FindSelectedTariffLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class FindSelectedTariffLogic {
    /** Field TARIFF  */
    private static final String TARIFF = "tariff";

    /**
     * Method findSelected ...
     *
     * @param requestData of type String
     * @return String
     * @throws TechnicalException when
     */
    public static String findSelected(String requestData) throws TechnicalException {
        Tariff tariff = null;
        JSONObject object = null;
        try {
            JSONObject json = stringToJson(requestData);
            Long id = jsonToId(json);
            tariff = TariffDAO.getInstance().findById(id);
            object = new JSONObject();
            object.put(TARIFF, tariff);

        } catch (ParseException e) {
            throw new TechnicalException("ParseException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }
        return object.toJSONString();
    }
}
