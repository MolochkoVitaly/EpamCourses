package by.bsu.chef.action;

import by.bsu.chef.entity.Food;
import by.bsu.chef.entity.SideDish;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;


public class SideDishMaker {
    private static final Logger LOG = Logger.getLogger(SideDishMaker.class);

    public static void maker(SideDish dish, ArrayList<Food> ingredients) {
        LOG.info("Starting method maker");
        if (ingredients.size() != 0) {
            Random rand = new Random();
            int countIngredients = rand.nextInt(ingredients.size()) + 1;

            for (int i = 0; i < countIngredients; i++){
                dish.setOneIngredient(ingredients.get(rand.nextInt(ingredients.size())));
            }
        }
        LOG.info("finishing method maker");
    }
}
