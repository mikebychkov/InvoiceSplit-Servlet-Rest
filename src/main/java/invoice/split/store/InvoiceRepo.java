package invoice.split.store;

import invoice.split.model.Invoice;
import org.hibernate.query.Query;

import java.util.List;

public class InvoiceRepo {

    public List<Invoice> getAll() {
        return StorageHelper.execGet(
                session -> session.createQuery("FROM invoice.split.model.Invoice ORDER BY name").list()
        );
    }

    public List<Invoice> getByName(String name) {
        String filter = "WHERE name = :name";
        return StorageHelper.execGet(session -> {
                Query q = session.createQuery("FROM invoice.split.model.Invoice " + filter + " ORDER BY name");
                q.setParameter("name", name);
                return q.list();
            }
        );
    }

    public Invoice save(String name) {
        Invoice inv = new Invoice(name);
        StorageHelper.exec(session -> session.saveOrUpdate(inv));
        return inv;
    }

    public void delete(String name) {
        Invoice inv = new Invoice(name);
        StorageHelper.exec(session -> session.delete(inv));
    }
}
