package invoice.split.servlet;

import invoice.split.service.InvoiceReportService;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InvoiceReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String inv = req.getParameter("inv");

        InvoiceReportService svc = new InvoiceReportService();

        JSONArray jo = new JSONArray(svc.get(inv));

        ServletHelper.writeResult(jo, resp);
    }
}
