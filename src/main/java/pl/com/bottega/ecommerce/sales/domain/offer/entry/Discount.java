package pl.com.bottega.ecommerce.sales.domain.offer.entry;

import java.math.BigDecimal;

public class Discount {
    private String discountCause;

    private BigDecimal discount;

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Discount(String discountCause, BigDecimal discount) {
        this.discountCause = discountCause;
        this.discount = discount;
    }
}
