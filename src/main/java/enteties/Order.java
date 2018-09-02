package enteties;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private Long id;
    private boolean paid;
    private Date purchaseDate;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return paid == order.paid &&
                Objects.equals(purchaseDate, order.purchaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paid, purchaseDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", paid=" + paid +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
