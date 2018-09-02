package enteties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Ship implements Serializable {
    private Long id;
    private int humanCapacity;
    private Date startDate;
    private Date endDate;
    private Long routeId;
    private String title;
    private BigDecimal price;

    public Ship(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHumanCapacity() {
        return humanCapacity;
    }

    public void setHumanCapacity(int humanCapacity) {
        this.humanCapacity = humanCapacity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        Ship ship = (Ship) o;
        return humanCapacity == ship.humanCapacity &&
                routeId == ship.routeId &&
                Objects.equals(startDate, ship.startDate) &&
                Objects.equals(endDate, ship.endDate) &&
                Objects.equals(title, ship.title) &&
                Objects.equals(price, ship.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humanCapacity, startDate, endDate, routeId, title, price);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", humanCapacity=" + humanCapacity +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", routeId=" + routeId +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
