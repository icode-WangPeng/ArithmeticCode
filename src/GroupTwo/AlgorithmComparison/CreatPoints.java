package GroupTwo.AlgorithmComparison;

import java.util.*;

/**
 * @author icode-wp
 * @Package GroupTwo.AlgorithmComparison
 * @date 2023/12/20 9:27
 */
public class CreatPoints {


    // ���������
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
        Map<Integer, Set<Integer>> p = new HashMap<>();   //keyΪx���꣬valueΪx������ͬ��yֵ�ļ���
        Random random = new Random();
        List<Point> points=new ArrayList<>();
        int num = 0;
        while (num<pointNum){
            int x = random.nextInt(81) - 40; // ��[-40, 40]��Χ������x����
            int y = random.nextInt(45) - 22; // ��[-22, 22]��Χ������y����
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
        System.out.println("�����������:");
        int pointNum=scanner.nextInt();
        List<Point> pointList=CreatPoints.showPoints(pointNum);


// ======================= �����㷨 ======================
        long startTime=System.currentTimeMillis();
        // �����㷨
       List resultList=BruteForce.bruteForce(pointNum,pointList);

        long endTime=System.currentTimeMillis();
        System.out.println("�����㷨ʱ��:"+(endTime-startTime));


// =================== ���η� ===========================
        long st=System.currentTimeMillis();
        // ���η�
        DivideAndConquer.putIn(pointNum,pointList);
        long et=System.currentTimeMillis();
        System.out.println("���η�ʱ��:"+(et-st));


        if (pointNum<=3520){
            // ʹ��swing�滭
            List<Point>pointLists= (List<Point>) resultList.get(0);
            List<Integer> minLists= (List<Integer>) resultList.get(1);
            Mypanel panel=new Mypanel(pointLists,pointLists.get(minLists.get(0)),pointLists.get(minLists.get(1)));
            panel.draw();
        }

    }




}
