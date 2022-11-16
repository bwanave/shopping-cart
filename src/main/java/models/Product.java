package models;

import java.math.BigDecimal;
import java.util.Objects;

public final class Product {
    private final String name;
    private final String description;
    private final ProductCategory category;
    private final Price price;

    public Product(String name, String description, ProductCategory category, Price price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    BigDecimal getPriceWithTax() {
        return price.getPriceWithTax();
    }

    BigDecimal getTaxAmountOnUnitPrice() {
        return price.getTaxAmountOnUnitPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(category, product.category) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, category, price);
    }
}
