package head_01.my_contacts;

public class Contact {
    private String listName;
    private String firstName;
    private String email;
    private String gender;
    private String birthDay;
    private String profession;
    private String location;
    private String status;
    private String interests;
    private String seeking;

    public Contact() {
    }

    public Contact(String listName, String firstName, String email, String gender, String birthDay, String profession, String location, String status, String interests, String seeking) {
        this.listName = listName;
        this.firstName = firstName;
        this.email = email;
        this.gender = gender;
        this.birthDay = birthDay;
        this.profession = profession;
        this.location = location;
        this.status = status;
        this.interests = interests;
        this.seeking = seeking;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getSeeking() {
        return seeking;
    }

    public void setSeeking(String seeking) {
        this.seeking = seeking;
    }
}
