package invoice.split.service;

import invoice.split.model.Invoice;
import invoice.split.store.InvoiceRepo;

import java.util.List;

public class InvoiceService {

    private final InvoiceRepo repo = new InvoiceRepo();

    public List<Invoice> get(String name) {
        if (name != null) {
            return repo.getByName(name);
        } else {
            return repo.getAll();
        }
    }

    public List<Invoice> save(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Invoice name not specified.");
        }
        return List.of(repo.save(name));
    }

    public void delete(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Invoice name not specified.");
        }
        repo.delete(name);
    }
}
