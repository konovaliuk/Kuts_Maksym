package enteties;

import java.io.Serializable;
import java.util.Objects;

public class AdditionalService implements Serializable {
    private Integer id;
    private String title;

    public AdditionalService() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        AdditionalService service = (AdditionalService) o;
        return id == service.id &&
                Objects.equals(title, service.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "AdditionalService{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
