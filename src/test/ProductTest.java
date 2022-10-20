package test;

import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class ProductTest {
    public static void main(String[] args) {
        Hamburger hamburger = new Hamburger(1, "새우버거", 3500, 500, false, 4500);
        Side side = new Side(3, "감자튀김", 1000, 300, 1);
        Drink drink = new Drink(5, "코카콜라", 1000, 200, true);

        System.out.println(hamburger);
        System.out.println(side);
        System.out.println(drink);
    }
}