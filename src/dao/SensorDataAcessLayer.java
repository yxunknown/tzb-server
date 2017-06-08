package dao;

import daoimpl.DatabaseInit;
import model.SensorData;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        Timestamp st = new Timestamp(System.currentTimeMillis());
        int sensorId = data.getNodeNO();
        int gpsX = data.getGpsX();
        int gpsY = data.getGpsY();
        String dataStr = "";
        try {
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            double[] d = data.getData();
            for (int i = 0; i < 10; i++) {
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
                       + "\"" + st.toString() + "\","
                       + sensorId + ","
                       + "\"" + gpsX + "\","
                       + "\"" + gpsY + "\","
                       + "\"" + dataStr + "\")";
            return new AccessLayer().insert(connection, sql);
        }
    }
    public List<SensorData> query(String sql) {
        List<SensorData> datas = new ArrayList<>();
        JSONArray array = new AccessLayer().query(connection, sql);
        if (array == null) {
            return null;
        } else {
            try {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    SensorData data = new SensorData();
                    data.setHead1("st:");
                    data.setEnd1("end");
                    data.setVervify1(0);
                    data.setGpsX(object.getInt("gps_x"));
                    data.setGpsY(object.getInt("gps_y"));
                    data.setNodeNO(object.getInt("sensor_id"));
                    JSONObject obj = new JSONObject(object.getString("data"));
                    JSONArray dataArray = obj.getJSONArray("data");
                    double[] dataInts = new double[10];
                    for (int j = 0; j < 10; j++) {
                        dataInts[j] = dataArray.getDouble(j);
                    }
                    data.setData(dataInts);
                    datas.add(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                datas = null;
            }
            return datas;

        }
    }
}
