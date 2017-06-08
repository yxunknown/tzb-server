package api;

import dao.UserAccesslayer;
import model.User;
import org.json.JSONObject;
import org.w3c.dom.UserDataHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by mevur on 6/5/2017.
 */
@WebServlet(name = "UserRegisterServlet", urlPatterns = "/api/register")
public class UserRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            JSONObject out;
            if (id == null || username == null || password == null) {
                out = new JSONObject();
                out.put("state", "1");
                out.put("msg", "parameter is not valid");
                throw new Exception(out.toString());
            } else {
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                UserAccesslayer layer = new UserAccesslayer();
                Long result = layer.insert(user);
                out = new JSONObject();
                if (result > 0) {
                    out.put("state", "0");
                    out.put("msg", "");
                } else {
                    out.put("state", "1");
                    out.put("msg", "register error");
                }
                PrintWriter writer = response.getWriter();
                writer.write(out.toString());
                writer.flush();
                writer.close();
            }
        } catch (Exception e) {
            response.sendError(500, e.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.sendError(403,"Request method is not allowed");
    }
}
