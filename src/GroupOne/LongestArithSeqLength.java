package GroupOne;

import java.util.*;

/**
 * @author icode-wp
 * @Package GroupOne
 * @date 2023/12/18 9:22
 */
// ��̬�滮
public class LongestArithSeqLength {
    public static int getLength(int[] A) {
        if(A==null) {
            return 0;
        }
        if(A.length<3) {
            return A.length;
        }
        // �洢��i��jΪβ��ĵǲ����еĳ���
        int[][] dp2 = new int[A.length][A.length];
        Map<Integer, Integer> map = new HashMap<>();

        int ans = 0;
        for(int i=0; i<A.length; i++){
            for(int j=i+1; j<A.length; j++){
                int num = longLength(A, i, j, map, dp2);
                ans = Math.max(ans, num);
            }
            map.put(A[i], i);
        }
        return ans;
    }
    private static int longLength(int[] A, int i, int j, Map<Integer, Integer> map, int[][] dp2){
        if(dp2[i][j]!=0) {
            return dp2[i][j];
        }
        if(i==0) {
            dp2[i][j]=2;
            return 2;
        }
        int diff = A[j]-A[i];
        //�Ƿ���� k, ʹ�� A[i]-A[k] = diff
        if(map.containsKey(A[i]-diff)){
            int preAns = longLength(A, map.get(A[i]-diff), i, map, dp2);
            dp2[i][j] = preAns + 1;
            return preAns + 1;
        }
        dp2[i][j]=2;
        return 2;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("������Ԫ��:"+" "+"-1ֹͣ");
        List<Integer> list=new ArrayList<>();
        while (true){
            int input=scanner.nextInt();
            if (input==-1){
                break;
            }
            list.add(input);
        }
        int[]nums=new int[list.size()];
        for (int i=0;i<list.size();i++ ){
            nums[i]=list.get(i);
        }

        System.out.println("��Ȳ������еĳ���Ϊ:"+getLength(nums));

    }
}
