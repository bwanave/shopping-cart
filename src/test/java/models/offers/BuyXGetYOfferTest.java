package models.offers;

import models.offers.BuyXGetYOffer;
import models.offers.Offer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class BuyXGetYOfferTest {

    /**
     * Given:
     * •	An empty shopping cart
     * •	And a product, Dove Soap with a unit price of 39.99 and a associated Buy 2 Get 1 Free offer., tax 12.5
     * •	And a product, Axe Deo with a unit price of 89.99 and no associated offer.
     * When:
     * •	The user adds 3 Dove Soaps to the shopping cart
     * Then:
     * •	The shopping cart should contain 3 Dove Soaps each with a unit price of 39.99
     * •	And the shopping cart's total price should equal 89.98
     * •	And the shopping cart's total discount should equal 39.99
     * •	And the total tax amount should equal 10.00
     */
    @Test
    void shouldReturnDiscountedPriceWhenBuy3Quanities() {
        Offer offer = new BuyXGetYOffer(2, 1);
        BigDecimal discount = offer.getDiscount(3, new BigDecimal("39.99"));
        Assertions.assertEquals(new BigDecimal("39.99"), discount);
    }

    @Test
    void shouldReturnDiscountedPriceWhenBuy6Quanities() {
        Offer offer = new BuyXGetYOffer(2, 1);
        BigDecimal discount = offer.getDiscount(6, new BigDecimal("39.99"));
        Assertions.assertEquals(new BigDecimal("79.98"), discount);
    }

    @Test
    void shouldReturnDiscountedPriceWhenBuy9Quanities() {
        Offer offer = new BuyXGetYOffer(3, 2);
        BigDecimal discount = offer.getDiscount(9, new BigDecimal("39.99"));
        Assertions.assertEquals(new BigDecimal("119.97"), discount);
    }
}