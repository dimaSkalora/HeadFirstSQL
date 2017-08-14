package head_02.black_book;

public class Book {
    private String date_name;
    private String rating;

    public Book() {
    }

    public Book(String date_name, String rating) {
        this.date_name = date_name;
        this.rating = rating;
    }

    public String getDate_name() {
        return date_name;
    }

    public void setDate_name(String date_name) {
        this.date_name = date_name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
