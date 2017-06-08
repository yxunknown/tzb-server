package dao;

import daoimpl.DatabaseInit;
import model.Alarm;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mevur on 6/7/2017.
 */
public class AlarmAccessLayer {

    private DatabaseInit init;
    private Connection connection;

    public AlarmAccessLayer() {
        try {
            init = DatabaseInit.getInstance();
            connection = init.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database connection error for " + e.getMessage());
        }
    }
    public long insert(Alarm alarm) {
        String up = alarm.getUpPointData();
        up = StringEscapeUtils.escapeJava(up);
        String down = alarm.getDownPointData();
        down = StringEscapeUtils.escapeJava(down);
        String sql = "INSERT INTO tzb.alarm ("
                   + "alarm_time,"
                   + "max_height,"
                   + "up_point_data,"
                   + "down_point_data,"
                   + "source_gps_x,"
                   + "source_gps_y,"
                   + "level,"
                   + "info)"
                   + "VALUES("
                   + "\"" + alarm.getAlarmTime() + "\","
                   + "\"" + alarm.getMaxHeight() + "\","
                   + "\"" + up + "\","
                   + "\"" + down + "\","
                   + "\"" + alarm.getSourceGpsX() + "\","
                   + "\"" + alarm.getSourceGpsY() + "\","
                   + "\"" + alarm.getLevel() + "\","
                   + "\"" + alarm.getInfo() + "\")";
        return new AccessLayer().insert(connection, sql);
    }
    public List<Alarm> query(String sql) {
        List<Alarm> alarms = new ArrayList<>();
        JSONArray array = new AccessLayer().query(connection, sql);
        if (array == null) {
            return null;
        } else {
            try {
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Alarm a = new Alarm();
                    a.setAlarmTime(obj.getString("alarm_time"));
                    a.setMaxHeight(obj.getString("max_height"));
                    a.setUpPointData(obj.getString("up_point_data"));
                    a.setDownPointData(obj.getString("down_point_data"));
                    a.setSourceGpsY(obj.getString("source_gps_y"));
                    a.setSourceGpsX(obj.getString("source_gps_x"));
                    a.setLevel(obj.getString("level"));
                    a.setInfo(obj.getString("info"));
                    alarms.add(a);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                alarms = null;
            }
            return alarms;
        }
    }
}
