package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PriceTest {

    @Test
    @DisplayName("Test the price with tax calculation logic")
    void shouldTestThePriceWithTaxCalculation() {
        Price price = new Price(new BigDecimal("99.99"), new BigDecimal("12.5"));
        Assertions.assertEquals(new BigDecimal("112.49"), price.getPriceWithTax(), "Price with tax calculation is not correct");
    }

    @Test
    @DisplayName("Test the tax amount on unit price calculation logic")
    void shouldTestTheTaxAmountOnUnitPriceCalculation() {
        Price price = new Price(new BigDecimal("99.59"), new BigDecimal("12.5"));
        Assertions.assertEquals(new BigDecimal("12.45"), price.getTaxAmountOnUnitPrice(), "Tax amount on unit price calculation is not correct");
    }

    @Test
    @DisplayName("Test equality of two different objects with same state")
    void twoObjectsShouldBeEqual() {
        Price price1 = new Price(new BigDecimal("100.00"), new BigDecimal("12.5"));
        Price price2 = new Price(new BigDecimal("100.00"), new BigDecimal("12.5"));
        boolean isEqual = price1.equals(price2);
        Assertions.assertTrue(isEqual, "Objects should be equal");
    }

    @Test
    @DisplayName("Test equality of two different objects with diff state")
    void twoObjectsShouldNotBeEqual() {
        Price price1 = new Price(new BigDecimal("100.00"), new BigDecimal("12.5"));
        Price price2 = new Price(new BigDecimal("99.00"), new BigDecimal("12.5"));
        boolean isEqual = price1.equals(price2);
        Assertions.assertFalse(isEqual, "Objects should not be equal");
    }
}