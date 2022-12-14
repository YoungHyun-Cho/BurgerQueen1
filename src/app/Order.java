package app;

import app.discount.Discount;

public class Order {
    private Cart cart;
    private Discount discount;
    private int finalPrice;

    public Order(Cart cart, Discount discount) {
        this.cart = cart;
        this.discount = discount;
    }

    private void setCart(Cart cart) {
        this.cart = cart;
    }

    public void makeOrder() {

        setCart(cart);

        discount.checkAllDiscountConditions();

        int totalPrice = cart.getTotalPrice();
        finalPrice = discount.discount(totalPrice);

        System.out.println("[ð£] ì£¼ë¬¸ì´ ìë£ëììµëë¤. ");
        System.out.println("[ð£] ì£¼ë¬¸ ë´ì­ì ë¤ìê³¼ ê°ìµëë¤. ");
        System.out.println("-".repeat(60));

        cart.printDetails();

        System.out.println("-".repeat(60));
        System.out.printf("ê¸ì¡ í©ê³      : %dì\n", totalPrice);
        System.out.printf("í ì¸ ì ì© ê¸ì¡ : %dì\n", finalPrice);
    }
}
