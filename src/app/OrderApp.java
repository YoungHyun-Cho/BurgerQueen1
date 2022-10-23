package app;

import app.discount.Discount;
import app.product.ProductRepository;
import app.product.Product;

import java.util.Scanner;

public class OrderApp {

    private Discount discount;

    public OrderApp(Discount discount) {
        this.discount = discount;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        ProductRepository productRepository = new ProductRepository();
        Product[] products = productRepository.getAllProducts();
        Menu menu = new Menu(products);
        Cart cart = new Cart(menu, productRepository);
        Order order = new Order(cart, discount);
        boolean isOrdered = false;

        System.out.println("🍔 BurgerQueen Order Service");

        while (true) {
            menu.printMenu();

            String input = scanner.nextLine();

            if (input.equals("+")) isOrdered = order.makeOrder();
            else {
                int menuNumber = Integer.parseInt(input);

                if (menuNumber == 0) cart.printCart();
                else if (1 <= menuNumber && menuNumber <= products.length) cart.addToCart(menuNumber);
            }

            if (isOrdered) break;
        }
    }
}
