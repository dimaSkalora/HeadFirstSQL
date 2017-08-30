package head_03.clown_info;

public class Info {

    private String name;
    private String last_seen;
    private String appearance;
    private String activities;

    public Info() {
    }

    public Info(String name, String last_seen, String appearance, String activities) {
        this.name = name;
        this.last_seen = last_seen;
        this.appearance = appearance;
        this.activities = activities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
}
