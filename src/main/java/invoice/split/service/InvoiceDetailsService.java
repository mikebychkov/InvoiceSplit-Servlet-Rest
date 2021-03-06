package invoice.split.service;

import invoice.split.model.InvoiceDetails;
import invoice.split.store.InvoiceDetailsRepo;

import java.util.List;

public class InvoiceDetailsService {

    private final InvoiceDetailsRepo repo = new InvoiceDetailsRepo();

    public List<InvoiceDetails> get(String invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice name not specified.");
        }
        return repo.getByInvoice(invoice);
    }

    public List<InvoiceDetails> getGrouped(String invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice name not specified.");
        }
        return repo.getByInvoiceGrouped(invoice);
    }

    public List<InvoiceDetails> save(InvoiceDetails details) {
        return List.of(repo.save(details));
    }

    public void delete(Integer id, String invoice) {
        if (id == null || invoice == null) {
            throw new IllegalArgumentException("Invoice details id or invoice name must be specified.");
        }
        if (id != null) {
            repo.delete(id);
        } else if (invoice != null) {
            repo.delete(invoice);
        }
    }
}
