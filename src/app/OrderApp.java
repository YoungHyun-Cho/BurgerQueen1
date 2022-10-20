package app;

import app.product.InMemoryProductRepository;
import app.product.Product;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {

    private Scanner scanner = new Scanner(System.in);
    private ProductRepository productRepository;
    private Product[] products = productRepository.getAllProducts();
    private Menu menu = new Menu(products);
    private Cart cart = new Cart(menu, productRepository);

    public OrderApp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void start() {

        System.out.println("🍔 BurgerQueen Order Service");

        while (true) {
            menu.printMenu();

            int input = Integer.parseInt(scanner.nextLine());

            if (input == 0) cart.printCart();
            else if (1 <= input && input <= products.length) cart.addToCart(input);

            if (cart.isOrdered()) break;
        }
    }
}
