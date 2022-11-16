package models;

import java.math.BigDecimal;
import java.util.Objects;

import static utils.Constants.PRECISION;
import static utils.Constants.ROUND_MODE_HALF_UP;

public class Price {
    public static final BigDecimal HUNDRED = new BigDecimal(100);

    private final BigDecimal unitPrice;
    private final BigDecimal tax;

    public Price(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        this.tax = BigDecimal.ZERO;
    }

    public Price(BigDecimal unitPrice, BigDecimal tax) {
        this.unitPrice = unitPrice;
        this.tax = tax;
    }

    BigDecimal getPriceWithTax() {
        BigDecimal taxAmount = this.getTaxAmountOnUnitPrice();
        return unitPrice.add(taxAmount).setScale(PRECISION, ROUND_MODE_HALF_UP);
    }

    BigDecimal getTaxAmountOnUnitPrice() {
        return unitPrice.multiply(tax).divide(HUNDRED, PRECISION, ROUND_MODE_HALF_UP);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Price price = (Price) obj;
        return Objects.equals(unitPrice, price.unitPrice) && Objects.equals(tax, price.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitPrice, tax);
    }
}
