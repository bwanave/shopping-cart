package models.offers;

import java.math.BigDecimal;

@FunctionalInterface
public interface Offer {
    BigDecimal getDiscount(int quantity, BigDecimal unitPrice);
}
