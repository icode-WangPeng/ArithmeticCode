package GroupOne;

import java.util.*;

public class sparrow {
    private void judge() {
        System.out.println("请输入手中已有的13张牌：");
        Scanner sc = new Scanner(System.in);
        int[] existing = new int[9], testArray = new int[9];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            int num = sc.nextInt();
            // 表示牌面为i+1的牌的数量
            existing[num - 1]++;
        }
        for (int i = 0; i < 9; i++) {
            if (existing[i] < 4) {
                //把不够四张牌的数字加入13张牌中，判断是否可以和牌
                int num = i + 1;
                //不对原数组进行操作，复制到另一个数组后操作
                System.arraycopy(existing, 0, testArray, 0, 9);
                testArray[i]++;
                if (judgeHu(testArray, 14, false))
                    res.add(num);
            }
        }
        if (res.isEmpty())
            System.out.println("没有可以和牌的方案");
        else {
            StringBuffer sbf = new StringBuffer();
            sbf.append(res.get(0));
            for (int i = 1; i < res.size(); i++) {
                sbf.append(" or ");
                sbf.append(res.get(i));
            }
            System.out.print("输出需要额外添加的一张牌(多种可能版)：");
            System.out.println(sbf);
        }
    }

    //递归缩小范围回调函数---选雀头，凑刻子，凑顺子
    private boolean judgeHu(int[] arr, int total, boolean hasHead) {
        if (total == 0) return true;
        if (!hasHead) { // 还没有选出头牌
            for (int i = 0; i < 9; i++) {
                // 若牌的数量大于2，则表示可以选出雀头
                if (arr[i] >= 2) {
                    // 选出雀头后 牌的数量-2
                    arr[i] -= 2;
                    // 递归调用，判断是否有刻子和顺子
                    if (judgeHu(arr, total - 2, true)) return true;
                    // 若没有找到刻子或者顺子，则将雀牌加回原数组
                    arr[i] += 2;
                }
            }
            return false;
        } else {
            for (int i = 0; i < 9; i++) {
                if (arr[i] > 0) {
                    // 凑出刻子
                    if (arr[i] >= 3) {
                        arr[i] -= 3;
                        if (judgeHu(arr, total - 3, true)) return true;
                        arr[i] += 3;
                    }
                    // 凑出顺子
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
