package util;

/**
 * Created by mevur on 6/6/2017.
 */
public class V2H {
    private double[] table1;
    private double[] table2;
    private double[] table3;

    /**
     * voltage to height
     */
    public V2H() {
        table1 = new double[22];
        table1[0] = 9.59;
        table1[1] = 9.76;
        table1[2] = 9.77;
        table1[3] = 9.91;
        table1[4] = 9.92;
        table1[5] = 10.07;
        table1[6] = 10.08;
        table1[7] = 10.22;
        table1[8] = 10.23;
        table1[9] = 10.37;
        table1[10] = 10.38;
        table1[11] = 10.55;
        table1[12] = 10.56;
        table1[13] = 10.67;
        table1[14] = 10.68;
        table1[15] = 10.87;
        table1[16] = 10.88;
        table1[17] = 10.97;
        table1[18] = 10.98;
        table1[19] = 11.02;
        table1[20] = 11.03;
        table1[21] = 11.30;
        table2 = new double[22];
        table2[0] = 9.50;
        table2[1] = 9.65;
        table2[2] = 9.66;
        table2[3] = 9.83;
        table2[4] = 9.84;
        table2[5] = 9.95;
        table2[6] = 9.96;
        table2[7] = 10.11;
        table2[8] = 10.12;
        table2[9] = 10.28;
        table2[10] = 10.29;
        table2[11] = 10.40;
        table2[12] = 10.41;
        table2[13] = 10.58;
        table2[14] = 10.59;
        table2[15] = 10.70;
        table2[16] = 10.71;
        table2[17] = 10.88;
        table2[18] = 10.89;
        table2[19] = 10.96;
        table2[20] = 10.97;
        table2[21] = 11.10;
        table3 = new double[22];
        table3[0] = 9.28;
        table3[1] = 9.42;
        table3[2] = 9.43;
        table3[3] = 9.59;
        table3[4] = 9.60;
        table3[5] = 9.73;
        table3[6] = 9.74;
        table3[7] = 9.89;
        table3[8] = 9.90;
        table3[9] = 10.05;
        table3[10] = 10.06;
        table3[11] = 10.18;
        table3[12] = 10.19;
        table3[13] = 10.38;
        table3[14] = 10.39;
        table3[15] = 10.50;
        table3[16] = 10.51;
        table3[17] = 10.71;
        table3[18] = 10.72;
        table3[19] = 10.87;
        table3[20] = 10.88;
        table3[21] = 10.99;

    }

    public double getHeight(int which, double voltage) {
        double[] table;
        if (which == 1) {
            //no1 node table
            table = table1;
        } else if (which == 2) {
            table = table2;
        } else {
            table = table3;
        }
        double out = 0;
        if (voltage < table[0]) {
            out = 25;
        } else if (voltage > table[17]) {
            out = 20;
        } else {
            for (int i = 0; i < table.length; i++) {
                if (voltage >= table[i] && voltage <= table[i + 1]) {
                    i = i / 2;
                    out = 25 - Double.parseDouble(String.valueOf(i)) / 2.0;
                    break;
                }
            }
        }
        return out;
    }
}
