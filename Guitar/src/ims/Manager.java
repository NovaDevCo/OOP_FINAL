package ims;

public class Manager extends User {  //Inheritance
    public Manager(String username) {
        super(username, "Manager");
    }

    //Polymorphism
    @Override
    public void showPermissions() {
        System.out.println("Manager can View, Add, Edit, Search Products, View Logs.");
    }
}