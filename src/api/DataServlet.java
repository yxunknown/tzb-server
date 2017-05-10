package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by mevur on 5/6/2017.
 */
@WebServlet(name = "data", urlPatterns = "/data")
public class DataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getRequestURI();
        System.out.println(data);
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.getParameter("data");
        System.out.println(request.getRequestURI());
        System.out.println(data);
        if (data != null) {
            PrintWriter writer = response.getWriter();
            writer.write(data);
            writer.flush();
            writer.close();
        }
    }
}
