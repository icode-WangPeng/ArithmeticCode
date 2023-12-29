package GroupTwo.taskAllocation;

/**
 * &#064;author: YAR <BR/>
 * &#064;time: 2023/12/19 14:59 <BR/>
 * &#064;className: taskAllocation_xiongyalidiedai <BR/>
 * &#064;description
 ***/

public class taskAllocation_xiongyalidiedai {
    public static final int MAX=9999;
    public static Matrix hungary = new Matrix();
    public static int[][] result;

    public static void main(String[] args)
    {
        result = new int [5041][2];
        result[0][0]=0;
        hungary=Matrix.input();
        reduction(hungary);
        circleZero(hungary);
        Matrix.output(result,hungary);
    }

    //行列归约---得到行列中的零元素进行任务指派
    public static void reduction(Matrix hungary){
        int i,j;
        int temp;

        //找到同行的最小元素
        for(i=1;i<=hungary.matrixsize;i++) {
            temp=hungary.cost[i][1];
            for(j=2;j<=hungary.matrixsize;j++)
                if(hungary.cost[i][j]<temp) {
                    temp=hungary.cost[i][j];
                }
        //减去同行最小元素
            for(j=1;j<=hungary.matrixsize;j++) {
                hungary.cost[i][j]=hungary.cost[i][j]-temp;
            }
        }

        //找到同列最小元素
        for(j=1;j<=hungary.matrixsize;j++) {
            temp=hungary.cost[1][j];
            for(i=2;i<=hungary.matrixsize;i++)
                if(hungary.cost[i][j]<temp) {
                    temp=hungary.cost[i][j];
                }
        //减去同列最小元素
            for(i=1;i<=hungary.matrixsize;i++) {
                hungary.cost[i][j]=hungary.cost[i][j]-temp;
            }
        }
    }

    public static void circleZero(Matrix hungary) {

        //cost成本矩阵新加行列
        //统计行列0元素的个数，记录成：0元素的总个数、行中0元素的个数、列中0元素的个数
        int i,j,p;
        int flag;
        for(i=0;i<=hungary.matrixsize;i++) {
            hungary.cost[i][0]=0;
        }
        for(j=1;j<=hungary.matrixsize;j++) {
            hungary.cost[0][j]=0;
        }
        for(i=1;i<=hungary.matrixsize;i++) {
            for(j=1;j<=hungary.matrixsize;j++) {
                if(hungary.cost[i][j]==0) {
                    hungary.cost[i][0]++;
                    hungary.cost[0][j]++;
                    hungary.cost[0][0]++;
                }
            }
        }

        //zero零元素矩阵初始化
        for(i=0;i<=hungary.matrixsize;i++) {
            for(j=0;j<=hungary.matrixsize;j++) {
                hungary.zeroelem[i][j]=0;
            }
        }

        //两遍遍历修改zero矩阵和cost矩阵
        flag=hungary.cost[0][0]+1;           //flag=归约后零元素总个数+1 保证进入循环
        while(hungary.cost[0][0]<flag){      //未被标记为(1)或(2)的0元素个数在两遍搜索后不变则结束循环
            flag=hungary.cost[0][0];
            //遍历行
            for(i=1;i<=hungary.matrixsize;i++){
                if(hungary.cost[i][0]==1){    //行中仅存在1个未被任何标记的0元素
                    for(j=1;j<=hungary.matrixsize;j++) {
                        if(hungary.cost[i][j]==0 && hungary.zeroelem[i][j]==0) {
                            break;
                        }
                    }
                    //零元素矩阵中的对应坐标，该位置赋值为1
                    hungary.zeroelem[i][j]=1;
                    //成本矩阵三个0元素计数均-1
                    hungary.cost[i][0]--;
                    hungary.cost[0][j]--;
                    hungary.cost[0][0]--;
                    if(hungary.cost[0][j]>0)  //对标(1)的0元素所在列的其它未被标记的0元素标(2)
                    {
                        for(p=1;p<=hungary.matrixsize;p++) {
                            if(hungary.cost[p][j]==0&&hungary.zeroelem[p][j]==0)
                            {
                                hungary.zeroelem[p][j]=2;
                                hungary.cost[p][0]--;
                                hungary.cost[0][j]--;
                                hungary.cost[0][0]--;
                            }
                        }
                    }
                }
            }
            for(j=1;j<=hungary.matrixsize;j++)      //第二遍先列后行
            {
                if(hungary.cost[0][j]==1)   //列中仅存在1个未被任何标记的0元素
                {
                    for(i=1;i<=hungary.matrixsize;i++) {
                        if(hungary.cost[i][j]==0&&hungary.zeroelem[i][j]==0)
                            break;
                    }
                    hungary.zeroelem[i][j]=1;
                    hungary.cost[i][0]--;
                    hungary.cost[0][j]--;
                    hungary.cost[0][0]--;
                    if(hungary.cost[i][0]>0)  //对标(1)的0元素所在行的其它未被标记的0元素标(2)
                        for(p=1;p<=hungary.matrixsize;p++)
                            if(hungary.cost[i][p]==0&&hungary.zeroelem[i][p]==0)
                            {
                                hungary.zeroelem[i][p]=2;
                                hungary.cost[i][0]--;
                                hungary.cost[0][p]--;
                                hungary.cost[0][0]--;
                            }
                }
            }
        }
        if(hungary.cost[0][0]>0) //存在未被标记为(1)或(2)的0元素
            twoZero(hungary);
        else
            judge(hungary,result);
    }

