import java.util.HashMap;
import java.util.Map;

/**
 * Inventory backed by a HashMap for average O(1) add/update/delete/search
 * operations keyed by productId.
 */
public class Inventory {
    private final Map<String, Product> products = new HashMap<>();

    // O(1) average
    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("Product already exists: " + product.getProductId());
        }
        products.put(product.getProductId(), product);
    }

    // O(1) average
    public void updateProduct(String productId, int newQuantity, double newPrice) {
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + productId);
        }
        product.setQuantity(newQuantity);
        product.setPrice(newPrice);
    }

    // O(1) average
    public void deleteProduct(String productId) {
        if (products.remove(productId) == null) {
            throw new IllegalArgumentException("Product not found: " + productId);
        }
    }

    // O(1) average
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public void printAll() {
        for (Product p : products.values()) {
            System.out.println(p);
        }
    }
}
