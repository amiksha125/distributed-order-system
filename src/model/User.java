package model;

public class User {

    private int userId;
    private String name;
    private String email;

    // constructor for inserting user
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // constructor for fetching user
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
