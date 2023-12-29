package GroupTwo.AlgorithmComparison;

import java.util.*;

/**
 * @author icode-wp
 * @Package GroupTwo.AlgorithmComparison
 * @date 2023/12/20 11:17
 */
public class DivideAndConquer {

    // ����Ƚ����࣬���ڰ���x�����y��������
    static class CompareX implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            // �����������ȣ����� 0
            if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                return 0;
            }
            // ���������� x ������ȣ����� y ����Ĵ�С���� -1 �� 1
            if (p1.getX() == p2.getX()) {
                return p1.getY() < p2.getY() ? -1 : 1;
            }
            // ���������� y ������ȣ����� x ����Ĵ�С���� -1 �� 1
            if (p1.getY() == p2.getY()) {
                return p1.getX() < p2.getX() ? -1 : 1;
            }
            // ���������Ȳ���ȣ�Ҳ����ͬһ��ˮƽ�߻�ֱ���ϣ����� x ����� y ����ĺ͵Ĵ�С���� -1 �� 1
            return (p1.getX() + p1.getY()) < (p2.getX() + p2.getY()) ? -1 : 1;
        }
    }

    static class CompareY implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            // �����������ȣ����� 0
            if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                return 0;
            }
            // ���������� y ������ȣ����� x ����Ĵ�С���� -1 �� 1
            if (p1.getY() == p2.getY()) {
                return p1.getX() < p2.getX() ? -1 : 1;
            }
            // ���������� y ���겻��ȣ����� y ����Ĵ�С���� -1 �� 1
            return p1.getY() < p2.getY() ? -1 : 1;
        }
    }
    public static double closestPair(Point[] points, int pointNum) {

        double d = Double.MAX_VALUE;
        if (pointNum <= 3) {
            // �����㷨
            for (int i = 0; i < points.length - 1; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    // ���ݾ��빫ʽ�������
                    double distance = Math.sqrt(((points[i].getX() - points[j].getX()) * (points[i].getX() - points[j].getX())) +
                            ((points[i].getY() - points[j].getY()) * (points[i].getY() - points[j].getY())));

                    if (distance < d) {
                        d = distance;
                    }
                }

            }
             return d;
        }

        // ���㼯����x���������ҳ��м���x����
        Arrays.sort(points, new CompareX());

        int mid=pointNum/2;
        double midX=points[mid].getX();

        // �ݹ�ض����������Ӽ����÷��η����õ�����������������Ժ���С����
        double d1 = closestPair(Arrays.copyOfRange(points, 0, mid), mid);
        double d2 = closestPair(Arrays.copyOfRange(points, mid, pointNum), pointNum - mid);
        double d3 = Math.min(d1, d2);

        // ���м������ڣ��ҳ�����x������[midX-d3,midX+d3]��Χ�ڵĵ㣬����y��������
        List<Point> strip = new ArrayList<>();
        for (int i = 0; i < pointNum; i++) {
            // Math.abs ���ؾ���ֵ  �жϸ�Ԫ���Ƿ���[midX-d3,midX+d3]��Χ��
            if (Math.abs(points[i].getX() - midX) < d3) {
                strip.add(points[i]);
            }
        }

        Collections.sort(strip,new CompareY());


        // ��ÿ���㣬ֻ��������y���������ڵ�6���㣨����4���㣩�ȽϾ��룬���С��d�������d��ֵ
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
        System.out.println("���η���̾���Ϊ:"+closestPair(points,pointNum));
    }


}
