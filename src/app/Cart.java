package app;

import app.product.ProductRepository;
import app.product.Product;
import app.product.subproduct.BurgerSet;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;

public class Cart {

    private Scanner scanner = new Scanner(System.in);
    private Product[] items = new Product[0];
    private Menu menu;
    private ProductRepository productRepository;

    public Cart(Menu menu, ProductRepository productRepository) {
        this.menu = menu;
        this.productRepository = productRepository;
    }

    public void addToCart(int productId) {
        Product product = productRepository.getProduct(productId);
        chooseOption(product);

        if (product instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) product;
            if (hamburger.isBurgerSet()) product = composeSet(hamburger);
        }

        Product newProduct;

        if (product instanceof Hamburger) newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side) newProduct = new Side((Side) product);
        else if (product instanceof Drink) newProduct = new Drink((Drink) product);
        else newProduct = new BurgerSet((BurgerSet) product);

        Product[] newItems = new Product[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[newItems.length - 1] = newProduct;
        items = newItems;
        System.out.printf("[ð£] %së¥¼(ì) ì¥ë°êµ¬ëì ë´ììµëë¤.\n", product.getName());
    }

    private void chooseOption(Product product) {

        String input;

        if (product instanceof Hamburger) {
            System.out.printf(
                    "ë¨íì¼ë¡ ì£¼ë¬¸íìê² ì´ì? (1)_ë¨í(%dì) (2)_ì¸í¸(%dì)\n",
                    product.getPrice(),
                    ((Hamburger) product).getBurgerSetPrice()
            );
            input = scanner.nextLine();
            if (input.equals("2")) ((Hamburger) product).setIsBurgerSet(true);
        }
        else if (product instanceof Side) {
            System.out.println("ì¼ì²©ì ëªê°ê° íìíì ê°ì?");
            input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        }
        else if (product instanceof Drink) {
            System.out.println("ë¹¨ëê° íìíì ê°ì? (1)_ì (2)_ìëì¤");
            input = scanner.nextLine();
            if (input.equals("2")) ((Drink) product).setHasStraw(false);
        }
    }


    private BurgerSet composeSet(Hamburger hamburger) {
        System.out.println("ì¬ì´ëë¥¼ ê³¨ë¼ì£¼ì¸ì");
        menu.printSides(true);

        String sideInput = scanner.nextLine();
        Side side = (Side) productRepository.getProduct(Integer.parseInt(sideInput));
        chooseOption(side);

        System.out.println("ìë£ë¥¼ ê³¨ë¼ì£¼ì¸ì.");
        menu.printDrinks(true);

        String selectDrink = scanner.nextLine();
        Drink drink = (Drink) productRepository.getProduct(Integer.parseInt(selectDrink));
        chooseOption(drink);

        String name = hamburger.getName() + "ì¸í¸";
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }

    public void printCart() {
        System.out.println("ð§º ì¥ë°êµ¬ë");
        System.out.println("-".repeat(60));
        printDetails();
        System.out.println("-".repeat(60));
        System.out.printf("í©ê³ : %dì\n", getTotalPrice());

        System.out.println("ì´ì ì¼ë¡ ëìê°ë ¤ë©´ ìí°ë¥¼ ëë¥´ì¸ì. ");
        scanner.nextLine();
    }

    public void printDetails() {

        for (Product product : items) {
            if (product instanceof BurgerSet) {
                BurgerSet burgerSet = (BurgerSet) product;
                System.out.printf(
                        "  %s %6dì (%s(ì¼ì²© %dê°), %s(ë¹¨ë %s))\n",
                        product.getName(),
                        product.getPrice(),
                        burgerSet.getSide().getName(),
                        burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(),
                        burgerSet.getDrink().hasStraw() ? "ìì" : "ìì"
                );
            }
            else if (product instanceof Hamburger) {
                System.out.printf(
                        "  %-8s %6dì (ë¨í)\n",
                        product.getName(),
                        product.getPrice()
                );
            }
            else if (product instanceof Side) {
                System.out.printf(
                        "  %-8s %6dì (ì¼ì²© %dê°)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Side) product).getKetchup()
                );
            }
            else if (product instanceof Drink) {
                System.out.printf(
                        "  %-8s %6dì (ë¹¨ë %s)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Drink) product).hasStraw() ? "ìì" : "ìì"
                );
            }
        }
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : items) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}