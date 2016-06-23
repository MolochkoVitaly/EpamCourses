package by.bsu.chef.entity;


public class Vegetables extends Food {
    private double energyValue; // энергетическая ценность

    public Vegetables(){

    }

    public Vegetables(String name, double weight, double calories, String manufacturer, double energyValue) {
        super(name, weight,calories, manufacturer);
        this.energyValue = energyValue;
    }

    public double getEnergyValue() {
        return energyValue;
    }

    public void setEnergyValue(float energyValue) {
        this.energyValue = energyValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"name\":\"").append(getName())
                .append("\", \"weight\":\"").append(getWeight())
                .append("\", \"calories\":\"").append(getCalories())
                .append("\", \"manufacturer\":\"").append(getManufacturer())
                .append("\", \"energyValue\":\"").append(energyValue).append("\"}");

        return sb.toString();
    }

}
