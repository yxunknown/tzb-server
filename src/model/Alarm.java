package model;

import org.json.JSONObject;

/**
 * Created by mevur on 6/4/2017.
 */
public class Alarm {
    private String alarmTime;
    private String maxHeight;
    private String upPointData;
    private String downPointData;
    private String sourceGpsX;
    private String sourceGpsY;
    private String level;
    private String info;

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(String maxHeight) {
        this.maxHeight = maxHeight;
    }

    public String getUpPointData() {
        return upPointData;
    }

    public void setUpPointData(String upPointData) {
        this.upPointData = upPointData;
    }

    public String getDownPointData() {
        return downPointData;
    }

    public void setDownPointData(String downPointData) {
        this.downPointData = downPointData;
    }

    public String getSourceGpsX() {
        return sourceGpsX;
    }

    public void setSourceGpsX(String sourceGpsX) {
        this.sourceGpsX = sourceGpsX;
    }

    public String getSourceGpsY() {
        return sourceGpsY;
    }

    public void setSourceGpsY(String sourceGpsY) {
        this.sourceGpsY = sourceGpsY;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("alarm_time", alarmTime);
            obj.put("max_height", maxHeight);
            obj.put("up_point_data", upPointData);
            obj.put("down_point_data", downPointData);
            obj.put("source_gps_x", sourceGpsX);
            obj.put("source_gps_y", sourceGpsY);
            obj.put("level", level);
            obj.put("info", info);
            return obj.toString();
        } catch (Exception e) {
            return super.toString();
        }

    }
}
