package by.bsu.chef.entity;


public abstract class Food {
    private String name;
    private double weight;
    private double calories;
    private String manufacturer;

    public Food() {
    }

    public Food(String name, double weight, double calories, String manufacturer) {

        this.name = name;
        this.weight = weight;
        this.calories = calories;
        this.manufacturer = manufacturer;
    }

    public double getWeight() {
        return weight;
    }

    public double getCalories() {
        return calories;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }
}
