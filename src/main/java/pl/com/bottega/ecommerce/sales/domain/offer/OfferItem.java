/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

    private Product product;
    private Money productPrice;
    private int quantity;
    private Money totalCost;
    private Discount discount;

    public OfferItem(Product product, Money productPrice, int quantity) {
        this(product, productPrice, quantity, null);
    }

    public OfferItem(Product product, Money productPrice, int quantity, Discount discount) {

        this.product = product;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.discount = discount;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null)
            discountValue = discountValue.subtract(discount.getMoney().getValue());

        this.totalCost = new Money(productPrice.getValue().multiply(new BigDecimal(quantity)).subtract(discountValue),
                productPrice.getCurrency());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((discount == null) ? 0 : discount.hashCode());
        result = prime * result + ((product.getProductName() == null) ? 0 : product.getProductName().hashCode());
        result = prime * result + ((productPrice == null) ? 0 : productPrice.hashCode());
        result = prime * result + ((product.getProductId() == null) ? 0 : product.getProductId().hashCode());
        result = prime * result + ((product.getProductType() == null) ? 0 : product.getProductType().hashCode());
        result = prime * result + quantity;
        result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (isNullObject((OfferItem) obj)) return false;
        return true;
    }

    private boolean isNullObject(OfferItem obj) {
        OfferItem other = obj;
        if (discount == null) {
            if (other.discount != null)
                return true;
        } else if (!discount.equals(other.discount))
            return true;
        if (product == null) {
            if (other.product != null)
                return true;
        } else if (!product.equals(other.product))
            return true;
        if (productPrice == null) {
            if (other.productPrice != null)
                return true;
        } else if (!productPrice.equals(other.productPrice))
            return true;
        if (quantity != other.quantity)
            return true;
        if (totalCost == null) {
            if (other.totalCost != null)
                return true;
        } else if (!totalCost.equals(other.totalCost))
            return true;
        return false;
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        if (productPrice == null) {
            if (other.productPrice != null)
                return false;
        } else if (!productPrice.equals(other.productPrice))
            return false;
        if (quantity != other.quantity)
            return false;

        BigDecimal difference = getMaximum(other).subtract(getMinimum(other));
        BigDecimal acceptableDelta = getMaximum(other).multiply(new BigDecimal(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

    private BigDecimal getMaximum(OfferItem other) {
        return totalCost.getValue().compareTo(other.totalCost.getValue()) > 0 ? totalCost.getValue() : other.totalCost.getValue();
    }

    private BigDecimal getMinimum(OfferItem other) {
        return totalCost.getValue().compareTo(other.totalCost.getValue()) > 0 ? other.totalCost.getValue() : totalCost.getValue();
    }

    BigDecimal getDifrence(){
        return null;
    }

    public String getProductId() {
        return product.getProductId();
    }

    public Money getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return product.getProductName();
    }

    public Date getProductSnapshotDate() {
        return product.getProductSnapshotDate();
    }

    public String getProductType() {
        return product.getProductType();
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return productPrice.getCurrency();
    }

    public Discount getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discount.getCause();
    }

    public int getQuantity() {
        return quantity;
    }

}
