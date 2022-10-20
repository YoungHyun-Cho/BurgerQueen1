package app;

import app.product.Product;
import app.product.ProductRepository;
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
    private boolean ordered = false;

    public Cart(Menu menu, ProductRepository productRepository) {
        this.menu = menu;
        this.productRepository = productRepository;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void chooseOption(Product product) {

        String input;

        if (product instanceof Hamburger) {
            System.out.printf(
                    "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    product.getPrice(),
                    ((Hamburger) product).getBurgerSetPrice()
            );
            input = scanner.nextLine();
            if (input.equals("2")) ((Hamburger) product).setBurgerSet(true);
        }
        else if (product instanceof Side) {
            System.out.println("케첩은 몇개가 필요하신가요?");
            input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        }
        else if (product instanceof Drink) {
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
            input = scanner.nextLine();
            if (input.equals("2")) ((Drink) product).setStraw(false);
        }
    }

    public void addToCart(int productId) {
        Product newItem = productRepository.getProduct(productId);
        chooseOption(newItem);

        if (newItem instanceof Hamburger) {
            Hamburger hamburger = (Hamburger) newItem;
            if (hamburger.isBurgerSet()) newItem = composeSet(hamburger);
        }

        Product[] newItems = new Product[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[newItems.length - 1] = newItem;
        items = newItems;
        System.out.printf("[📣] %s를(을) 장바구니에 담았습니다.\n", newItem.getName());
    }


    public BurgerSet composeSet(Hamburger hamburger) {
        System.out.println("사이드를 골라주세요");
        menu.printSides(true);

        String sideInput = scanner.nextLine();
        Side side = (Side) productRepository.getProduct(Integer.parseInt(sideInput));
        chooseOption(side);

        System.out.println("음료를 골라주세요.");
        menu.printDrinks(true);

        String selectDrink = scanner.nextLine();
        Drink drink = (Drink) productRepository.getProduct(Integer.parseInt(selectDrink));
        chooseOption(drink);

        String name = hamburger.getName() + "세트";
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }

    public void printCart() {
        System.out.println("🧺 장바구니");
        System.out.println("-".repeat(60));
        printDetails();
        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", getTotalPrice());

        System.out.println("(1)_이전으로 (2)_주문하기");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equals("2")) makeOrder();
    }

    public void printDetails() {

        for (Product product : items) {
            if (product instanceof BurgerSet) {
                BurgerSet burgerSet = (BurgerSet) product;
                System.out.printf(
                        "  %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                        product.getName(),
                        product.getPrice(),
                        burgerSet.getSide().getName(),
                        burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(),
                        burgerSet.getDrink().hasStraw() ? "있음" : "없음"
                );
            }
            else if (product instanceof Hamburger) {
                System.out.printf(
                        "  %-8s %6d원 (단품)\n",
                        product.getName(),
                        product.getPrice()
                );
            }
            else if (product instanceof Side) {
                System.out.printf(
                        "  %-8s %6d원 (케첩 %d개)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Side) product).getKetchup()
                );
            }
            else if (product instanceof Drink) {
                System.out.printf(
                        "  %-8s %6d원 (빨대 %s)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Drink) product).hasStraw() ? "있음" : "없음"
                );
            }
        }
    }

    public void makeOrder() {

        System.out.println("[📣] 주문이 완료되었습니다. ");
        System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
        System.out.println("-".repeat(60));
        printDetails();
        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n\n", getTotalPrice());
        ordered = true;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : items) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
