package models;

import models.offers.BuyXGetYOffer;
import models.offers.Offer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static models.ProductCategory.DEO;
import static models.ProductCategory.SOAP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShoppingCartTest {

    /**
     * Given:
     * <p>
     * An empty shopping cart.
     * <p>
     * And a product, Dove Soap with a unit price of 39.99
     * <p>
     * <p>
     * When:
     * <p>
     * The user adds 5 Dove Soaps to the shopping cart
     * <p>
     * <p>
     * Then:
     * <p>
     * The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99
     * <p>
     * And the shopping cart’s total price should equal 199.95
     */
    @Test
    @DisplayName("Adds 5 Dove Soaps to the empty shopping cart, then assert quantity and total price")
    void shouldAdd5DoveSoapsToTheEmptyCart() {
        //Given:
        ShoppingCart shoppingCart = new ShoppingCart();
        Product doveSoap = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new BigDecimal("39.99"));

        //When:
        shoppingCart.addProduct(doveSoap, 5);

        //Then:
        ShoppingCartItem cartItem = getShoppingCartItemByProduct(doveSoap, shoppingCart);
        assertEquals(5, cartItem.getQuantity(), "Shopping Cart has invalid quantity of Dove Soaps");
        assertEquals(new BigDecimal("199.95"), shoppingCart.getTotalPrice(), "Total price of Shopping Cart is not correct");
    }


    /**
     * Given:
     * <p>
     * An empty shopping cart
     * <p>
     * And a product, Dove Soap with a unit price of 39.99
     * <p>
     * <p>
     * When:
     * <p>
     * The user adds 5 Dove Soaps to the shopping cart
     * <p>
     * And then adds another 3 Dove Soaps to the shopping cart
     * <p>
     * <p>
     * Then:
     * <p>
     * The shopping cart should contain 8 Dove Soaps each with a unit price of 39.99
     * <p>
     * And the shopping cart’s total price should equal 319.92
     */
    @Test
    @DisplayName("Adds first 5 and then 3 Dove Soaps to the empty shopping cart, then assert quantity and total price")
    void shouldAdd5And3DoveSoapsToTheEmptyCart() {
        //Given:
        ShoppingCart shoppingCart = new ShoppingCart();
        Product doveSoap = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new BigDecimal("39.99"));

        //When:
        shoppingCart.addProduct(doveSoap, 5);
        shoppingCart.addProduct(doveSoap, 3);

        //Then:
        ShoppingCartItem cartItem = getShoppingCartItemByProduct(doveSoap, shoppingCart);
        assertEquals(8, cartItem.getQuantity(), "Shopping Cart has invalid quantity of Dove Soaps");
        assertEquals(new BigDecimal("319.92"), shoppingCart.getTotalPrice(), "Total price of Shopping Cart is not correct");
    }

    /**
     * Given:
     * <p>
     * An empty shopping cart
     * <p>
     * And a product, Dove Soap with a unit price of 39.99
     * <p>
     * And another product, Axe Deo with a unit price of 99.99
     * <p>
     * And a tax rate of 12.5%
     * <p>
     * <p>
     * When:
     * <p>
     * The user adds 2 Dove Soaps to the shopping cart
     * <p>
     * And then adds 2 Axe Deo’s to the shopping cart
     * <p>
     * <p>
     * Then:
     * <p>
     * The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99
     * <p>
     * And the shopping cart should contain 2 Axe Deo’s each with a unit price of 99.99
     * <p>
     * And the total tax amount should equal 35.00
     * <p>
     * And the shopping cart’s total price should equal 314.96
     */
    @Test
    @DisplayName("Adds 2 Dove Soaps and 2 Axe Deo's to the empty shopping cart, then assert quantity, tax amount and total price")
    void shouldAdd2DoveSoapsAnd2AxeDeoToTheEmptyCart() {
        //Given:
        BigDecimal taxRate = new BigDecimal("12.5");
        ShoppingCart shoppingCart = new ShoppingCart(taxRate);
        Product doveSoap = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new BigDecimal("39.99"));
        Product axeDeo = new Product("Axe Deo", "Axe Deo Body Spray", DEO, new BigDecimal("99.99"));

        //When:
        shoppingCart.addProduct(doveSoap, 2);
        shoppingCart.addProduct(axeDeo, 2);

        //Then:
        ShoppingCartItem doveSoapCartItem = getShoppingCartItemByProduct(doveSoap, shoppingCart);
        assertEquals(2, doveSoapCartItem.getQuantity(), "Shopping Cart has invalid quantity of Dove Soaps");
        ShoppingCartItem axeDeoCartItem = getShoppingCartItemByProduct(axeDeo, shoppingCart);
        assertEquals(2, axeDeoCartItem.getQuantity(), "Shopping Cart has invalid quantity of Axe Deo");
        assertEquals(new BigDecimal("35.00"), shoppingCart.getTotalTaxAmount(), "Total tax amount of Shopping Cart is not correct");
        assertEquals(new BigDecimal("314.96"), shoppingCart.getTotalPrice(), "Total price of Shopping Cart is not correct");
    }

    /**
     * Step 4: Add products to the shopping cart, which have "Buy X, Get Y free" offer.
     * <p>
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
    void shouldAddProductsToEmptyShoppingCartAndReturnDiscountedPrice() {
        //Given:
        BigDecimal taxRate = new BigDecimal("12.5");
        ShoppingCart shoppingCart = new ShoppingCart(taxRate);
        Offer offer = new BuyXGetYOffer(2, 1);
        Product doveSoap = new Product("Dove Soap", "Dove Beauty Bar Soap (135 g)", SOAP, new BigDecimal("39.99"), offer);
        Product axeDeo = new Product("Axe Deo", "Axe Deo Body Spray", DEO, new BigDecimal("89.99"));

        //When:
        shoppingCart.addProduct(doveSoap, 3);

        //Assert
        Assertions.assertEquals(new BigDecimal("89.98"), shoppingCart.getTotalPrice());
        Assertions.assertEquals(new BigDecimal("39.99"), shoppingCart.getTotalDiscount());
        Assertions.assertEquals(new BigDecimal("10.00"), shoppingCart.getTotalTaxAmount());
    }

    private ShoppingCartItem getShoppingCartItemByProduct(Product product, ShoppingCart shoppingCart) {
        List<ShoppingCartItem> cartItems = shoppingCart.getCartItems();
        Optional<ShoppingCartItem> optionalCartItem = cartItems.stream()
                                                               .filter(item -> item.getProduct().equals(product))
                                                               .findFirst();
        assertTrue(optionalCartItem.isPresent(), "Dove Soap product doesn't present in Shopping Cart");
        return optionalCartItem.get();
    }


}