    public static void twoZero(Matrix hungary) {
        int i,j;
        int p,q;
        int m,n;
        int flag;
        Matrix backup = new Matrix();
        for(i=1;i<=hungary.matrixsize;i++)
            if(hungary.cost[i][0]>0)
                break;
        if(i<=hungary.matrixsize) {
            for(j=1;j<=hungary.matrixsize;j++) {
                backup= hungary.clone();//备份以寻找多解
                if(hungary.cost[i][j]==0&&hungary.zeroelem[i][j]==0) {
                    hungary.zeroelem[i][j]=1;
                    hungary.cost[i][0]--;
                    hungary.cost[0][j]--;
                    hungary.cost[0][0]--;
                    for(q=1;q<=hungary.matrixsize;q++)
                        if(hungary.cost[i][q]==0&&hungary.zeroelem[i][q]==0) {
                            hungary.zeroelem[i][q]=2;
                            hungary.cost[i][0]--;
                            hungary.cost[0][q]--;
                            hungary.cost[0][0]--;
                        }
                    for(p=1;p<=hungary.matrixsize;p++)
                        if(hungary.cost[p][j]==0&&hungary.zeroelem[p][j]==0)
                        {
                            hungary.zeroelem[p][j]=2;
                            hungary.cost[p][0]--;
                            hungary.cost[0][j]--;
                            hungary.cost[0][0]--;
                        }
                    flag=hungary.cost[0][0]+1;
                    while(hungary.cost[0][0]<flag)
                    {
                        flag=hungary.cost[0][0];
                        for(p=i+1;p<=hungary.matrixsize;p++)
                        {
                            if(hungary.cost[p][0]==1)
                            {
                                for(q=1;q<=hungary.matrixsize;q++)
                                    if(hungary.cost[p][q]==0&&hungary.zeroelem[p][q]==0)
                                        break;
                                hungary.zeroelem[p][q]=1;
                                hungary.cost[p][0]--;
                                hungary.cost[0][q]--;
                                hungary.cost[0][0]--;
                                for(m=1;m<=hungary.matrixsize;m++)
                                    if(hungary.cost[m][q]==0&&hungary.zeroelem[m][q]==0)
                                    {
                                        hungary.zeroelem[m][q]=2;
                                        hungary.cost[m][0]--;
                                        hungary.cost[0][q]--;
                                        hungary.cost[0][0]--;
                                    }
                            }
                        }
                        for(q=1;q<=hungary.matrixsize;q++)
                        {
                            if(hungary.cost[0][q]==1)
                            {
                                for(p=1;p<=hungary.matrixsize;p++)
                                    if(hungary.cost[p][q]==0&&hungary.zeroelem[p][q]==0)
                                        break;
                                hungary.zeroelem[p][q]=1;
                                hungary.cost[p][q]--;
                                hungary.cost[0][q]--;
                                hungary.cost[0][0]--;
                                for(n=1;n<=hungary.matrixsize;n++)
                                    if(hungary.cost[p][n]==0&&hungary.zeroelem[p][n]==0)
                                    {
                                        hungary.zeroelem[p][n]=2;
                                        hungary.cost[p][0]--;
                                        hungary.cost[0][n]--;
                                        hungary.cost[0][0]--;
                                    }
                            }
                        }
                    }
                    if(hungary.cost[0][0]>0)                   //确保hungary.cost[][]中的0元素都在zeroelem[][]中被完全标记出来。
                        twoZero(hungary);
                    else
                        judge(hungary,result);
                }
                hungary=backup;
            }
        }
    }

