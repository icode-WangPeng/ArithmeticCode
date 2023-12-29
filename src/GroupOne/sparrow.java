package GroupOne;

import java.util.*;

public class sparrow {
    private void judge() {
        System.out.println("�������������е�13���ƣ�");
        Scanner sc = new Scanner(System.in);
        int[] existing = new int[9], testArray = new int[9];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            int num = sc.nextInt();
            // ��ʾ����Ϊi+1���Ƶ�����
            existing[num - 1]++;
        }
        for (int i = 0; i < 9; i++) {
            if (existing[i] < 4) {
                //�Ѳ��������Ƶ����ּ���13�����У��ж��Ƿ���Ժ���
                int num = i + 1;
                //����ԭ������в��������Ƶ���һ����������
                System.arraycopy(existing, 0, testArray, 0, 9);
                testArray[i]++;
                if (judgeHu(testArray, 14, false))
                    res.add(num);
            }
        }
        if (res.isEmpty())
            System.out.println("û�п��Ժ��Ƶķ���");
        else {
            StringBuffer sbf = new StringBuffer();
            sbf.append(res.get(0));
            for (int i = 1; i < res.size(); i++) {
                sbf.append(" or ");
                sbf.append(res.get(i));
            }
            System.out.print("�����Ҫ������ӵ�һ����(���ֿ��ܰ�)��");
            System.out.println(sbf);
        }
    }

    //�ݹ���С��Χ�ص�����---ѡȸͷ���տ��ӣ���˳��
    private boolean judgeHu(int[] arr, int total, boolean hasHead) {
        if (total == 0) return true;
        if (!hasHead) { // ��û��ѡ��ͷ��
            for (int i = 0; i < 9; i++) {
                // ���Ƶ���������2�����ʾ����ѡ��ȸͷ
                if (arr[i] >= 2) {
                    // ѡ��ȸͷ�� �Ƶ�����-2
                    arr[i] -= 2;
                    // �ݹ���ã��ж��Ƿ��п��Ӻ�˳��
                    if (judgeHu(arr, total - 2, true)) return true;
                    // ��û���ҵ����ӻ���˳�ӣ���ȸ�Ƽӻ�ԭ����
                    arr[i] += 2;
                }
            }
            return false;
        } else {
            for (int i = 0; i < 9; i++) {
                if (arr[i] > 0) {
                    // �ճ�����
                    if (arr[i] >= 3) {
                        arr[i] -= 3;
                        if (judgeHu(arr, total - 3, true)) return true;
                        arr[i] += 3;
                    }
                    // �ճ�˳��
                    if (i + 2 < 9 && arr[i + 1] > 0 && arr[i + 2] > 0) {
                        arr[i]--;
                        arr[i + 1]--;
                        arr[i + 2]--;
                        if (judgeHu(arr, total - 3, true)) return true;
                        arr[i]++;
                        arr[i + 1]++;
                        arr[i + 2]++;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new sparrow().judge();
    }
}
