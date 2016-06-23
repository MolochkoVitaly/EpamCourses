package by.bsu.chef.action;

import by.bsu.chef.entity.Food;
import by.bsu.chef.entity.SideDish;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;


public class SortByCalories {
    private static final Logger LOG = Logger.getLogger(SortByCalories.class);

    public static void sort(SideDish dish) {
        LOG.info("starting method sort");
        ArrayList<Food> temp = new ArrayList<>(dish.getIngredients());
        temp.sort(Comparator.comparing(Food::getCalories));
        dish.setIngredients(temp);
        LOG.info("finishing method sort");
    }
}
