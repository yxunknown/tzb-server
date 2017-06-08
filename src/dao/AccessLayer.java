package dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.*;
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
    public JSONArray query(Connection connection, String sql) {
        JSONArray result = new JSONArray();
        JSONObject object = new JSONObject();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            ResultSetMetaData metaData = set.getMetaData();
            int columns = metaData.getColumnCount();
            while (set.next()) {
                object = new JSONObject();
                for (int i = 0; i < columns; i++) {
                    object.put(metaData.getColumnName(i + 1),
                            set.getString(i + 1));
                }
                result.put(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            result = null;
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }
}
