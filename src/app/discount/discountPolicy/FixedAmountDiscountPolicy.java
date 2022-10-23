package app.discount.discountPolicy;

public class FixedAmountDiscountPolicy implements DiscountPolicy {

    private int discountAmount;

    public FixedAmountDiscountPolicy(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public int calculateDiscountedPrice(int price) {
        return price - discountAmount;
    }
}
