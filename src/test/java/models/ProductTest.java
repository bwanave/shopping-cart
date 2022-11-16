package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static models.ProductCategory.DEO;
import static models.ProductCategory.SOAP;

class ProductTest {

    @Test
    @DisplayName("Test equality of two different objects with same state")
    void twoObjectsShouldBeEqual() {
        Product doveSoap1 = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new Price(new BigDecimal("39.99")));
        Product doveSoap2 = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new Price(new BigDecimal("39.99")));
        boolean isEqual = doveSoap1.equals(doveSoap2);
        Assertions.assertTrue(isEqual, "Objects should be equal");
    }

    @Test
    @DisplayName("Test equality of two different objects with diff state")
    void twoObjectsShouldNotBeEqual() {
        Product doveSoap = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new Price(new BigDecimal("39.99")));
        Product axeDeo = new Product("Axe Deo", "Axe Deo Body Spray", DEO, new Price(new BigDecimal("99.99")));
        boolean isEqual = doveSoap.equals(axeDeo);
        Assertions.assertFalse(isEqual, "Objects should not be equal");
    }
}