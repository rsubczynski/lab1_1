package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;

public class Product {

    private String productId;
    private String productName;
    private Date productSnapshotDate;
    private String productType;

    public Product(String productId, String productName, Date productSnapshotDate, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productSnapshotDate = productSnapshotDate;
        this.productType = productType;
    }

    public String getProductId() {
        return productId;
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

    @Override public boolean equals(Object obj) {
        Boolean isValid = validObject(obj);
        if (isValid != null)
            return isValid;
        return isNullProducts((Product) obj);
    }

    private Boolean validObject(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return null;
    }

    private boolean isNullProducts(Product obj) {
        Product other = obj;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
            return false;
        if (productSnapshotDate == null) {
            if (other.productSnapshotDate != null)
                return false;
        } else if (!productSnapshotDate.equals(other.productSnapshotDate))
            return false;
        if (productType == null) {
            if (other.productType != null)
                return false;
        } else if (!productType.equals(other.productType))
            return false;
        return true;
    }
}

