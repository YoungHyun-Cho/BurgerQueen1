package app;

import app.product.InMemoryProductRepository;
import app.product.ProductRepository;

public class AppConfigurer {

    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }
}
