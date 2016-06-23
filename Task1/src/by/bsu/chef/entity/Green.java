package by.bsu.chef.entity;


public class Green extends Food {
    private String taste;

    public Green(){
    }

    public Green(String name, double weight, double calories,String manufacturer, String taste) {
        super(name, weight, calories, manufacturer);
        this.taste = taste;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{\"name\":\"").append(getName())
                .append("\", \"weight\":\"").append(getWeight())
                .append("\", \"calories\":\"").append(getCalories())
                .append("\", \"manufacturer\":\"").append(getManufacturer())
                .append("\", \"taste\":\"").append(taste).append("\"}");

        return sb.toString();
    }
}
