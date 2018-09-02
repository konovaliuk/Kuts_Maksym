package enteties;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {
    private Long id;
    private Integer ticketTypeId;
    private Long shipId;
    private Long userId;
    private boolean booked;

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketTypeId == ticket.ticketTypeId &&
                shipId == ticket.shipId &&
                userId == ticket.userId &&
                booked == ticket.booked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketTypeId, shipId, userId, booked);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketTypeId=" + ticketTypeId +
                ", shipId=" + shipId +
                ", userId=" + userId +
                ", booked=" + booked +
                '}';
    }
}
