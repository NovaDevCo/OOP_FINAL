package ims;

public class Product {
    private String id;   //Encapsulation
    private String category;
    private String name;
    private String description;
    private int stock;
    private double price;
    private int qtySold;
    private String status;

    public Product(String id, String category, String name, double price, int stock) {
        // Constructor
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.qtySold = 0;
        this.status = stock > 0 ? "Available" : "Not Available";
    }

    // Getters
    public String getId() { return id; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }
    public double getPrice() { return price; }
    public int getQtySold() { return qtySold; }
    public String getStatus() { return status; }

    //New: Sales Value (Revenue)
    public double getSalesValue() {
        return qtySold * price;
    }

    // Setters
    public void setCategory(String category) { this.category = category; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { if (price >= 0) this.price = price; }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
            updateStatus();
        }
    }

    // Decrease stock and increase sold
    public void addSale(int qty) {
        if (qty > 0 && qty <= stock) {
            this.stock -= qty;
            this.qtySold += qty;
            updateStatus();
        }
    }

    private void updateStatus() {
        this.status = stock > 0 ? "Available" : "Not Available";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - ₱%.2f | Stock: %d | Sold: %d | Revenue: ₱%.2f | Status: %s",
                id, name, category, price, stock, qtySold, getSalesValue(), status);
    }
}