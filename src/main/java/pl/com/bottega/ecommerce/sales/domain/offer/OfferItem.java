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

public class OfferItem {

    private Product productEntry;
    private Discount discountEntry;

    public OfferItem(Product productEntry) {
        this(productEntry, null);
    }

    public OfferItem(Product productEntry, Discount discountEntry) {

        BigDecimal discountValue = new BigDecimal(0);
        if (discountEntry.getDiscount() != null)
            discountValue = discountValue.subtract(discountEntry.getDiscount());

        productEntry.setTotalCost(productEntry.getTotalCost().multiply(new BigDecimal(productEntry.getQuantity())).subtract(discountValue));
        this.productEntry = productEntry;
        this.discountEntry = discountEntry;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((discountEntry.getDiscount() == null) ? 0 : discountEntry.getDiscount().hashCode());
        result = prime * result + ((productEntry.getProductName() == null) ? 0 : productEntry.getProductName().hashCode());
        result = prime * result + ((productEntry.getProductPrice() == null) ? 0 : productEntry.getProductPrice().hashCode());
        result = prime * result
                + ((productEntry.getProductId() == null) ? 0 : productEntry.getProductId().hashCode());
        result = prime * result + ((productEntry.getProductType() == null) ? 0 : productEntry.getProductType().hashCode());
        result = prime * result + productEntry.getQuantity();
        result = prime * result
                + ((productEntry.getTotalCost() == null) ? 0 : productEntry.getTotalCost().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        IsThisSameObject(obj);
        IsntNullObject(obj);
        IsThisSameClass(obj);
        OfferItem other = (OfferItem) obj;
        isValidBigDecimalFiled(discountEntry.getDiscount(),other.discountEntry.getDiscount());
        isValidBigDecimalFiled(productEntry.getProductPrice(),other.productEntry.getProductPrice());
        isValidStringFiled(productEntry.getProductId(),other.productEntry.getProductId());
        isValidStringFiled(productEntry.getProductName(),other.productEntry.getProductName());
        isValidBigDecimalFiled(productEntry.getTotalCost(),other.productEntry.getTotalCost());

        if (productEntry.getProductType() != other.productEntry.getProductType())
            return false;

        if (productEntry.getQuantity() != other.productEntry.getQuantity())
            return false;


        return true;
    }

    private boolean isValidBigDecimalFiled(BigDecimal entity, BigDecimal other) {
        boolean tmp = true;
        if (entity == null) {
            if (other != null)
                tmp = false;
        } else if (!entity.equals(other)) {
            tmp = false;
        }
        return tmp;
    }

    private boolean isValidStringFiled(String entity, String other) {
        boolean tmp = true;
        if (entity == null) {
            if (other != null)
                tmp = false;
        } else if (!entity.equals(other)) {
            tmp = false;
        }
        return tmp;
    }

    private boolean IsThisSameClass(Object obj) {
        return getClass() == obj.getClass();
    }

    private boolean IsntNullObject(Object obj) {
        return obj != null;
    }

    private boolean IsThisSameObject(Object object) {
        return this == object;
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (productEntry.getProductName() == null) {
            if (other.productEntry.getProductName() != null)
                return false;
        } else if (!productEntry.getProductName().equals(other.productEntry.getProductName()))
            return false;
        if (productEntry.getProductPrice() == null) {
            if (other.productEntry.getProductPrice() != null)
                return false;
        } else if (!productEntry.getProductPrice().equals(other.productEntry.getProductPrice()))
            return false;
        if (productEntry.getProductId() == null) {
            if (other.productEntry.getProductId() != null)
                return false;
        } else if (!productEntry.getProductId().equals(other.productEntry.getProductId()))
            return false;
        if (productEntry.getProductType() != other.productEntry.getProductType())
            return false;

        if (productEntry.getQuantity() != other.productEntry.getQuantity())
            return false;

        BigDecimal max, min;
        if (productEntry.getTotalCost().compareTo(other.productEntry.getTotalCost()) > 0) {
            max = productEntry.getTotalCost();
            min = other.productEntry.getTotalCost();
        } else {
            max = other.productEntry.getTotalCost();
            min = productEntry.getTotalCost();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

    public Product getProductEntry() {
        return productEntry;
    }

    public Discount getDiscountEntry() {
        return discountEntry;
    }

}
