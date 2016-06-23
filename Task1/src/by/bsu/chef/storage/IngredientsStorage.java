package by.bsu.chef.storage;


import by.bsu.chef.entity.Food;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class IngredientsStorage {
    private static final Logger LOG = Logger.getLogger(IngredientsStorage.class);
    private ArrayList<Food>  ingredients;

    public IngredientsStorage() {
    }

    public IngredientsStorage(ArrayList<Food> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
        LOG.info("Created ingredient's storage");
    }

    public ArrayList<Food> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void setIngredients(ArrayList<Food> ingredients) {
        this.ingredients = ingredients;
    }
}
