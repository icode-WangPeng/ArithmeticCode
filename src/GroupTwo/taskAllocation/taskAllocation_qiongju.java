package GroupTwo.taskAllocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * &#064;author: YAR <BR/>
 * &#064;time: 2023/12/19 14:58 <BR/>
 * &#064;className: taskAllocation <BR/>
 * &#064;description
 ***/
public class taskAllocation_qiongju {
    private static final int INF = 9999;
    private static int n;
    private static int[][] c;
    private static List<List<Integer>> ps = new ArrayList<>();

    public static void insert(List<Integer> s, int i, List<List<Integer>> ps1) {
        for (int j = 0; j <= s.size(); j++) {
            List<Integer> s1 = new ArrayList<>(s);
            s1.add(j, i);
            ps1.add(s1);
        }
    }

    public static void perm(int n) {
        for (int i = 2; i <= n; i++) {
            List<List<Integer>> ps1 = new ArrayList<>();
            for (List<Integer> integers : ps) {
                insert(integers, i, ps1);
            }
            ps = ps1;
        }
    }

    public static int[] allocate(int n) {
        perm(n);
        int minCost = INF;
        int minI = -1;
        //����ÿ�ַ���
        for (int i = 0; i < ps.size(); i++) {
            int cost = 0;
            for (int j = 0; j < n; j++) {
                //j��Ӧ�ĵڼ����˸ɵڼ�������
                cost += c[j][ps.get(i).get(j) - 1];
            }
            if (cost < minCost) {
                minCost = cost;
                minI = i;
            }
        }
        return new int[]{minI, minCost};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("���������Ľ���:");
        n = scanner.nextInt();
        c = new int[n][n];
        System.out.println("������þ���");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = scanner.nextInt();
            }
        }
        ps.add(new ArrayList<>(Collections.singletonList(1)));
        int[] result = allocate(n);
        System.out.println("��ٷ�:");
        for (int k = 0; k < n; k++) {
            System.out.printf("��%d���˰�������%d\n", k + 1, ps.get(result[0]).get(k));
        }
        System.out.printf("��С����Ϊ��%d\n", result[1]);
    }

}
