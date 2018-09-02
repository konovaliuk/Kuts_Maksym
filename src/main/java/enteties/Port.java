package enteties;

import java.io.Serializable;
import java.util.Objects;

public class Port implements Serializable {
    private Long id;
    private String title;

    public Port() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(title, port.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
