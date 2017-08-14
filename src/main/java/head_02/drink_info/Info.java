package head_02.drink_info;

public class Info {
    private String drink_name;
    private float cost;
    private float carbs;
    private String color;
    private char ice;
    private int calories;

    public Info() {
    }

    public Info(String drink_name, float cost, float carbs, String color, char ice, int calories) {
        this.drink_name = drink_name;
        this.cost = cost;
        this.carbs = carbs;
        this.color = color;
        this.ice = ice;
        this.calories = calories;
    }

    public String getDrink_name() {
        return drink_name;
    }

    public void setDrink_name(String drink_name) {
        this.drink_name = drink_name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getCarbs() {
        return carbs;
    }

    public void setCarbs(float carbs) {
        this.carbs = carbs;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public char getIce() {
        return ice;
    }

    public void setIce(char ice) {
        this.ice = ice;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
