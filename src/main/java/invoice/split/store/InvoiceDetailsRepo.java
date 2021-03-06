package invoice.split.store;

import invoice.split.model.InvoiceDetails;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailsRepo {

    public List<InvoiceDetails> getByInvoice(String invoice) {
        String filter = "WHERE invoice = :invoice";
        return StorageHelper.execGet(session -> {
                    Query q = session.createQuery("FROM invoice.split.model.InvoiceDetails " + filter + " ORDER BY usr");
                    q.setParameter("invoice", invoice);
                    return q.list();
                }
        );
    }

    public List<InvoiceDetails> getByInvoiceGrouped(String invoice) {
        String groupBy = " GROUP BY id, invoice, usr ";
        String filter = " WHERE invoice = :invoice ";
        return StorageHelper.execGet(session -> {
                    NativeQuery q = session.createSQLQuery("SELECT id, invoice, usr, SUM(amount) AS amount FROM invoice_details "
                            + filter
                            + groupBy
                            + " ORDER BY usr");
                    q.setParameter("invoice", invoice);

                    List<InvoiceDetails> details = new ArrayList<>();

                    List<Object[]> rsl = q.getResultList();
                    rsl.forEach((record) -> {
                        int id = (Integer) record[0];
                        String inv = (String) record[1];
                        String usr = (String) record[2];
                        double sum = (Double) record[3];
                        details.add(
                                InvoiceDetails.of(id, inv, usr, "", sum)
                        );
                    });

                    return details;
                }
        );
    }

    public InvoiceDetails save(InvoiceDetails details) {
        StorageHelper.exec(session -> session.saveOrUpdate(details));
        return details;
    }

    public void delete(int id) {
        String filter = "WHERE id = :id";
        StorageHelper.exec(session -> {
                    Query q = session.createQuery("DELETE FROM invoice.split.model.InvoiceDetails " + filter);
                    q.setParameter("id", id);
                    q.executeUpdate();
                }
        );
    }

    public void delete(String inoice) {
        String filter = "WHERE inoice = :inoice";
        StorageHelper.exec(session -> {
                    Query q = session.createQuery("DELETE FROM invoice.split.model.InvoiceDetails " + filter);
                    q.setParameter("inoice", inoice);
                    q.executeUpdate();
                }
        );
    }
}
