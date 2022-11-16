package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static models.ProductCategory.SOAP;

class ShoppingCartItemTest {

    @Test
    @DisplayName("Test the product in Cart Item")
    void shouldTestProductInCartItem() {
        BigDecimal taxRate = new BigDecimal("12.5");
        Product doveSoap = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new Price(new BigDecimal("39.99"), taxRate));
        ShoppingCartItem cartItem = new ShoppingCartItem(doveSoap, 1);
        Assertions.assertEquals(doveSoap, cartItem.getProduct());
    }

    @Test
    @DisplayName("Test the product quantity in Cart Item")
    void shouldTestTheQuantityInCartTime() {
        BigDecimal taxRate = new BigDecimal("12.5");
        Product axeDeo = new Product("Axe Deo", "Axe Deo Body Spray", SOAP, new Price(new BigDecimal("99.99"), taxRate));
        ShoppingCartItem cartItem = new ShoppingCartItem(axeDeo, 1);
        Assertions.assertEquals(1, cartItem.getQuantity());
    }

    @Test
    @DisplayName("Test total price calculation")
    void shouldTestTotalPriceCalculation() {
        BigDecimal taxRate = new BigDecimal("13.5");
        Product axeDeo = new Product("Axe Deo", "Axe Deo Body Spray", SOAP, new Price(new BigDecimal("55.99"), taxRate));
        ShoppingCartItem cartItem = new ShoppingCartItem(axeDeo, 1);
        Assertions.assertEquals(new BigDecimal("63.55"), cartItem.getTotalPrice());
    }

    @Test
    @DisplayName("Test tax amount on unit price calculation")
    void shouldTestTaxAmountCalculation() {
        BigDecimal taxRate = new BigDecimal("11.5");
        Product axeDeo = new Product("Axe Deo", "Axe Deo Body Spray", SOAP, new Price(new BigDecimal("101.99"), taxRate));
        ShoppingCartItem cartItem = new ShoppingCartItem(axeDeo, 1);
        Assertions.assertEquals(new BigDecimal("11.73"), cartItem.getTotalTaxAmount());
    }

    @Test
    @DisplayName("Test total price and tax recalculation when quantity increases")
    void shouldRecalculateTotalPriceAndTaxWhenQuantityIncreases() {
        BigDecimal taxRate = new BigDecimal("12.5");
        Product doveSoap = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new Price(new BigDecimal("39.99"), taxRate));
        ShoppingCartItem cartItem = new ShoppingCartItem(doveSoap, 1);
        Assertions.assertEquals(new BigDecimal("44.99"), cartItem.getTotalPrice());
        Assertions.assertEquals(new BigDecimal("5.00"), cartItem.getTotalTaxAmount());
        cartItem.incrementQuantityBy(1);
        Assertions.assertEquals(new BigDecimal("89.98"), cartItem.getTotalPrice());
        Assertions.assertEquals(new BigDecimal("10.00"), cartItem.getTotalTaxAmount());
    }
}