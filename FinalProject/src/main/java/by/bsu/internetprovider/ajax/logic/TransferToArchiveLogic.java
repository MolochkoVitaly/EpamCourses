package by.bsu.internetprovider.ajax.logic;


import by.bsu.internetprovider.dao.impl.TariffDAO;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.TechnicalException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import static by.bsu.internetprovider.ajax.util.JSONUtil.jsonToId;
import static by.bsu.internetprovider.ajax.util.JSONUtil.stringToJson;

/**
 * Class TransferToArchiveLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class TransferToArchiveLogic {
    /**
     * Method transfer ...
     *
     * @param requestData of type String
     * @throws TechnicalException when
     */
    public static void transfer(String requestData) throws TechnicalException {
        try {
            JSONObject json = stringToJson(requestData);
            Long tariffId = jsonToId(json);
            TariffDAO.getInstance().transferToArchive(tariffId);
        } catch (ParseException e) {
            throw new TechnicalException("ParseException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }

    }
}
