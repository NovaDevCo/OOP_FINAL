package ims;

public class NameSearch implements SearchStrategy {
    private final String query;

    public NameSearch(String query) {
        this.query = query.toLowerCase();
    }

    @Override
    public boolean matches(Product product) {
        return product.getName().toLowerCase().contains(query);
    }
}
