package head_02.easy_drinks;

public class Drink {
    private String drink_name;
    private String main;
    private float amount1;
    private String second;
    private float amount2;
    private String directions;

    public Drink() {
    }

    public Drink(String drink_name, String main, float amount1, String second, float amount2, String directions) {
        this.drink_name = drink_name;
        this.main = main;
        this.amount1 = amount1;
        this.second = second;
        this.amount2 = amount2;
        this.directions = directions;
    }

    public String getDrink_name() {
        return drink_name;
    }

    public void setDrink_name(String drink_name) {
        this.drink_name = drink_name;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public float getAmount1() {
        return amount1;
    }

    public void setAmount1(float amount1) {
        this.amount1 = amount1;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public float getAmount2() {
        return amount2;
    }

    public void setAmount2(float amount2) {
        this.amount2 = amount2;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
