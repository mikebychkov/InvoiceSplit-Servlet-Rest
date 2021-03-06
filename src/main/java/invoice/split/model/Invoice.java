package invoice.split.model;

import javax.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    private String name;

    public Invoice() {
    }

    public Invoice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
