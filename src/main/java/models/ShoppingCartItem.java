package models;

public class ShoppingCartItem {
    private final Product product;
    private int quantity;

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ShoppingCartItem(ShoppingCartItem original) {
        this.product = original.product;
        this.quantity = original.quantity;
    }

    Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    void incrementQuantityBy(int quantity) {
        this.quantity += quantity;
    }
}
