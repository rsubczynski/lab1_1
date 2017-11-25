package pl.com.bottega.ecommerce.sales.domain.offer.entry;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

    private String productId;

    private BigDecimal productPrice;

    private String productName;

    private Date productSnapshotDate;

    private String productType;

    private int quantity;

    public Product(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate, String productType, int quantity, BigDecimal totalCost, String currency) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productSnapshotDate = productSnapshotDate;
        this.productType = productType;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.currency = currency;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    private BigDecimal totalCost;

    private String currency;

    public String getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return currency;
    }

    public int getQuantity() {
        return quantity;
    }
}
