package ims;

public class Manager extends User {
    public Manager(String username) {
        super(username, "Manager");
    }

    @Override
    public void showPermissions() {
        System.out.println("Manager can: view, add, edit, search products, view logs.");
    }
}
