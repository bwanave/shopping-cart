package models;

import java.math.BigDecimal;

import static utils.Constants.PRECISION;
import static utils.Constants.ROUND_MODE_HALF_UP;

public class ShoppingCartItem {
    private final Product product;
    private int quantity;
    private BigDecimal totalPrice;
    private BigDecimal totalTaxAmount;

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        calculatePriceAndTaxAmount();
    }

    public ShoppingCartItem(ShoppingCartItem original) {
        this.product = original.product;
        this.quantity = original.quantity;
        this.totalPrice = original.totalPrice;
        this.totalTaxAmount = original.totalTaxAmount;
    }

    Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    BigDecimal getTotalPrice() {
        return totalPrice;
    }

    BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    void incrementQuantityBy(int quantity) {
        this.quantity += quantity;
        calculatePriceAndTaxAmount();
    }

    private void calculatePriceAndTaxAmount() {
        BigDecimal priceWithTax = product.getPriceWithTax();
        BigDecimal taxAmountOnUnitPrice = product.getTaxAmountOnUnitPrice();
        BigDecimal quantityInBigDecimal = new BigDecimal(quantity);
        this.totalPrice = priceWithTax.multiply(quantityInBigDecimal);
        this.totalTaxAmount = taxAmountOnUnitPrice.multiply(quantityInBigDecimal);
        this.totalPrice = this.totalPrice.setScale(PRECISION, ROUND_MODE_HALF_UP);
        this.totalTaxAmount = this.totalTaxAmount.setScale(PRECISION, ROUND_MODE_HALF_UP);
    }
}
