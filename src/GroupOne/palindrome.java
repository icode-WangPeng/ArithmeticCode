package GroupOne;

import java.util.Scanner;

//最长回文子序列
public class palindrome {
    //备忘录+递归
    int[][] memo;
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        memo = new int[n][n];// int类型 自动填入0
        return palidrome(s, 0, n - 1);
    }
    private int palidrome(String s, int i, int j) {
        int res = 0;
        //只有单个字符串，直接返回它本身
        if (i == j) {
            return 1;
        }
        //子序列长度为0或负数
        if (i > j) {
            return 0;
        }
        //说明已经计算过该子问题，直接返回
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        //头尾两字符相等，左指针右移1，右指针左移1
        if (s.charAt(i) == s.charAt(j)) {
            res = Math.max(res, palidrome(s, i + 1, j - 1) + 2);
        } else {//头尾字符不相等：左指针右移1 或 右指针左移1
            res = Math.max(res, palidrome(s, i + 1, j));
            res = Math.max(res, palidrome(s, i, j - 1));
        }
        memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        palindrome p = new palindrome();
        System.out.println("请输入一个字符串:");
        Scanner sc = new Scanner(System.in);
        String s = sc.next(); // 测试字符串
        int result = p.longestPalindromeSubseq(s);
        System.out.println("最长回文子序列的长度是" + result);
    }

}

