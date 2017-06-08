package computation;

import org.json.JSONObject;

/**
 * Created by mevur on 6/6/2017.
 */
public class Point {
    public double x;
    public double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point() {

    }

    @Override
    public String toString() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("x", x);
            obj.put("y", y);
            return obj.toString();
        } catch (Exception e) {
            return super.toString();
        }
    }
}
