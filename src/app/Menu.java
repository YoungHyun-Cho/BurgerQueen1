package app;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class Menu {

    private Product[] products;

    public Menu(Product[] products) {
        this.products = products;
    }

    public void printMenu() {
        System.out.println("[🔻] 메뉴");
        System.out.println("-".repeat(60));

        printHamburgers(false);
        printSides(false);
        printDrinks(false);

        System.out.println();
        System.out.println("🧺 (0) 장바구니");
        System.out.println("-".repeat(60));
        System.out.print("[📣] 메뉴를 선택해주세요 : ");
    }



    public void printHamburgers(boolean isBurgerSet) {
        System.out.println("🍔 햄버거");
        for (Product product : products) {
            if (product instanceof Hamburger) printEachMenu(product, isBurgerSet);
        }
        System.out.println();
    }

    public void printDrinks(boolean isBurgerSet) {
        System.out.println("🥤 음료");
        for (Product product : products) {
            if (product instanceof Drink) printEachMenu(product, isBurgerSet);
        }
        System.out.println();
    }

    public void printSides(boolean isBurgerSet) {
        System.out.println("🍟 사이드");
        for (Product product : products) {
            if (product instanceof Side) printEachMenu(product, isBurgerSet);
        }
        System.out.println();
    }

    private static void printEachMenu(Product product, boolean isBurgerSet) {

        if (isBurgerSet) {
            System.out.printf(
                    "   (%d) %s %5dKcal\n",
                    product.getId(),
                    product.getName(),
                    product.getKcal()
            );
        }
        else {
            System.out.printf(
                    "   (%d) %s %5dKcal %5d원\n",
                    product.getId(),
                    product.getName(),
                    product.getKcal(),
                    product.getPrice()
            );
        }
    }
}
