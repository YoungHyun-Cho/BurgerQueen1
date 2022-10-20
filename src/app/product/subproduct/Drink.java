package app.product.subproduct;

import app.product.Product;

public class Drink extends Product {
    private boolean straw;

    public Drink(int id, String name, int price, int kcal, boolean straw) {
        super(id, name, price, kcal);
        this.straw = straw;
    }

    public boolean hasStraw() {
        return straw;
    }

    public void setStraw(boolean straw) {
        this.straw = straw;
    }

    @Override
    public String toString() {
        return super.toString() + "Drink{" +
                "straw=" + straw +
                '}';
    }
}