package by.bsu.internetprovider.ajax.util;


import by.bsu.internetprovider.entity.Review;
import by.bsu.internetprovider.entity.Tariff;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Objects;

/**
 * Class JSONUtil ...
 *
 * @author Виталий
 * Created on 19.06.2016
 */
public class JSONUtil {
    /** Field COMMAND  */
    private static final String COMMAND = "command";

    /** Field UPDATE  */
    private static final String UPDATE = "update";

    /** Field ID  */
    private static final String ID = "id";

    /** Field TEXT  */
    private static final String TEXT = "text";

    /** Field TARIFF_NAME  */
    private static final String TARIFF_NAME = "name";

    /** Field TARIFF_DESCRIPTION  */
    private static final String TARIFF_DESCRIPTION = "description";

    /** Field UPLOAD_SPEED  */
    private static final String UPLOAD_SPEED = "upload";

    /** Field DOWNLOAD_SPEED  */
    private static final String DOWNLOAD_SPEED = "download";

    /** Field TARIFF_PRICE  */
    private static final String TARIFF_PRICE = "price";

    /** Field TARIFF_VOLUME  */
    private static final String TARIFF_VOLUME = "volume";

    /** Field SUM  */
    private static final String SUM = "sum";

    /** Field DEFAULT_LONG_VALUE  */
    private static final String DEFAULT_LONG_VALUE = "-1";

    /**
     * Constructor JSONUtil creates a new JSONUtil instance.
     */
    public JSONUtil() {}

    /**
     * Method getCommand ...
     *
     * @param json of type JSONObject
     * @return String
     */
    public static String getCommand(JSONObject json) {
        return (String) json.get(COMMAND);
    }

    /**
     * Method getUpdateType ...
     *
     * @param json of type JSONObject
     * @return String
     */
    public static String getUpdateType(JSONObject json) {
        return (String)json.get(UPDATE);
    }

    /**
     * Method stringToJson ...
     *
     * @param data of type String
     * @return JSONObject
     * @throws ParseException when
     */
    public static JSONObject stringToJson(String data) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(data.trim());
    }

    /**
     * Method jsonToId ...
     *
     * @param json of type JSONObject
     * @return Long
     */
    public static Long jsonToId(JSONObject json) {
        return Long.parseLong((String)json.get(ID));
    }

    /**
     * Method jsonToReviewText ...
     *
     * @param json of type JSONObject
     * @return String
     */
    public static String jsonToReviewText(JSONObject json) {
        return (String)json.get(TEXT);
    }

    /**
     * Method jsonToSum ...
     *
     * @param json of type JSONObject
     * @return String
     */
    public static String jsonToSum(JSONObject json) {
        return (String) json.get(SUM);
    }

    /**
     * Method jsonToTariff ...
     *
     * @param json of type JSONObject
     * @return Tariff
     */
    public static Tariff jsonToTariff(JSONObject json) {
        Tariff tariff = new Tariff();
        String current;
        tariff.setTariffName((String) json.get(TARIFF_NAME));
        tariff.setDescription((String) json.get(TARIFF_DESCRIPTION));
        current = !Objects.equals((String) json.get(UPLOAD_SPEED), "") ? (String) json.get(UPLOAD_SPEED) : DEFAULT_LONG_VALUE;
        tariff.setUploadSpeed(Long.parseLong(current));
        current = !Objects.equals((String) json.get(DOWNLOAD_SPEED), "") ? (String) json.get(DOWNLOAD_SPEED) : DEFAULT_LONG_VALUE;
        tariff.setDownloadSpeed(Long.parseLong((current)));
        current = !Objects.equals((String) json.get(TARIFF_PRICE), "") ? (String) json.get(TARIFF_PRICE) : DEFAULT_LONG_VALUE;
        tariff.setMonthPayment(Long.parseLong(current));
        current = !Objects.equals((String) json.get(TARIFF_VOLUME), "") ? (String) json.get(TARIFF_VOLUME) : DEFAULT_LONG_VALUE;
        tariff.setTrafficVolume(Long.parseLong(current));
        return tariff;
    }
}
