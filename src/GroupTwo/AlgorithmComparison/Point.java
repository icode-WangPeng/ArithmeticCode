package GroupTwo.AlgorithmComparison;

import java.text.DecimalFormat;

/**
 * @author icode-wp
 * @Package GroupTwo.AlgorithmComparison
 * @date 2023/12/20 9:23
 */
public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        // 设置保留四位小数的格式
        DecimalFormat decimalFormat = new DecimalFormat("#0.####");
        double xNum = Double.parseDouble(decimalFormat.format(x));
        double yNum = Double.parseDouble(decimalFormat.format(y));
        this.x = xNum;
        this.y = yNum;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
