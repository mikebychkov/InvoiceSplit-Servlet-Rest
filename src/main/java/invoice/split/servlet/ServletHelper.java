package invoice.split.servlet;

import org.json.JSONArray;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletHelper {

    public static void writeResult(JSONArray jo, HttpServletResponse resp) {

        resp.setContentType("text/json");

        try (PrintWriter out = resp.getWriter()) {
            out.write(jo.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
