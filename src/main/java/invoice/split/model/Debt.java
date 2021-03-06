package invoice.split.model;

public class Debt {

    private String usr;
    private String to;
    private Double amount;

    public Debt(String usr, String to, Double amount) {
        this.usr = usr;
        this.to = to;
        this.amount = amount;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
