package by.bsu.chef.util;

import by.bsu.chef.entity.Green;
import by.bsu.chef.entity.Spice;
import by.bsu.chef.entity.Vegetables;
import by.bsu.chef.exception.IsNotCorrectValueException;
import by.bsu.chef.valid.Validator;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Objects;


public class JSONParseUtil {
    private static final Logger LOG = Logger.getLogger(JSONParseUtil.class);

    public static String getTypeFromJson(JSONObject json) {
        LOG.info("got class type from json");
        return (String)json.get(JsonTags.TYPE);
    }

    public static JSONArray jsonObjectToJsonArray(JSONObject object){
        return (JSONArray) object.get(JsonTags.INGREDIENTS);
    }

    public static Green jsonToGreen(JSONObject json) throws IsNotCorrectValueException {
        double weight = (double) json.get(JsonTags.WEIGHT);
        double calories = (double) json.get(JsonTags.CALORIES);
        String manufacturer = ((String) json.get(JsonTags.MANUFACTURER)).trim();
        String name = ((String)  json.get(JsonTags.NAME)).trim();
        String taste = ((String)json.get(JsonTags.TASTE)).trim();
        if (Validator.validateValue(weight) && Validator.validateValue(calories)) {
            if (Objects.equals(name, "") || Objects.equals(manufacturer, "") || Objects.equals(taste, "")) {
                throw new IsNotCorrectValueException("Не корректные данные в JSON файле");
            }
            return new Green(name, weight, calories, manufacturer, taste);
        } else {
            throw new IsNotCorrectValueException("Не корректные данные в JSON файле");
        }
    }

    public static Spice jsonToSpice(JSONObject json) throws IsNotCorrectValueException {
        double weight = (double) json.get(JsonTags.WEIGHT);
        double calories = (double) json.get(JsonTags.CALORIES);
        String manufacturer = ((String) json.get(JsonTags.MANUFACTURER)).trim();
        String name = ((String) json.get(JsonTags.NAME)).trim();
        double portion = (double) json.get(JsonTags.PORTION);
        if (Validator.validateValue(weight) && Validator.validateValue(calories) && Validator.validateValue(portion)) {
            if (Objects.equals(name, "") || Objects.equals(manufacturer, "")) {
                throw new IsNotCorrectValueException("Не корректные данные в JSON файле");
            }
            return new Spice(name, weight, calories, manufacturer, portion);
        } else {
            throw new IsNotCorrectValueException("Не корректные данные в JSON файле");
        }
    }

    public static Vegetables jsonToVegetables(JSONObject json) throws IsNotCorrectValueException {
        double weight = (double) json.get(JsonTags.WEIGHT);
        double calories = (double) json.get(JsonTags.CALORIES);
        String manufacturer = ((String) json.get(JsonTags.MANUFACTURER)).trim();
        String name = ((String)json.get(JsonTags.NAME)).trim();
        double energyValue = (double) json.get(JsonTags.ENERGY_VALUE);
        if (Validator.validateValue(weight) && Validator.validateValue(calories) && Validator.validateValue(energyValue)) {
            if (Objects.equals(name, "") || Objects.equals(manufacturer, "")) {
                throw new IsNotCorrectValueException("Не корректные данные в JSON файле");
            }
            return new Vegetables(name, weight, calories, manufacturer, energyValue);
        } else {
            throw new IsNotCorrectValueException("Не корректные данные в JSON файле");
        }
    }


}