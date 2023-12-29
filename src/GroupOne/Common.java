package GroupOne;

import java.util.Arrays;
import java.util.Scanner;

public class Common {
    //备忘录和递归
    private char[] t1, t2;
    private int[][] cache;
    public int longestCommonSubsequence(String text1, String text2) {
        t1 = text1.toCharArray();
        t2 = text2.toCharArray();
        int n = t1.length, m = t2.length;
        cache = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1); // -1 表示没有访问过
        }
        return commonSubsequence(n - 1, m - 1);
    }

    private int commonSubsequence(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        //如果计算过当前位置直接返回缓存数组中的值
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        //当前位置字符相同
        if (t1[i] == t2[j]) {
            return cache[i][j] = commonSubsequence(i - 1, j - 1) + 1;
        }
        //当前位置字符不同
        return cache[i][j] = Math.max(commonSubsequence(i - 1, j), commonSubsequence(i, j - 1));
    }

    public static void main(String[] args) {
        Common common = new Common();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个字符串：");
        String text1 = sc.next();
        System.out.println("请输入第二个字符串：");
        String text2 = sc.next();

        int result = common.longestCommonSubsequence(text1, text2);
        System.out.println("最长公共子序列的长度是" + result);
    }
}
