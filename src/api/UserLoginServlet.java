package api;

import dao.UserAccesslayer;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.jar.JarException;

/**
 * Created by mevur on 6/5/2017.
 */
@WebServlet(name = "UserLoginServlet", urlPatterns = "/api/login")
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        if (id == null || password == null) {
            response.sendError(403, "parameter is not valid");
        } else {
            String sql = "SELECT * FROM tzb.user WHERE id=\"" + id + "\" AND password=\"" + password + "\"";
            List<User> users = new UserAccesslayer().query(sql);
            JSONObject obj = new JSONObject();
            try {
                if (users.size() > 0) {
                    obj.put("state", "0");
                    obj.put("msg", "");
                } else {
                    obj.put("state", "1");
                    obj.put("msg", "id or password match failed");
                }
                PrintWriter writer =  response.getWriter();
                writer.write(obj.toString());
                writer.flush();
                writer.close();
            } catch (JSONException e) {
                e.printStackTrace();
                response.sendError(500, e.getMessage());
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.sendError(403, "Request method is not allowed");
    }
}
