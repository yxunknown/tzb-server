package dao;

import daoimpl.DatabaseInit;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;

/**
 * Created by mevur on 5/9/2017.
 */
public class SensorDataAcessLayer {
    private Connection connection;
    private DatabaseInit init;
    private SimpleDateFormat format;
    public SensorDataAcessLayer() {
        init = DatabaseInit.getInstance();
        try {
            format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            connection = init.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Get database connection failed, for reason:" + e.getMessage());
        }
    }
    public long insert(SensorData data) {
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        int sensorId = data.getNodeNO();
        int gpsX = data.getGpsX();
        int gpsY = data.getGpsY();
        String dataStr = "";
        try {
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            int[] d = data.getData();
            int valid = data.getValidDataNumbers();
            valid = valid > 10 ? 10 : valid;
            for (int i = 0; i < valid; i++) {
                array.put(d[i]);
            }
            object.put("data", array);
            dataStr = object.toString();
            dataStr = StringEscapeUtils.escapeJava(dataStr);
        } catch (JSONException e) {
            e.printStackTrace();
            dataStr = null;
        }
        if (dataStr == null) {
            return -1;
        } else {
            String sql = "INSERT INTO sensor_data (time, sensor_id, gps_x, gps_y, data) values("
                       + "\"" + time + "\","
                       + sensorId + ","
                       + "\"" + gpsX + "\","
                       + "\"" + gpsY + "\","
                       + "\"" + dataStr + "\")";
            return new AccessLayer().insert(connection, sql);
        }
    }
}
