package by.bsu.chef.entity;


public class Spice extends Food {
    private double portion;

    public Spice() {

    }

    public Spice(String name, double weight, double calories,String manufacturer, double portion) {
        super(name, weight, calories, manufacturer);
        this.portion = portion;
    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(float portion) {
        this.portion = portion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"name\":\"").append(getName())
                .append("\", \"weight\":\"").append(getWeight())
                .append("\", \"calories\":\"").append(getCalories())
                .append("\", \"manufacturer\":\"").append(getManufacturer())
                .append("\", \"portion\":\"").append(portion).append("\"}");

        return sb.toString();
    }

}
