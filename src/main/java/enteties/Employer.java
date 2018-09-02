package enteties;

import java.io.Serializable;
import java.util.Objects;

public class Employer implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private Long shipId;
    private String position;

    public Employer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return shipId == employer.shipId &&
                Objects.equals(firstName, employer.firstName) &&
                Objects.equals(lastName, employer.lastName) &&
                Objects.equals(position, employer.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, shipId, position);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", shipId=" + shipId +
                ", position='" + position + '\'' +
                '}';
    }
}
