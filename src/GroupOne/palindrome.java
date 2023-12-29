package GroupOne;

import java.util.Scanner;

//�����������
public class palindrome {
    //����¼+�ݹ�
    int[][] memo;
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        memo = new int[n][n];// int���� �Զ�����0
        return palidrome(s, 0, n - 1);
    }
    private int palidrome(String s, int i, int j) {
        int res = 0;
        //ֻ�е����ַ�����ֱ�ӷ���������
        if (i == j) {
            return 1;
        }
        //�����г���Ϊ0����
        if (i > j) {
            return 0;
        }
        //˵���Ѿ�������������⣬ֱ�ӷ���
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        //ͷβ���ַ���ȣ���ָ������1����ָ������1
        if (s.charAt(i) == s.charAt(j)) {
            res = Math.max(res, palidrome(s, i + 1, j - 1) + 2);
        } else {//ͷβ�ַ�����ȣ���ָ������1 �� ��ָ������1
            res = Math.max(res, palidrome(s, i + 1, j));
            res = Math.max(res, palidrome(s, i, j - 1));
        }
        memo[i][j] = res;
        return res;
    }

    public static void main(String[] args) {
        palindrome p = new palindrome();
        System.out.println("������һ���ַ���:");
        Scanner sc = new Scanner(System.in);
        String s = sc.next(); // �����ַ���
        int result = p.longestPalindromeSubseq(s);
        System.out.println("����������еĳ�����" + result);
    }

}

