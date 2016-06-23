package by.bsu.chef.valid;


import by.bsu.chef.util.JsonTags;
import org.json.simple.JSONObject;

public class Validator {
    public static boolean validateValue(double value) {
        return value >= 0;
    }

    private static boolean validKey(String key, JSONObject json){
        return json.containsKey(key);
    }

    public static boolean validateJsonGreen(JSONObject json) {
        return (validKey(JsonTags.NAME, json) && validKey(JsonTags.WEIGHT, json) &&
                validKey(JsonTags.CALORIES, json) && validKey(JsonTags.MANUFACTURER, json) &&
                validKey(JsonTags.TASTE, json));
    }

    public static boolean validateJsonSpice(JSONObject json) {
        return (validKey(JsonTags.NAME, json) && validKey(JsonTags.WEIGHT, json) &&
                validKey(JsonTags.CALORIES, json) && validKey(JsonTags.MANUFACTURER, json) &&
                validKey(JsonTags.PORTION, json));
    }

    public static boolean validateJsonVegetables(JSONObject json) {
        return (validKey(JsonTags.NAME, json) && validKey(JsonTags.WEIGHT, json) &&
                validKey(JsonTags.CALORIES, json) && validKey(JsonTags.MANUFACTURER, json) &&
                validKey(JsonTags.ENERGY_VALUE, json));
    }
}
