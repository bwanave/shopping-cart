package models.offers;

import java.math.BigDecimal;

public class BuyXGetYOffer implements Offer {

    private final int buyQuantity;
    private final int getQuantity;

    public BuyXGetYOffer(int buyQuantity, int getQuantity) {
        this.buyQuantity = buyQuantity;
        this.getQuantity = getQuantity;
    }

    @Override
    public BigDecimal getDiscount(int quantity, BigDecimal unitPrice) {
        int minQuantity = buyQuantity + getQuantity;
        if (quantity >= minQuantity) {
            int multiplier = (quantity / minQuantity);
            BigDecimal unitDiscount = new BigDecimal(getQuantity).multiply(unitPrice);
            BigDecimal discount = new BigDecimal(multiplier).multiply(unitDiscount);

            int remainderMultiplier = (quantity % minQuantity - buyQuantity);
            if (remainderMultiplier > 0)
                discount = discount.add(new BigDecimal(remainderMultiplier).multiply(unitPrice));
            return discount;
        }
        else return BigDecimal.ZERO;
    }
}
