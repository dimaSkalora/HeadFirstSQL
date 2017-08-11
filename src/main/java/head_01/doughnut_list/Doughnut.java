package head_01.doughnut_list;

public class Doughnut {
    private String name;
    private String type;
    private float cost;

    public Doughnut() {
      /*  name = "kip2";
        type = "pak";
        cost = 2.05f;*/
    }

    public Doughnut(String name, String type, float cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
