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

import pl.com.bottega.ecommerce.sales.domain.offer.entry.Discount;
import pl.com.bottega.ecommerce.sales.domain.offer.entry.Product;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

	private Product product;
	private Discount discount;

	public OfferItem(Product product, Discount discount) {
		this.product = product;
		this.discount = discount;

		BigDecimal discountValue = new BigDecimal(0);
		if (discount != null)
			discountValue = discountValue.subtract(discount.getDiscount());

		product.setTotalCost(product.getProductPrice().multiply(new BigDecimal(product.getQuantity())).subtract(discountValue)) ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((discount.getDiscount() == null) ? 0 : discount.getDiscount().hashCode());
		result = prime * result + ((product.getProductName() == null) ? 0 : product.getProductName().hashCode());
		result = prime * result + ((product.getProductPrice() == null) ? 0 : product.getProductPrice().hashCode());
		result = prime * result
				+ ((product.getProductId() == null) ? 0 : product.getProductId() .hashCode());
		result = prime * result + ((product.getProductType() == null) ? 0 : product.getProductType().hashCode());
		result = prime * result + product.getQuantity();
		result = prime * result
				+ ((product.getTotalCost() == null) ? 0 : product.getTotalCost().hashCode());
		return result;
	}

	public Product getProduct() {
		return product;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferItem other = (OfferItem) obj;
		if (discount.getDiscount() == null) {
			if (other.discount.getDiscount() != null)
				return false;
		} else if (!discount.getDiscount().equals(other.discount.getDiscount()))
			return false;
		if (product.getProductName() == null) {
			if (other.product.getProductName() != null)
				return false;
		} else if (!product.getProductName().equals(other.product.getProductName()))
			return false;
		if (product.getProductPrice() == null) {
			if (other.product.getProductPrice() != null)
				return false;
		} else if (!product.getProductPrice().equals(other.product.getProductPrice()))
			return false;
		if (product.getProductId() == null) {
			if (other.product.getProductId() != null)
				return false;
		} else if (!product.getProductId().equals(other.product.getProductId()))
			return false;
		if (product.getProductType() != other.product.getProductType() )
			return false;
		if (product.getQuantity() != other.product.getQuantity())
			return false;
		if (product.getTotalCost() == null) {
			if (other.product.getTotalCost() != null)
				return false;
		} else if (!product.getTotalCost().equals(other.product.getTotalCost()))
			return false;
		return true;
	}

	/**
	 * 
	 * @param other
	 * @param delta
	 *            acceptable percentage difference
	 * @return
	 */
	public boolean sameAs(OfferItem other, double delta) {
		if (product.getProductName() == null) {
			if (other.product.getProductName() != null)
				return false;
		} else if (!product.getProductName().equals(other.product.getProductName()))
			return false;
		if (product.getProductPrice() == null) {
			if (other.product.getProductPrice() != null)
				return false;
		} else if (!product.getProductPrice().equals(other.product.getProductPrice()))
			return false;
		if (product.getProductId() == null) {
			if (other.product.getProductId() != null)
				return false;
		} else if (!product.getProductId().equals(other.product.getProductId()))
			return false;
		if (product.getProductType() != other.product.getProductType())
			return false;

		if (product.getQuantity() != other.product.getQuantity())
			return false;

		BigDecimal max, min;
		if (product.getTotalCost().compareTo(other.product.getTotalCost()) > 0) {
			max = product.getTotalCost();
			min = other.product.getTotalCost();
		} else {
			max = other.product.getTotalCost();
			min = product.getTotalCost();
		}

		BigDecimal difference = max.subtract(min);
		BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

		return acceptableDelta.compareTo(difference) > 0;
	}

}
