package ims;

import java.util.*;

public class InventoryManager {
    private final List<Product> products;
    private final List<String> logs = new ArrayList<>();

    public InventoryManager(List<Product> products) {
        this.products = products;
    }

    // View all products
    public void viewInventory() {
        products.forEach(System.out::println);
    }

    // Add product
    public void addProduct(Product p) {
        products.add(p);
        log("Added product: " + p.getName());
    }

    // Search using strategy
    public List<Product> search(SearchStrategy strategy) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (strategy.matches(p)) {
                results.add(p);
            }
        }
        return results;
    }

    // Edit product
    public void editProduct(Product p, String category, String name, double price, int stock, int sold) {
        p.setCategory(category);
        p.setName(name);
        p.setPrice(price);
        p.setStock(stock);

        if (sold > 0) {
            p.addSale(sold); // decreases stock and increases qtySold
        }

        log("Edited product: " + p.getId());
    }

    // Stock Status Check
    public void stockStatusCheck() {
        System.out.println("\n=== Stock Status Check ===");
        boolean found = false;

        for (Product p : products) {
            if (p.getStock() == 0) {
                System.out.println("❌ " + p.getName() + " → OUT OF STOCK");
                found = true;
            } else if (p.getStock() > 50) {
                System.out.println("📦 " + p.getName() + " → OVERSTOCK");
                found = true;
            } else if (p.getStock() < 3) {
                System.out.println("⚠️ " + p.getName() + " → LOW STOCK");
                found = true;
            }
        }

        if (!found) {
            System.out.println("✅ All products are within normal stock levels.");
        }

        log("Ran stock status check.");
    }

    // 📊 Sales Report
    public void viewSalesReport() {
        System.out.println("\n=== Sales Report ===");
        double totalRevenue = 0;
        Product topSeller = null;

        for (Product p : products) {
            double salesValue = p.getSalesValue(); // qtySold × price
            System.out.println(p.getName() + " → Sold: " + p.getQtySold() +
                    " | Revenue: ₱" + salesValue);
            totalRevenue += salesValue;

            if (topSeller == null || salesValue > topSeller.getSalesValue()) {
                topSeller = p;
            }
        }

        System.out.println("=== Total Revenue: ₱" + totalRevenue + " ===");
        if (topSeller != null) {
            System.out.println("🏆 Top Seller: " + topSeller.getName() +
                    " | Revenue: ₱" + topSeller.getSalesValue());
        }

        log("Viewed sales report.");
    }

    // Logging
    public void log(String message) {
        logs.add("[" + new Date() + "] " + message);
    }

    public void viewLogs() {
        logs.forEach(System.out::println);
    }

    public void deleteLogs() {
        logs.clear();
        log("All logs deleted by Manager.");
    }
}
