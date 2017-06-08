package computation;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mevur on 6/6/2017.
 */
public class Interpolation {
    private List<Double> differenceQuotient;
    private List<Point> points;
    private int count;
    private double[]x;
    public Interpolation(@NotNull List<Point> points) {
        this.points = points;
        this.count = points.size();
        x = new double[count];
        for (int i = 0; i < count; i++) {
            x[i] = points.get(i).x;
        }
    }
    private void processDifferenceQuotient() {
        differenceQuotient = new ArrayList<>(count);
        //差商记录表
        double[][] temp = new double[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (i == 0) {
                    temp[i][j] = points.get(j).y;
                } else {
                    if (j < i) {
                        temp[i][j] = 0;
                    } else {
                        double x2 = temp[i - 1][j];
                        double x1 = temp[i - 1][j - 1];
                        double dx = x[j] - x[j - i];
                        temp[i][j] = (x2 - x1) / dx;
                    }
                }
            }
        }
        for (int i = 0; i < count; i++) {
            differenceQuotient.add(temp[i][i]);
        }
    }
    public Polynomial newton(){
        processDifferenceQuotient();
        Polynomial polynomial = new Polynomial("x");
        String node = "";
        for (int i = 0; i < count; i++) {
            if (i == 0) {
                node = String.valueOf(differenceQuotient.get(0));
                polynomial.addNode(node);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i; j++) {
                    sb.append("(x-" + x[j] + ")*");
                }
                sb.append(differenceQuotient.get(i));
                node = sb.toString();
                polynomial.addNode(node);
            }
        }
        return polynomial;
    }
}
