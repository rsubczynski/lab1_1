package pl.com.bottega.ecommerce.sales.domain.offer;

public class Discount {

    private Money money;
    private String cause;

    public Discount(Money money, String cause) {
        this.money = money;
        this.cause = cause;
    }

    public Money getMoney() {
        return money;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Discount other = (Discount) obj;
        if (money == null) {
            if (other.money != null)
                return false;
        } else if (!money.equals(other.money))
            return false;
        if (cause == null) {
            if (other.cause != null)
                return false;
        } else if (!cause.equals(other.cause))
            return false;
        return true;
    }
}

