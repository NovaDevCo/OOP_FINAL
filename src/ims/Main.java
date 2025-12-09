package ims;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        AuthService auth = new AuthService();

        System.out.println("=== Inventory System Login ===");
        System.out.print("Username: ");
        String username = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();

        if (auth.authenticate(username, password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            Manager managerUser = new Manager(username);
            managerUser.showPermissions();

            // Seed demo products
            List<Product> products = InventoryData.sampleProducts();
            InventoryManager manager = new InventoryManager(products);

            // Launch menu
            Menu menu = new Menu(manager);
            menu.start();
        } else {
            System.out.println("Invalid credentials. Access denied.");
        }
    }
}
