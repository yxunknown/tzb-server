package computation;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mevur on 6/6/2017.
 */
public class Polynomial {
    private List<String> expression;
    private String x;

    /**
     * Polynomial default constructor
     *
     * @param unknown unknown is what for x
     */
    public Polynomial(String unknown) {
        this.x = unknown;
        expression = new ArrayList<>();
    }

    /**
     * add a node to polynomial
     *
     * @param node node is a x-contained expression
     */
    public void addNode(String node) {
        expression.add(node);
    }

    public double compute(double xi) {
        double result = 0;
        for (String exp : expression) {
            if (exp.contains(this.x)) {
                result += postfix(exp, xi);
            } else {
                result += Double.parseDouble(exp);
            }
        }
        return result;
    }

    private double postfix(String exp, double xi) {
        FelEngine fel = new FelEngineImpl();
        FelContext context = fel.getContext();
        context.set(this.x, xi);
        fel.setContext(context);
        Object o = fel.eval(exp);
        return Double.parseDouble(String.valueOf(o));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("P(" + x + ") = ");
        for (int i = 0; i < expression.size(); i++) {
            sb.append(expression.get(i) + " + ");
        }
        sb.deleteCharAt(sb.length() - 1); //delete " "
        sb.deleteCharAt(sb.length() - 1); //delete "+"
        sb.deleteCharAt(sb.length() - 1); //delete " "
        return sb.toString();
    }
}
