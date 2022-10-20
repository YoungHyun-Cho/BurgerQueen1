package app.product;

public interface ProductRepository {
    Product getProduct(int productId);
    Product[] getAllProducts();
}
