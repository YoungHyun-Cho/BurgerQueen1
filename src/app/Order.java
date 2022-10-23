package app;

import app.discount.Discount;

public class Order {
    private Cart cart;
    private Discount discount;
    private int price;

    public Order(Discount discount) {
        this.discount = discount;
    }

    private void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean makeOrder(Cart cart) {

        setCart(cart);

        discount.checkAllDiscountConditions();

        int priceBeforeDiscount = cart.getTotalPrice();
        int discountedPrice = discount.discount(priceBeforeDiscount);

        System.out.println("[📣] 주문이 완료되었습니다. ");
        System.out.println("[📣] 주문 내역은 다음과 같습니다. ");
        System.out.println("-".repeat(60));

        cart.printDetails();

        System.out.println("-".repeat(60));
        System.out.printf("금액 합계      : %d원\n", priceBeforeDiscount);
        System.out.printf("할인 적용 금액 : %d원\n", discountedPrice);

        return true;
    }
}
