package ims;

public class CategorySearch implements SearchStrategy {
    private final String category;

    public CategorySearch(String category) {
        this.category = category.toLowerCase();
    }

    //Polymorphism
    @Override
    public boolean matches(Product product) {
        return product.getCategory().toLowerCase().contains(category);
    }
}