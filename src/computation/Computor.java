package computation;

import com.sun.istack.internal.NotNull;
import model.SensorData;
import dao.SensorDataAcessLayer;
import model.Alarm;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by mevur on 6/4/2017.
 */
public class Computor extends Thread{
    private String stime;

    /**
     * constructor for Computor
     * @param stime start time
     */
    public Computor(@NotNull String stime) {
        this.stime = stime;
    }

    private List<Point> loadData() {
        SensorDataAcessLayer acessLayer = new SensorDataAcessLayer();
        String sql1 = "SELECT * FROM tzb.sensor_data where sensor_id=1 AND time >= " + "\"" + stime + "\"";
        String sql2 = "SELECT * FROM tzb.sensor_data where sensor_id=2 AND time >= " + "\"" + stime + "\"";
        String sql3 = "SELECT * FROM tzb.sensor_data where sensor_id=3 AND time >= " + "\"" + stime + "\"";
        List<SensorData> data1 = acessLayer.query(sql1);
        List<SensorData> data2 = acessLayer.query(sql2);
        List<SensorData> data3 = acessLayer.query(sql3);
        List<Point> points = new ArrayList<>();
        SensorData d = null;
        if (data1 != null && data1.size() > 0) {
            d = data1.get(0);
            double[] ds = d.getData();
            Point p = new Point();
            p.x = 25;
            p.y = max(ds);
            points.add(p);
        }
        if (data2 != null && data2.size() > 0) {
            d = data2.get(0);
            double[] ds = d.getData();
            Point p = new Point();
            p.x = 68;
            p.y = max(ds);
            points.add(p);
        }
        if (data3 != null && data3.size() > 0) {
            d = data3.get(0);
            double[] ds = d.getData();
            Point p = new Point();
            p.x = 120;
            p.y = max(ds);
            points.add(p);
        }

        return points;
    }
    public Alarm compute() {
        List<Point> points = loadData();
        Interpolation interpolation = new Interpolation(points);
        Polynomial polynomial = interpolation.newton();
        if (polynomial == null) {
            return null;
        } else {
            double x = 0;
            JSONArray array = new JSONArray();
            Point max = new Point();
            try {
                while (x < 120) {
                    Point p = new Point();
                    p.x = x;
                    p.y = polynomial.compute(x);
                    if (x == 0) {
                        max = p;
                    }
                    array.put(new JSONObject(p.toString()));
                    x += 1;
                }
            } catch (Exception e) {

            }
            Alarm alarm = new Alarm();
            long time = System.currentTimeMillis();
            Timestamp st = new Timestamp(time);
            alarm.setAlarmTime(st.toString());
            alarm.setDownPointData(array.toString());
            alarm.setMaxHeight(String.valueOf(max.y));
            alarm.setSourceGpsX("0");
            alarm.setSourceGpsY("0");
            alarm.setUpPointData(array.toString());
            alarm.setInfo("在（0.0）处产生滑坡险情");
            alarm.setLevel("3");
            return alarm;
        }
        
    }

    public double max(double[] data) {
        if (data == null) {
            return -1;
        } else {
            double max = data[0];
            for (int i = 0; i < data.length; i++) {
                if (data[i] > max) {
                    max = data[i];
                }
            }
            return max;
        }
    }



}
