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
    private final BigDecimal taxRate;
    private BigDecimal totalRawPrice;

    public ShoppingCart() {
        this(BigDecimal.ZERO);
    }

    public ShoppingCart(BigDecimal taxRate) {
        this.cartItems = new HashMap<>();
        this.taxRate = taxRate;
        this.totalRawPrice = BigDecimal.ZERO;
    }

    public void addProduct(Product product, int quantity) {
        if (cartItems.containsKey(product)) cartItems.get(product).incrementQuantityBy(quantity);
        else {
            ShoppingCartItem cartItem = new ShoppingCartItem(product, quantity);
            cartItems.put(product, cartItem);
        }
        this.totalRawPrice = totalRawPrice.add(product.getUnitPrice().multiply(new BigDecimal(quantity)));
    }

    public List<ShoppingCartItem> getCartItems() {
        return cartItems.values().stream().map(ShoppingCartItem::new).collect(Collectors.toList());
    }

    public BigDecimal getTotalPrice() {
        return totalRawPrice.subtract(getTotalDiscount()).add(getTotalTaxAmount());
    }

    public BigDecimal getTotalTaxAmount() {
        return this.totalRawPrice.subtract(getTotalDiscount())
                                 .multiply(taxRate)
                                 .divide(new BigDecimal("100"), PRECISION, ROUND_MODE_HALF_UP);
    }

    public BigDecimal getTotalDiscount() {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        for (ShoppingCartItem cartItem : this.cartItems.values()) {
            Product product = cartItem.getProduct();
            BigDecimal discount = product.getOffer().getDiscount(cartItem.getQuantity(), product.getUnitPrice());
            totalDiscount = totalDiscount.add(discount);
        }
        return totalDiscount;
    }
}
