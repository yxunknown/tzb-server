package test;

import computation.Point;
import dao.AlarmAccessLayer;
import dao.UserAccesslayer;
import model.Alarm;
import model.SensorData;
import dao.SensorDataAcessLayer;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * Created by mevur on 5/30/2017.
 */
public class Database {
    public static void main(String[] args) {
        new Database().queryTest();
    }
    public void insertTest() {
        User user = new User();
        user.setId("18996486935");
        user.setUsername("程飘");
        user.setPassword("love..");
        UserAccesslayer accesslayer = new UserAccesslayer();
        accesslayer.insert(user);
    }

    public void queryTest() {
        UserAccesslayer a = new UserAccesslayer();
        List<User> users = a.query("SELECT * FROM user");
        System.out.println(users);
    }
}
