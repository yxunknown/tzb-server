package socket;

import dao.SensorData;

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
        String[] bytes = frame.split(" ");
        //drop data at index 0
        if (bytes.length == 34) {
            SensorData data = new SensorData();
            String head1 = "Ox" + bytes[1];
            data.setHead1(head1);
            String head2 = "Ox" + bytes[2];
            data.setHead2(head2);
            int nodeNO = Integer.parseInt(bytes[3], 16);
            data.setNodeNO(nodeNO);
            int validData = Integer.parseInt(bytes[4], 16);
            data.setValidDataNumbers(validData);
            String intemp = "";
            intemp = bytes[5] + bytes[6];
            int gpsx = Integer.parseInt(intemp, 16);
            data.setGpsX(gpsx);
            intemp = bytes[7] + bytes[8];
            int gpsy = Integer.parseInt(intemp, 16);
            data.setGpsY(gpsy);
            int[] sensorData = new int[10];
            for (int i = 0; i < 10; i++) {
                intemp = bytes[9 + i * 2] + bytes[10 + i * 2];
                sensorData[i] = Integer.parseInt(intemp, 16);
            }
            data.setData(sensorData);
            int verify1 = Integer.parseInt(bytes[29], 16);
            data.setVervify1(verify1);
            intemp = bytes[30] + bytes[31];
            int verify2 = Integer.parseInt(intemp, 16);
            data.setVervify2(verify2);
            String end1 = "Ox" + bytes[32];
            data.setEnd1(end1);
            String end2 = "Ox" + bytes[33];
            data.setEnd2(end2);
            return data;
        } else {
            return null;
        }
    }
}
