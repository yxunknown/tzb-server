package model;

import java.util.Arrays;

/**
 * Created by mevur on 5/8/2017.
 */
public class SensorData {
    /**
     * frame head1
     */
    private String head1;
    /**
     * number of sensor
     */
    private int nodeNO;
    /**
     * gps x data
     */
    private int gpsX;
    /**
     * gps y data
     */
    private int gpsY;
    /**
     * sensor data array, max len is 10
     */
    private double[] data = new double[10];
    /**
     * check sum1
     */
    private int vervify1;
    /**
     * frame end 1
     */
    private String end1;

    public String getHead1() {
        return head1;
    }

    public void setHead1(String head1) {
        this.head1 = head1;
    }

    public int getNodeNO() {
        return nodeNO;
    }

    public void setNodeNO(int nodeNO) {
        this.nodeNO = nodeNO;
    }

    public int getGpsX() {
        return gpsX;
    }

    public void setGpsX(int gpsX) {
        this.gpsX = gpsX;
    }

    public int getGpsY() {
        return gpsY;
    }

    public void setGpsY(int gpsY) {
        this.gpsY = gpsY;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public int getVervify1() {
        return vervify1;
    }

    public void setVervify1(int vervify1) {
        this.vervify1 = vervify1;
    }

    public String getEnd1() {
        return end1;
    }

    public void setEnd1(String end1) {
        this.end1 = end1;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "head1=" + head1 +
                ", nodeNO=" + nodeNO +
                ", gpsX=" + gpsX +
                ", gpsY=" + gpsY +
                ", data=" + Arrays.toString(data) +
                ", vervify1=" + vervify1 +
                ", end1=" + end1 +
                '}';
    }
}
