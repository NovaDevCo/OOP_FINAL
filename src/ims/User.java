package ims;

public abstract class User {
    protected String username;    //Inheritance
    protected String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }
    //Polymorphism
    public abstract void showPermissions();
}