package GroupTwo.AlgorithmComparison;

import java.util.*;

/**
 * @author icode-wp
 * @Package GroupTwo.AlgorithmComparison
 * @date 2023/12/20 9:27
 */
public class CreatPoints {


    // 生成随机点
    public static List<Point> getRandomPoints(int pointNum,boolean flag){
        Random random=new Random();
        List<Point> points=new ArrayList<>();
        int num=0;
        while (num<pointNum){
            double x =random.nextDouble()*10000;
            double y =random.nextDouble()*10000;
            points.add(new Point(x,y));
            num++;
        }
        return points;
    }

    public static List<Point> getRandomPoints(int pointNum){
        Map<Integer, Set<Integer>> p = new HashMap<>();   //key为x坐标，value为x坐标相同的y值的集合
        Random random = new Random();
        List<Point> points=new ArrayList<>();
        int num = 0;
        while (num<pointNum){
            int x = random.nextInt(81) - 40; // 在[-40, 40]范围内生成x坐标
            int y = random.nextInt(45) - 22; // 在[-22, 22]范围内生成y坐标
            if (p.get(x) != null){
                if (p.get(x).contains(y)){
                    continue;
                }else {
                    p.get(x).add(y);
                    points.add(new Point(x,y));
                }
            }else {
                Set<Integer> set = new HashSet<>();
                set.add(y);
                p.put(x,set);
                points.add(new Point(x,y));
            }
            num++;
        }
        return points;
    }



    public static List<Point> showPoints(int pointNum){
        if (pointNum<=3520){
            return getRandomPoints(pointNum);
        }
        boolean flag=true;
        return getRandomPoints(pointNum,flag);
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入结点个数:");
        int pointNum=scanner.nextInt();
        List<Point> pointList=CreatPoints.showPoints(pointNum);


// ======================= 暴力算法 ======================
        long startTime=System.currentTimeMillis();
        // 暴力算法
       List resultList=BruteForce.bruteForce(pointNum,pointList);

        long endTime=System.currentTimeMillis();
        System.out.println("暴力算法时间:"+(endTime-startTime));


// =================== 分治法 ===========================
        long st=System.currentTimeMillis();
        // 分治法
        DivideAndConquer.putIn(pointNum,pointList);
        long et=System.currentTimeMillis();
        System.out.println("分治法时间:"+(et-st));


        if (pointNum<=3520){
            // 使用swing绘画
            List<Point>pointLists= (List<Point>) resultList.get(0);
            List<Integer> minLists= (List<Integer>) resultList.get(1);
            Mypanel panel=new Mypanel(pointLists,pointLists.get(minLists.get(0)),pointLists.get(minLists.get(1)));
            panel.draw();
        }

    }




}
