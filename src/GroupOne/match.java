package GroupOne;

import java.util.Scanner;

public class match {
    //贪心算法
    //回溯+双指针
    public boolean isMatch(String s, String p) {
        //sPos,pPos分别标记第一个字符的下标   sStar，pStar分别标记'*'匹配过的位置
        int sPos=0,pPos=0,sStar=-1,pStar=-1,m=s.length(),n=p.length();
        //判断字符串中的字符
        while(sPos<m){
            //s和p标记字符相等 或 p字符是'?'
            if(pPos<n&&(s.charAt(sPos)==p.charAt(pPos)||p.charAt(pPos)=='?')){
                sPos++;
                pPos++;
            //p字符是'*'
            } else if (pPos<n&&p.charAt(pPos)=='*') {
                sStar=sPos;
                pStar=pPos;
                pPos++;
            //之前匹配过，因*可匹配多个字符，再次利用最近匹配过的*匹配更多的字符
            }else if(sStar!=-1){
                pPos=pStar;
                sPos=++sStar;
            //都不满足匹配失败
            }else {
                return false;
            }
        }

        //字符串匹配完毕，需字符模式为空或剩下'*'才能匹配成功
        while(pPos<n&&p.charAt(pPos)=='*') {
            pPos++;
        }
        return pPos==n;
    }

    public static void main(String[] args) {
        match matcher = new match();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String s = sc.next();
        System.out.println("请输入字符模式：");
        String p = sc.next();
        boolean result = matcher.isMatch(s, p);
        System.out.println("匹配结果：" + result);
    }

}
