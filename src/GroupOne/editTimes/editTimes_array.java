package GroupOne.editTimes;

import java.util.Scanner;

/**
 * &#064;author: YAR <BR/>
 * &#064;time: 2023/12/18 10:51 <BR/>
 * &#064;className: editTimes <BR/>
 * &#064;description
 ***/
public class editTimes_array {

    //��ʼ�����ɶ�ά�����������еĳ�ʼ��
    public static int[][] initList(String word1, String word2){

        int len1=word1.length();
        int len2=word2.length();
        int[][] plan=new int[len1+1][len2+1];

        //�������--ֱ�Ӹ�ֵ
        for (int j=0; j < len2; j++) {  //word1Ϊ" "
            plan[0][j] = j;
        }

        for (int i=0; i < len1; i++) {  //word2Ϊ" "
            plan[i][0] = i;
        }
        return plan;
    }

    //���������--�ݹ�
    public static int miniDistance(int[][] plan,String word1, String word2){
        int min=0;
        for(int i=1;i<plan.length;i++){
            for (int j=1;j<plan[i].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    plan[i][j]=plan[i-1][j-1];
                    continue;
                }
                int insert=plan[i][j-1]+1;
                int delete=plan[i-1][j]+1;
                int update=plan[i-1][j-1]+1;
                plan[i][j]=Math.min(Math.min(insert,delete),update);
            }
        }
        return plan[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        System.out.println("��������༭�ĵ���");
        Scanner scanner=new Scanner(System.in);
        String words1= scanner.nextLine();
        System.out.println("������༭Ŀ�굥��");
        String words2=scanner.nextLine();
        int[][] result=initList(words1,words2);
        System.out.println("��̱༭����Ϊ��"+miniDistance(result,words1,words2));
    }
}
