package app.product.subproduct;

import app.product.Product;

public class Hamburger extends Product {
    private boolean burgerSet;
    private int burgerSetPrice;

    public Hamburger(int id, String name, int price, int kcal, boolean burgerSet, int burgerSetPrice) {
        super(id, name, price, kcal);
        this.burgerSet = burgerSet;
        this.burgerSetPrice = burgerSetPrice;
    }

    public boolean isBurgerSet() {
        return burgerSet;
    }

    public void setBurgerSet(boolean burgerSet) {
        this.burgerSet = burgerSet;
    }

    public int getBurgerSetPrice() {
        return burgerSetPrice;
    }

    public void setBurgerSetPrice(int burgerSetPrice) {
        this.burgerSetPrice = burgerSetPrice;
    }

    @Override
    public String toString() {
        return super.toString() + "Hamburger{" +
                "burgerSet=" + burgerSet +
                ", burgerSetPrice=" + burgerSetPrice +
                '}';
    }
}