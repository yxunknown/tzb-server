package socket;

import model.SensorData;
import util.V2H;

import java.text.SimpleDateFormat;

/**
 * Created by mevur on 5/9/2017.
 */
public class FrameParse {
    /**
     * frame is a hex format string, and consist of 33 bytes data
     * @param frame
     * @return
     */
    public static SensorData parseToSensorData(String frame) {
        String[] datas = frame.split(" ");
        SensorData data = new SensorData();
        //drop data at index 0
        String temp = "";
        if (datas.length != 15) {
            return null;
        } else {
            temp = datas[0];
            String head = temp.substring(0, temp.length() - 1);
            data.setHead1(head);
            String id = temp.substring(temp.length() - 1, temp.length());
            data.setNodeNO(Integer.parseInt(id));
            temp = datas[1];
            data.setGpsX(Integer.parseInt(temp));
            temp = datas[2];
            data.setGpsY(Integer.parseInt(temp));
            double[] v = new double[10];
            V2H v2H = new V2H();
            for (int i = 0; i < 10; i++) {
                temp = datas[i + 3];
                double dt = Double.parseDouble(temp);
                //check table get real height
                v[i] = v2H.getHeight(Integer.parseInt(id), dt);
            }
            data.setData(v);
            temp = datas[13];
            data.setVervify1(Integer.parseInt(temp));
            temp = datas[14];
            data.setEnd1(temp);
            return data;
        }
    }
}
