package GroupThree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class roundBrackets {
    //深度优先遍历+递归
   static   List<String> result=new ArrayList<>();
    //减枝
    public static List<String> generateParenthesis(int n) {
        if(n<=0){
            return result;
        }
        getRound("",n,n);
        return result;
    }
    /**
     * 深度优先遍历，搜索可能的结果
     * @param str 递归得到的结果
     * @param i 左括号剩余数目
     * @param j 右括号剩余数目
     */
    private static void getRound(String str,int i,int j){
        if(i==0&&j==0){
            result.add(str);
            return;
        }
        if(i>j){
            return;
        }
        if(i>0){
            getRound(str+"(",i-1,j);
        }
        if (j>0){
            getRound(str+")",i,j-1);
        }
    }


    public static void main(String[] args) {
        System.out.println("请输入需要生成的括号数量：");
        System.out.println("括号数量为1-8");
        Scanner sc = new Scanner(System.in);
        int size=sc.nextInt();
        List<String> generatedParentheses = generateParenthesis(size); // 测试生成括号的数量
        System.out.println("所有可能的并且有效的括号组合：" + generatedParentheses);
    }
}
