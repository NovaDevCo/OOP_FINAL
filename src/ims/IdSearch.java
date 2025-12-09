package ims;

public class IdSearch implements SearchStrategy {
    private final String id;

    public IdSearch(String id) {
        this.id = id;
    }

    @Override
    public boolean matches(Product product) {
        return product.getId().equals(id);
    }
}
