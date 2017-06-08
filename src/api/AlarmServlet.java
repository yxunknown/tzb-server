package api;

import dao.AlarmAccessLayer;
import model.Alarm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by mevur on 6/8/2017.
 */
@WebServlet(name = "AlarmServlet", urlPatterns = "/api/get/alarm")
public class AlarmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        AlarmAccessLayer a = new AlarmAccessLayer();
        List<Alarm> alarms = a.query("SELECT * FROM tzb.alarm");
        String out = "";
        if (alarms == null || alarms.size() == 0) {
            out = "[]";
        } else {
            out = alarms.toString();
        }
        PrintWriter writer = response.getWriter();
        writer.write(out);
        writer.flush();
        writer.close();
    }
}
