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
        System.out.println("[๐ป] ๋ฉ๋ด");
        System.out.println("-".repeat(60));

        printHamburgers(false);
        printSides(false);
        printDrinks(false);

        System.out.println();
        System.out.println("๐งบ (0) ์ฅ๋ฐ๊ตฌ๋");
        System.out.println("๐ฆ (+) ์ฃผ๋ฌธํ๊ธฐ");
        System.out.println("-".repeat(60));
        System.out.print("[๐ฃ] ๋ฉ๋ด๋ฅผ ์ ํํด์ฃผ์ธ์ : ");
    }

    public void printHamburgers(boolean isBurgerSet) {
        System.out.println("๐ ํ๋ฒ๊ฑฐ");
        for (Product product : products) {
            if (product instanceof Hamburger) printEachMenu(product, isBurgerSet);
        }
        System.out.println();
    }

    public void printDrinks(boolean isBurgerSet) {
        System.out.println("๐ฅค ์๋ฃ");
        for (Product product : products) {
            if (product instanceof Drink) printEachMenu(product, isBurgerSet);
        }
        System.out.println();
    }

    public void printSides(boolean isBurgerSet) {
        System.out.println("๐ ์ฌ์ด๋");
        for (Product product : products) {
            if (product instanceof Side) printEachMenu(product, isBurgerSet);
        }
        System.out.println();
    }

    private static void printEachMenu(Product product, boolean isBurgerSet) {
        if (isBurgerSet) System.out.printf("   (%d) %s %5dKcal\n", product.getId(), product.getName(), product.getKcal());
        else System.out.printf("   (%d) %s %5dKcal %5d์\n", product.getId(), product.getName(), product.getKcal(), product.getPrice());
    }
}
