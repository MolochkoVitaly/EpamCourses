package by.bsu.chef.reporter;


import by.bsu.chef.entity.*;

import java.util.ArrayList;


public class Reporter {

    public static void printCalories(SideDish food) {
        System.out.println("В салате " + food.getName() + " - " + food.calculateCalories() + " калорий");
    }

    public static void printIngredients(SideDish food) {
        System.out.println("Салат " + food.getName() + " состоит из: ");

        for (Food aFood: food.getIngredients()) {
            System.out.println(aFood.getName());
        }
    }

    public static void printIngredients(ArrayList<Food> list) {
        System.out.println("Список всех ингредиентов: ");

        for (Food aFood: list) {
            System.out.println(aFood.getName());
        }
    }
}
