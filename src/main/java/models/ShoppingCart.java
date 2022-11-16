package models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Constants.PRECISION;
import static utils.Constants.ROUND_MODE_HALF_UP;

public class ShoppingCart {
    private final Map<Product, ShoppingCartItem> cartItems;
    private BigDecimal totalPrice;
    private BigDecimal totalTaxAmount;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
        this.totalPrice = BigDecimal.ZERO;
        this.totalTaxAmount = BigDecimal.ZERO;
    }

    public void addProduct(Product product, int quantity) {
        if (cartItems.containsKey(product))
            cartItems.get(product).incrementQuantityBy(quantity);
        else {
            ShoppingCartItem cartItem = new ShoppingCartItem(product, quantity);
            cartItems.put(product, cartItem);
        }
        calculatePriceAndTaxAmount();
    }

    public List<ShoppingCartItem> getCartItems() {
        return cartItems.values().stream()
                        .map(ShoppingCartItem::new)
                        .collect(Collectors.toList());
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    private void calculatePriceAndTaxAmount() {
        totalPrice = BigDecimal.ZERO;
        totalTaxAmount = BigDecimal.ZERO;
        for (ShoppingCartItem cartItem : cartItems.values()) {
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
            totalTaxAmount = totalTaxAmount.add(cartItem.getTotalTaxAmount());
        }
        this.totalPrice = this.totalPrice.setScale(PRECISION, ROUND_MODE_HALF_UP);
        this.totalTaxAmount = this.totalTaxAmount.setScale(PRECISION, ROUND_MODE_HALF_UP);
    }
}
