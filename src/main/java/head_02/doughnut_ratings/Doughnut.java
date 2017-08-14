package head_02.doughnut_ratings;

import java.sql.Date;
import java.sql.Time;

public class Doughnut {
    private String location;
    private Time time;
    private Date date;
    private String type;
    private int rating;
    private String comments;

    public Doughnut() {
    }

    public Doughnut(String location, Time time, Date date, String type, int rating, String comments) {
        this.location = location;
        this.time = time;
        this.date = date;
        this.type = type;
        this.rating = rating;
        this.comments = comments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
