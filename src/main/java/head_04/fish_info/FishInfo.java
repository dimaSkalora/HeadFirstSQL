package head_04.fish_info;

public class FishInfo {
    private Integer fish_id;
    private String common;
    private String species;
    private String location;
    private String weight;

    public FishInfo() {
    }

    public FishInfo(String common, String species, String location, String weight) {
        this.common = common;
        this.species = species;
        this.location = location;
        this.weight = weight;
    }

    public FishInfo(Integer fish_id, String common, String species, String location, String weight) {
        this.fish_id = fish_id;
        this.common = common;
        this.species = species;
        this.location = location;
        this.weight = weight;
    }

    public Integer getFish_id() {
        return fish_id;
    }

    public void setFish_id(Integer fish_id) {
        this.fish_id = fish_id;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "FishInfo{" +
                "fish_id=" + fish_id +
                ", common='" + common + '\'' +
                ", species='" + species + '\'' +
                ", location='" + location + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
