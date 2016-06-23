package by.bsu.chef.init;


import by.bsu.chef.entity.Food;
import by.bsu.chef.exception.IsNotCorrectValueException;
import by.bsu.chef.exception.UnknownIngredientException;
import by.bsu.chef.util.JSONParseUtil;
import by.bsu.chef.valid.Validator;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

import static by.bsu.chef.util.JSONParseUtil.getTypeFromJson;
import static by.bsu.chef.util.JSONParseUtil.jsonToGreen;
import static by.bsu.chef.util.JSONParseUtil.jsonToSpice;
import static by.bsu.chef.util.JSONParseUtil.jsonToVegetables;

public class Initialize {
    private static final Logger LOG = Logger.getLogger(Initialize.class);

    public static ArrayList<Food> getIngredients(JSONObject jsonObject) throws UnknownIngredientException, IsNotCorrectValueException {
        LOG.info("starting initialization");
        ArrayList<Food> ingredients = new ArrayList<>();
        if (jsonObject == null) {
            throw new IsNotCorrectValueException("Пустой JSON файл или произошла ошибка при работе с файлом");
        }

        JSONArray jsonArray = JSONParseUtil.jsonObjectToJsonArray(jsonObject);

        for (Object aJsonArray : jsonArray) {
            JSONObject json = (JSONObject) aJsonArray;
            String type = getTypeFromJson(json);

            switch (type) {
                case "Green":
                    if (Validator.validateJsonGreen(json)) {
                        ingredients.add(jsonToGreen(json));
                    }
                    break;
                case "Spice":
                    if (Validator.validateJsonSpice(json)) {
                        ingredients.add(jsonToSpice(json));
                    }
                    break;
                case "Vegetables":
                    if (Validator.validateJsonVegetables(json)) {
                        ingredients.add(jsonToVegetables(json));
                    }
                    break;
                default:
                    throw new UnknownIngredientException();
            }
        }
        LOG.info("finishing initialization");
        return ingredients;
    }
}
