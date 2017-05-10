package dao;

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
     * frame head2
     */
    private String head2;
    /**
     * number of sensor
     */
    private int nodeNO;
    /**
     * number of data, max is 10
     */
    private int validDataNumbers;
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
    private int[] data = new int[10];
    /**
     * check sum1
     */
    private int vervify1;
    /**
     * check sum2
     */
    private int vervify2;
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

    public String getHead2() {
        return head2;
    }

    public void setHead2(String head2) {
        this.head2 = head2;
    }

    public int getNodeNO() {
        return nodeNO;
    }

    public void setNodeNO(int nodeNO) {
        this.nodeNO = nodeNO;
    }

    public int getValidDataNumbers() {
        return validDataNumbers;
    }

    public void setValidDataNumbers(int validDataNumbers) {
        this.validDataNumbers = validDataNumbers;
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

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public int getVervify1() {
        return vervify1;
    }

    public void setVervify1(int vervify1) {
        this.vervify1 = vervify1;
    }

    public int getVervify2() {
        return vervify2;
    }

    public void setVervify2(int vervify2) {
        this.vervify2 = vervify2;
    }

    public String getEnd1() {
        return end1;
    }

    public void setEnd1(String end1) {
        this.end1 = end1;
    }

    public String getEnd2() {
        return end2;
    }

    public void setEnd2(String end2) {
        this.end2 = end2;
    }

    /**
     * frame end 2
     */
    private String end2;

    @Override
    public String toString() {
        return "SensorData{" +
                "head1=" + head1 +
                ", head2=" + head2 +
                ", nodeNO=" + nodeNO +
                ", validDataNumbers=" + validDataNumbers +
                ", gpsX=" + gpsX +
                ", gpsY=" + gpsY +
                ", data=" + Arrays.toString(data) +
                ", vervify1=" + vervify1 +
                ", vervify2=" + vervify2 +
                ", end1=" + end1 +
                ", end2=" + end2 +
                '}';
    }
}
