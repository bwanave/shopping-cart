package models;

import models.offers.Offer;

import java.math.BigDecimal;
import java.util.Objects;

public final class Product {
    private final String name;
    private final String description;
    private final ProductCategory category;
    private final BigDecimal unitPrice;

    private final Offer offer;

    public Product(String name, String description, ProductCategory category, BigDecimal unitPrice) {
        this(name, description, category, unitPrice, (buyQty, getQty) -> BigDecimal.ZERO);
    }

    public Product(String name, String description, ProductCategory category, BigDecimal unitPrice, Offer offer) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.unitPrice = unitPrice;
        this.offer = offer;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Offer getOffer() {
        return offer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description) && category == product.category && Objects.equals(unitPrice, product.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, category, unitPrice);
    }
}
