package enteties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class TicketType implements Serializable {
    private Integer id;
    private String ticketType;
    private BigDecimal price;

    public TicketType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return Objects.equals(ticketType, that.ticketType) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketType, price);
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "id=" + id +
                ", ticketType='" + ticketType + '\'' +
                ", price=" + price +
                '}';
    }
}