    //判断是否符合匈牙利算法条件
    static void judge(Matrix hungary, int[][] result){
        int i,j;
        int num=0; //线的条数
        int start; //每组解的储存开始位置
        for(i=1;i<=hungary.matrixsize;i++)
            for(j=1;j<=hungary.matrixsize;j++)
                if(hungary.zeroelem[i][j]==1)
                    num++;      //划线的条数/标记为(1)的0元素个数
        if(num==hungary.matrixsize) {  //与矩阵阶数相同则符合
            start=result[0][0]*hungary.matrixsize+1;
            for(i=1;i<=hungary.matrixsize;i++)
                for(j=1;j<=hungary.matrixsize;j++)
                    if(hungary.zeroelem[i][j]==1) {
                        result[start][0]=i;
                        result[start++][1]=j;
                    }
            result[0][0]++;
        }
        else //否则需要进行变形
            refresh(hungary);
    }

    //不符合条件，对矩阵进行变形
    static void refresh(Matrix hungary) {
        int i,j,min=0;
        int flag1=0,flag2=0;
        for(i=1;i<=hungary.matrixsize;i++) {
            for(j=1;j<=hungary.matrixsize;j++)
                if(hungary.zeroelem[i][j]==1) {
                    hungary.zeroelem[i][0]=1;         //有独立零元素
                    break;
                }
        }
        while(flag1==0)
        {
            flag1=1;
            for(i=1;i<=hungary.matrixsize;i++)
                if(hungary.zeroelem[i][0]==0)
                {
                    hungary.zeroelem[i][0]=2;
                    for(j=1;j<=hungary.matrixsize;j++)
                        if(hungary.zeroelem[i][j]==2)
                        {
                            hungary.zeroelem[0][j]=1;
                        }
                }
            for(j=1;j<=hungary.matrixsize;j++)
            {
                if(hungary.zeroelem[0][j]==1)
                {
                    hungary.zeroelem[0][j]=2;
                    for(i=1;i<=hungary.matrixsize;i++)
                        if(hungary.zeroelem[i][j]==1)
                        {
                            hungary.zeroelem[i][0]=0;
                            flag1=0;
                        }
                }
            }
        }                    //对打勾的行和列标记成2
        for(i=1;i<=hungary.matrixsize;i++)
        {
            if(hungary.zeroelem[i][0]==2)
            {
                for(j=1;j<=hungary.matrixsize;j++)
                {
                    if(hungary.zeroelem[0][j]!=2)
                        if(flag2==0)
                        {
                            min=hungary.cost[i][j];
                            flag2=1;
                        }
                        else
                        {
                            if(hungary.cost[i][j]<min)
                                min=hungary.cost[i][j];
                        }
                }
            }
        }     //寻找未被覆盖的最小值
        for(i=1;i<=hungary.matrixsize;i++)
        {
            if(hungary.zeroelem[i][0]==2)
                for(j=1;j<=hungary.matrixsize;j++)
                    hungary.cost[i][j]=hungary.cost[i][j]-min;
        }
        for(j=1;j<=hungary.matrixsize;j++)
        {
            if(hungary.zeroelem[0][j]==2)
                for(i=1;i<=hungary.matrixsize;i++)
                    hungary.cost[i][j]=hungary.cost[i][j]+min;
        }                   //未被划线的行减去未被覆盖的最小值，被划线的列加上未被覆盖的最小值
        for(i=0;i<=hungary.matrixsize;i++)
            for(j=0;j<=hungary.matrixsize;j++)
                hungary.zeroelem[i][j]=0;              //矩阵清0
        circleZero(hungary);
    }

}

