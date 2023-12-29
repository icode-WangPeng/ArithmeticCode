package GroupTwo.taskAllocation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix {
    public int[][] cost;       //cost成本矩阵
    public int[][] zeroelem;   //zero零元素矩阵
    public int[][] costforout;
    public int matrixsize;

    public Matrix() {
        costforout = new int [101][101];
        zeroelem = new int [101][101];
        cost = new int [101][101];
    }

    public Matrix(Matrix m) {
        costforout = new int [101][101];
        zeroelem = new int [101][101];
        cost = new int [101][101];

        for(int q = 0; q < 101; q++)
        {
            for(int w = 0; w < 101; w++)
            {
                this.costforout[q][w] = m.costforout[q][w];
                this.zeroelem[q][w] = m.zeroelem[q][w];
                this.cost[q][w] = m.cost[q][w];
            }
        }
        this.matrixsize = m.matrixsize;
    }

    public Matrix clone()
    {
        return new Matrix(this);
    }

    //构造任务分配矩阵
    public static Matrix input() {
        Scanner in = new Scanner(System.in);
        int i,j;
        Matrix hungary = new Matrix();
        System.out.println("请输入任务数:");
        try
        {
            hungary.matrixsize = in.nextInt();
            System.out.print("请输入" + hungary.matrixsize + "阶矩阵(用空格和回车):\n");
            for(i=1;i<=hungary.matrixsize;i++)
            {
                for(j=1;j<=hungary.matrixsize;j++)
                {
                    hungary.cost[i][j] = in.nextInt();
                    hungary.costforout[i][j]=hungary.cost[i][j];
                }
            }
            in.close();
        }
        catch(InputMismatchException e)
        {
            System.out.println(e);
            System.exit(-1);
        }
        return hungary;
    }

    public static int[][] inputArray(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入矩阵的阶数:");
        int n = scanner.nextInt();
        int[][] c = new int[n][n];
        System.out.println("请输入该矩阵");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = scanner.nextInt();
            }
        }
        return c;
    }

    //输出结果
    public static void output(int result[][],Matrix hungary) {
        int num; //解的数量
        int minsum; //最小的工作成本
        int i,j;
        int start;  //每个解的储存开始位置
        minsum=0;
        for(i=1;i<=hungary.matrixsize;i++) {
            minsum=minsum+hungary.costforout[i][result[i][1]];
        }
        System.out.println("**匈牙利算法最优安排展示如下**");
        num=result[0][0];
        System.out.println("总共有" + num + "种解");
        for(i=1;i<=num;i++)
        {
            System.out.println("第" + i + "种解：");
            start=(i-1)*hungary.matrixsize+1;
            for(j=start;j<start+hungary.matrixsize;j++)
                System.out.println("第" + result[j][0] + "个人做第" + result[j][1] + "件工作。");
            System.out.println("最小代价为：" + minsum);
        }
    }
}
