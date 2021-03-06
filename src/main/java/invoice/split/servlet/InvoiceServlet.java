package invoice.split.servlet;

import invoice.split.service.InvoiceService;
import org.json.JSONArray;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InvoiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        String inv = req.getParameter("inv");

        InvoiceService svc = new InvoiceService();

        JSONArray jo = new JSONArray(svc.get(inv));

        ServletHelper.writeResult(jo, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String inv = req.getParameter("inv");

        InvoiceService svc = new InvoiceService();

        JSONArray jo = null;

        try {
            jo = new JSONArray(svc.save(inv));
        } catch (Exception e) {
            jo = new JSONArray(
                    List.of("ERROR", e.getLocalizedMessage())
            );
        }

        ServletHelper.writeResult(jo, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {

        String inv = req.getParameter("inv");

        InvoiceService svc = new InvoiceService();

        List<String> list = null;

        try {
            svc.delete(inv);
            list = List.of("OK");
        } catch (Exception e) {
            list = List.of("ERROR", e.getLocalizedMessage());
        }

        JSONArray jo = new JSONArray(list);

        ServletHelper.writeResult(jo, resp);
    }
}
