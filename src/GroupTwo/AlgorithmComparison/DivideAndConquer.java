package GroupTwo.AlgorithmComparison;

import java.util.*;

/**
 * @author icode-wp
 * @Package GroupTwo.AlgorithmComparison
 * @date 2023/12/20 11:17
 */
public class DivideAndConquer {

    // 定义比较器类，用于按照x坐标或y坐标排序
    static class CompareX implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            // 如果两个点相等，返回 0
            if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                return 0;
            }
            // 如果两个点的 x 坐标相等，根据 y 坐标的大小返回 -1 或 1
            if (p1.getX() == p2.getX()) {
                return p1.getY() < p2.getY() ? -1 : 1;
            }
            // 如果两个点的 y 坐标相等，根据 x 坐标的大小返回 -1 或 1
            if (p1.getY() == p2.getY()) {
                return p1.getX() < p2.getX() ? -1 : 1;
            }
            // 如果两个点既不相等，也不在同一条水平线或垂直线上，根据 x 坐标和 y 坐标的和的大小返回 -1 或 1
            return (p1.getX() + p1.getY()) < (p2.getX() + p2.getY()) ? -1 : 1;
        }
    }

    static class CompareY implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            // 如果两个点相等，返回 0
            if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                return 0;
            }
            // 如果两个点的 y 坐标相等，根据 x 坐标的大小返回 -1 或 1
            if (p1.getY() == p2.getY()) {
                return p1.getX() < p2.getX() ? -1 : 1;
            }
            // 如果两个点的 y 坐标不相等，根据 y 坐标的大小返回 -1 或 1
            return p1.getY() < p2.getY() ? -1 : 1;
        }
    }
    public static double closestPair(Point[] points, int pointNum) {

        double d = Double.MAX_VALUE;
        if (pointNum <= 3) {
            // 暴力算法
            for (int i = 0; i < points.length - 1; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    // 根据距离公式计算距离
                    double distance = Math.sqrt(((points[i].getX() - points[j].getX()) * (points[i].getX() - points[j].getX())) +
                            ((points[i].getY() - points[j].getY()) * (points[i].getY() - points[j].getY())));

                    if (distance < d) {
                        d = distance;
                    }
                }

            }
             return d;
        }

        // 将点集按照x坐标排序，找出中间点的x坐标
        Arrays.sort(points, new CompareX());

        int mid=pointNum/2;
        double midX=points[mid].getX();

        // 递归地对左右两个子集调用分治法，得到两个子问题的最近点对和最小距离
        double d1 = closestPair(Arrays.copyOfRange(points, 0, mid), mid);
        double d2 = closestPair(Arrays.copyOfRange(points, mid, pointNum), pointNum - mid);
        double d3 = Math.min(d1, d2);

        // 在中间区域内，找出所有x坐标在[midX-d3,midX+d3]范围内的点，按照y坐标排序
        List<Point> strip = new ArrayList<>();
        for (int i = 0; i < pointNum; i++) {
            // Math.abs 返回绝对值  判断该元素是否在[midX-d3,midX+d3]范围内
            if (Math.abs(points[i].getX() - midX) < d3) {
                strip.add(points[i]);
            }
        }

        Collections.sort(strip,new CompareY());


        // 对每个点，只需与它在y坐标上相邻的6个点（或者4个点）比较距离，如果小于d，则更新d的值
        for (int i = 0; i < strip.size(); i++) {
            double key;
            int value;

            for (int j = i + 1; j < strip.size() && (strip.get(j).getY() - strip.get(i).getY()) < d3; j++) {
                double d4 = Math.sqrt((strip.get(i).getX() - strip.get(j).getX()) * (strip.get(i).getX() - strip.get(j).getX()) +
                        (strip.get(i).getY() - strip.get(j).getY()) * (strip.get(i).getY() - strip.get(j).getY()));
                if (d4 < d3) {
                    d3 = d4;

                }
            }
        }
        return d3;
    }

    public static void putIn(int pointNum,List<Point> list) {
        Point[] points=new Point[list.size()];
       for (int i = 0; i < points.length; i++) {
           points[i]=list.get(i);
       }
        System.out.println("分治法最短距离为:"+closestPair(points,pointNum));
    }


}
