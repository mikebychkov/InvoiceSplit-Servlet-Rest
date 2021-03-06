package invoice.split.servlet;

import invoice.split.model.InvoiceDetails;
import invoice.split.service.InvoiceDetailsService;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class InvoiceDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String inv = req.getParameter("inv");

        InvoiceDetailsService svc = new InvoiceDetailsService();

        JSONArray jo = new JSONArray(svc.get(inv));

        ServletHelper.writeResult(jo, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String inv = req.getParameter("inv");
        String user = req.getParameter("usr");
        String name = req.getParameter("name");
        double amount = Double.parseDouble(req.getParameter("sum"));

        InvoiceDetailsService svc = new InvoiceDetailsService();

        JSONArray jo = new JSONArray(svc.save(
           InvoiceDetails.of(inv, user, name, amount)
        ));

        ServletHelper.writeResult(jo, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String inv = req.getParameter("inv");
        String user = req.getParameter("usr");
        String name = req.getParameter("name");
        double amount = Double.parseDouble(req.getParameter("sum"));

        InvoiceDetailsService svc = new InvoiceDetailsService();

        JSONArray jo = new JSONArray(svc.save(
                InvoiceDetails.of(id, inv, user, name, amount)
        ));

        ServletHelper.writeResult(jo, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.parseInt(req.getParameter("id"));
        String inv = req.getParameter("inv");

        InvoiceDetailsService svc = new InvoiceDetailsService();

        JSONArray jo = null;

        try {
            svc.delete(id, inv);
            jo = new JSONArray(List.of("OK"));
        } catch (Exception e) {
            jo = new JSONArray(
                    List.of("ERROR", e.getLocalizedMessage())
            );
        }

        ServletHelper.writeResult(jo, resp);
    }
}
