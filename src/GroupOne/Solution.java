package GroupOne;

import java.util.Scanner;

public class Solution {
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length,len=1;
        if (n == 0) {
            return 0;
        }
        int[] sub = new int[n + 1];
        sub[0] = -32768;
        sub[len]=nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > sub[len]) {
                sub[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (sub[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                sub[pos + 1] = nums[i];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入一个串长度：");
        int n=sc.nextInt();
        int[] s=new int[n];
        System.out.println("请依次输入数组中的内容");
        for(int i=0;i<n;i++)
            s[i]=sc.nextInt();
        System.out.println(lengthOfLIS(s));
    }
}
