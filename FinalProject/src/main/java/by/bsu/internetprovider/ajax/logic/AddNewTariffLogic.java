package by.bsu.internetprovider.ajax.logic;


import by.bsu.internetprovider.dao.impl.TariffDAO;
import by.bsu.internetprovider.entity.Tariff;
import by.bsu.internetprovider.exception.DAOException;
import by.bsu.internetprovider.exception.LogicException;
import by.bsu.internetprovider.exception.TechnicalException;
import by.bsu.internetprovider.manager.MessageManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.regex.Pattern;

import static by.bsu.internetprovider.ajax.util.JSONUtil.jsonToTariff;
import static by.bsu.internetprovider.ajax.util.JSONUtil.stringToJson;

/**
 * Class AddNewTariffLogic ...
 *
 * @author Виталий
 * Created on 21.06.2016
 */
public class AddNewTariffLogic {
    /** Field PARAM_NAME_VALIDATION  */
    private static final String PARAM_TEXT_VALIDATION = "[A-ZА-ЯЁ][A-ZА-ЯЁa-zа-яё0-9+-_\\s]{2,}$";

    /** Field PARAM_NAME_VALIDATION  */
    private static final String PARAM_DESCRIPTION_VALIDATION = "^[A-ZА-ЯЁ][A-ZА-ЯЁa-zа-яё.,:;!,\"~`#@$%&^*()-_=+\\s]{2,}$";

    /** Field PARAM_TEXT_VALIDATION  */
    private static final String PARAM_NUMBER_VALIDATION = "[1-9][0-9]{1,}";

    /**
     * Method add ...
     *
     * @param requestData of type String
     * @throws TechnicalException when
     */
    public static void add(String requestData, String locale) throws TechnicalException, LogicException {
        Tariff tariff = null;
        try {
            JSONObject json = stringToJson(requestData);
            tariff =  jsonToTariff(json);
            validation(tariff, locale);
            TariffDAO.getInstance().add(tariff);
        } catch (ParseException e) {
            throw new TechnicalException("ParseException", e);
        } catch (DAOException e) {
            throw new TechnicalException("DAOException", e);
        }
    }

    /**
     * Method validation ...
     *
     * @param tariff of type Tariff
     * @param locale of type String
     * @throws LogicException when
     */
    private static void validation(Tariff tariff, String locale) throws LogicException {
        validationText(PARAM_TEXT_VALIDATION, MessageManager.TARIFF_NAME_IS_NOT_VALID, tariff.getTariffName(), locale);
        validationText(PARAM_DESCRIPTION_VALIDATION,MessageManager.TARIFF_DESCRIPTION_IS_NOT_VALID, tariff.getDescription(), locale);
        validationText(PARAM_NUMBER_VALIDATION, MessageManager.UPLOAD_IS_NOT_VALID,  tariff.getUploadSpeed().toString(), locale);
        validationText(PARAM_NUMBER_VALIDATION, MessageManager.DOWNLOAD_IS_NOT_VALID, tariff.getDownloadSpeed().toString(), locale);
        validationText(PARAM_NUMBER_VALIDATION, MessageManager.VOLUME_IS_NOT_VALID, tariff.getTrafficVolume().toString(), locale);
        validationText(PARAM_NUMBER_VALIDATION, MessageManager.PRICE_IS_NOT_VALID, tariff.getMonthPayment().toString(), locale);
    }

    /**
     * Method validationText ...
     *
     * @param pattern of type String
     * @param errorMessage of type String
     * @param field of type String
     * @param locale of type String
     * @throws LogicException when
     */
    private static void validationText(String pattern, String errorMessage, String field, String locale) throws LogicException {
        if (!Pattern.matches(pattern, field)) {
            throw new LogicException(MessageManager.getManagerByLocale(locale).getProperty(errorMessage));
        }
    }
}
