package enteties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Excursion implements Serializable {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Long portId;

    public Excursion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tile) {
        this.title = tile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Excursion excursion = (Excursion) o;
        return portId == excursion.portId &&
                Objects.equals(title, excursion.title) &&
                Objects.equals(description, excursion.description) &&
                Objects.equals(price, excursion.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, portId);
    }

    @Override
    public String toString() {
        return "Excursion{" +
                "id=" + id +
                ", tile='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", portId=" + portId +
                '}';
    }
}
