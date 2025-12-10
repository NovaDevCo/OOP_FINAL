package ims;

import java.util.Scanner;
import java.util.List;

public class Menu {
    private final InventoryManager manager;
    private final Scanner in = new Scanner(System.in);

    public Menu(InventoryManager manager) {
        this.manager = manager;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Product");
            System.out.println("3. Search Product");
            System.out.println("4. Edit Product");
            System.out.println("5. View Logs");
            System.out.println("6. Delete Logs");
            System.out.println("7. Stock Status Check");
            System.out.println("8. Sales Report");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n=== Guitar Accessories Products ===");
                    manager.viewInventory();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    searchProduct();
                    break;
                case 4:
                    editProduct();
                    break;
                case 5:
                    manager.viewLogs();
                    break;
                case 6:
                    deleteLogs();
                    break;
                case 7:
                    manager.stockStatusCheck();
                    break;
                case 8:
                    manager.viewSalesReport(); // new option
                    break;
                case 9: {
                    System.out.println("Exiting System. Thank you.");
                    return;
                }
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addProduct() {
        System.out.print("ID: ");
        String id = in.nextLine();
        System.out.print("Category: ");
        String category = in.nextLine();
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.print("Price: ");
        double price = in.nextDouble();
        System.out.print("Stock: ");
        int stock = in.nextInt();
        in.nextLine();
        Product p = new Product(id, category, name, price, stock);
        manager.addProduct(p);
    }

    private void searchProduct() {
        System.out.print("Search by (1) ID, (2) Name, (3) Category: ");
        int opt = in.nextInt();
        in.nextLine();

        SearchStrategy strategy;
        if (opt == 1) {
            System.out.print("Enter ID: ");
            String id = in.nextLine();
            strategy = new IdSearch(id);
        } else if (opt == 2) {
            System.out.print("Enter Name: ");
            String name = in.nextLine();
            strategy = new NameSearch(name);
        } else {
            System.out.print("Enter Category: ");
            String category = in.nextLine();
            strategy = new CategorySearch(category);
        }

        List<Product> results = manager.search(strategy);
        if (results.isEmpty()) {
            System.out.println("No products found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private void editProduct() {
        System.out.print("Enter Product ID to edit: ");
        String id = in.nextLine();
        Product p = manager.search(new IdSearch(id)).stream().findFirst().orElse(null);

        if (p == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Editing product: " + p.getName());
        System.out.println("Choose field to update:");
        System.out.println("1. Category");
        System.out.println("2. Name");
        System.out.println("3. Price");
        System.out.println("4. Stock");
        System.out.println("5. Sold Quantity");
        System.out.print("Choose an option: ");
        int choice = in.nextInt();
        in.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.print("New Category [" + p.getCategory() + "]: ");
                String category = in.nextLine();
                p.setCategory(category.isEmpty() ? p.getCategory() : category);
            }
            case 2 -> {
                System.out.print("New Name [" + p.getName() + "]: ");
                String name = in.nextLine();
                p.setName(name.isEmpty() ? p.getName() : name);
            }
            case 3 -> {
                System.out.print("New Price [" + p.getPrice() + "]: ");
                double price = in.nextDouble();
                in.nextLine();
                if (price >= 0) p.setPrice(price);
            }
            case 4 -> {
                System.out.print("New Stock [" + p.getStock() + "]: ");
                int stock = in.nextInt();
                in.nextLine();
                if (stock >= 0) p.setStock(stock);
            }
            case 5 -> {
                System.out.print("Add Sold Quantity [" + p.getQtySold() + "]: ");
                int sold = in.nextInt();
                in.nextLine();
                if (sold > 0 && sold <= p.getStock()) {
                    p.addSale(sold); // decreases stock, increases sold
                    System.out.println("Sold updated. Total sales value: ₱" + p.getSalesValue());
                } else {
                    System.out.println("Invalid sold quantity.");
                }
            }
            default -> System.out.println("Invalid choice.");
        }

        manager.log("Edited product: " + p.getId());
        System.out.println("Product updated.");
    }

    private void deleteLogs() {
        System.out.print("Are you sure you want to delete all logs? (Y/N): ");
        String confirm = in.nextLine().trim().toUpperCase();
        if (confirm.equals("Y")) {
            manager.deleteLogs();
            System.out.println("All logs deleted.");
        } else {
            System.out.println("Delete logs cancelled.");
        }
    }
}