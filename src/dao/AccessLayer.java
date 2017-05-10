package dao;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by mevur on 5/9/2017.
 */
public class AccessLayer {
    public long insert(Connection connection, String sql) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            long result = statement.executeLargeUpdate(sql);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public JSONObject query(Connection connection, String sql) {
        JSONObject object = new JSONObject();
        return object;
    }
}
