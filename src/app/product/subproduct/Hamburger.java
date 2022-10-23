package app.product.subproduct;

import app.product.Product;

public class Hamburger extends Product {
    private boolean isBurgerSet;
    private int burgerSetPrice;

    public Hamburger(int id, String name, int price, int kcal, boolean burgerSet, int burgerSetPrice) {
        super(id, name, price, kcal);
        this.isBurgerSet = burgerSet;
        this.burgerSetPrice = burgerSetPrice;
    }

    public Hamburger(Hamburger hamburger) {
        super(hamburger.getName(), hamburger.getPrice(), hamburger.getKcal());
        this.isBurgerSet = hamburger.isBurgerSet();
        this.burgerSetPrice = getBurgerSetPrice();
    }

    public boolean isBurgerSet() {
        return isBurgerSet;
    }

    public void setIsBurgerSet(boolean burgerSet) {
        this.isBurgerSet = burgerSet;
    }

    public int getBurgerSetPrice() {
        return burgerSetPrice;
    }
}