package dao;

import daoimpl.DatabaseInit;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mevur on 6/5/2017.
 */
public class UserAccesslayer {
    private DatabaseInit init;
    private Connection connection;
    public UserAccesslayer() {
        try {
            init = DatabaseInit.getInstance();
            connection = init.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection error for " + e.getMessage());
        }
    }

    public long insert(User user) {
        String sql = "INSERT INTO tzb.user (id, username, password) VALUES("
                   + "\"" + user.getId() + "\","
                   + "\"" + user.getUsername() + "\","
                   + "\"" + user.getPassword() + "\")";
        System.out.println(sql);
        return new AccessLayer().insert(connection, sql);
    }

    public List<User> query(String sql) {
        List<User> users = new ArrayList<>();
        JSONArray array = new AccessLayer().query(connection, sql);
        if (array == null) {
            return null;
        } else {
            try {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    User user = new User();
                    user.setId(object.getString("id"));
                    user.setUsername(object.getString("username"));
                    user.setPassword(object.getString("password"));
                    users.add(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
                users = null;
            }
            return users;
        }
    }
}
