package GroupTwo.AlgorithmComparison;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author icode-wp
 * @Package GroupTwo.AlgorithmComparison
 * @date 2023/12/20 9:46
 */
// 蛮力算法
public class BruteForce {
    public static List bruteForce(int pointNum,List<Point> pointList){
        TreeMap<Double,List<Integer>> map=new TreeMap<>();
        double d=Double.MAX_VALUE;

        for (int i=0;i<pointList.size()-1;i++){
            // 选择二叉树，因为自己会进行升序排序
            TreeMap<Double,Integer> treeMap=new TreeMap<>();
            for (int j=i+1;j<pointList.size();j++){
                // sqrt是开平方
               double distance=Math.sqrt(((pointList.get(i).getX()-pointList.get(j).getX())*(pointList.get(i).getX()-pointList.get(j).getX()))+
                        ((pointList.get(i).getY()-pointList.get(j).getY())*(pointList.get(i).getY()-pointList.get(j).getY())));

                if (distance<d){
                    d=distance;
                    treeMap.put(d,j);
                }
            }
            double key=treeMap.firstKey();
            int value=treeMap.get(key);
            List<Integer> list=new ArrayList<>();
            list.add(i);
            list.add(value);
            map.put(key,list);
            d=Double.MAX_VALUE;
        }

        double dist=map.firstKey();
        List<Integer> minList=map.get(dist);
        System.out.println("暴力算法最小距离为:");
        pointPar(pointList.get(minList.get(0)),pointList.get(minList.get(1)),dist);

        // 存入结果
        List resultList=new ArrayList<>();
        resultList.add(0,pointList);
        resultList.add(1,minList);
        return resultList;

    }


    public static void pointPar(Point a, Point b,double distance){
        System.out.println("P("+a.getX()+","+a.getY()+") and P("+b.getX()+","+b.getY()+")"+" "+"距离为:"+distance);
    }

}
