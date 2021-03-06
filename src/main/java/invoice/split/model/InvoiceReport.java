package invoice.split.model;

import java.util.ArrayList;
import java.util.List;

public class InvoiceReport {

    private String invoice;
    private List<Debt> debts = new ArrayList<>();

    public InvoiceReport(String invoice) {
        this.invoice = invoice;
    }

    public void addDebt(Debt debt) {
        debts.add(debt);
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public void setDebts(List<Debt> debts) {
        this.debts = debts;
    }
}
