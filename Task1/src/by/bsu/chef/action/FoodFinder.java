package by.bsu.chef.action;

import by.bsu.chef.entity.Food;
import by.bsu.chef.entity.SideDish;
import org.apache.log4j.Logger;

import java.util.ArrayList;


public class FoodFinder {
    private static final Logger LOG = Logger.getLogger(FoodFinder.class);

    public static ArrayList<Food> finder(SideDish dish, double leftSide, double rightSide) {
        LOG.info("starting method finder");
        ArrayList<Food> result = new ArrayList<>();
        for (int i = 0; i < dish.getIngredients().size(); i++){
            if ((dish.getIngredients().get(i).getCalories() >= leftSide) &&
                    (dish.getIngredients().get(i).getCalories() <= rightSide)){
                result.add(dish.getIngredients().get(i));
            }

        }
        LOG.info("finishing method finder");
        return result;
    }
}
