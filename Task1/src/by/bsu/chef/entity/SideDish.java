package by.bsu.chef.entity;

import java.util.ArrayList;
import java.util.Iterator;


public class SideDish {
    private String name;
    private ArrayList<Food> ingredients;

    public SideDish(){
    }

    public SideDish(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Food> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void setIngredients(ArrayList<Food> ingredients) {
        this.ingredients = ingredients;
    }

    public void setOneIngredient(Food food){
        ingredients.add(food);
    }

    public double calculateCalories() {
        Iterator<Food> iterator = ingredients.iterator();
        double sumCalories = 0.0;

        while (iterator.hasNext()) {
            sumCalories += (iterator.next()).getCalories();
        }

        return sumCalories;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"name\":\"").append(getName())
                .append("\", \"ingredients\":\"").append(ingredients).append("\"}");

        return sb.toString();
    }
}
