package GroupOne;

import java.util.Arrays;
import java.util.Scanner;

public class Common {
    //����¼�͵ݹ�
    private char[] t1, t2;
    private int[][] cache;
    public int longestCommonSubsequence(String text1, String text2) {
        t1 = text1.toCharArray();
        t2 = text2.toCharArray();
        int n = t1.length, m = t2.length;
        cache = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1); // -1 ��ʾû�з��ʹ�
        }
        return commonSubsequence(n - 1, m - 1);
    }

    private int commonSubsequence(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        //����������ǰλ��ֱ�ӷ��ػ��������е�ֵ
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        //��ǰλ���ַ���ͬ
        if (t1[i] == t2[j]) {
            return cache[i][j] = commonSubsequence(i - 1, j - 1) + 1;
        }
        //��ǰλ���ַ���ͬ
        return cache[i][j] = Math.max(commonSubsequence(i - 1, j), commonSubsequence(i, j - 1));
    }

    public static void main(String[] args) {
        Common common = new Common();
        Scanner sc = new Scanner(System.in);
        System.out.println("�������һ���ַ�����");
        String text1 = sc.next();
        System.out.println("������ڶ����ַ�����");
        String text2 = sc.next();

        int result = common.longestCommonSubsequence(text1, text2);
        System.out.println("����������еĳ�����" + result);
    }
}
