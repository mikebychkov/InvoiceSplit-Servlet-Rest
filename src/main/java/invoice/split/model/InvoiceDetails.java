package invoice.split.model;

import javax.persistence.*;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String invoice;
    private String usr;
    private String name;
    private Double amount;

    public InvoiceDetails() {
    }

    public static InvoiceDetails of(String invoice, String user, String name, double amount) {
        InvoiceDetails rsl = new InvoiceDetails();
        rsl.setInvoice(invoice);
        rsl.setUsr(user);
        rsl.setName(name);
        rsl.setAmount(amount);
        return rsl;
    }

    public static InvoiceDetails of(int id, String invoice, String user, String name, double amount) {
        InvoiceDetails rsl = InvoiceDetails.of(invoice, user, name, amount);
        rsl.setId(id);
        return rsl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
