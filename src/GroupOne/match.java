package GroupOne;

import java.util.Scanner;

public class match {
    //̰���㷨
    //����+˫ָ��
    public boolean isMatch(String s, String p) {
        //sPos,pPos�ֱ��ǵ�һ���ַ����±�   sStar��pStar�ֱ���'*'ƥ�����λ��
        int sPos=0,pPos=0,sStar=-1,pStar=-1,m=s.length(),n=p.length();
        //�ж��ַ����е��ַ�
        while(sPos<m){
            //s��p����ַ���� �� p�ַ���'?'
            if(pPos<n&&(s.charAt(sPos)==p.charAt(pPos)||p.charAt(pPos)=='?')){
                sPos++;
                pPos++;
            //p�ַ���'*'
            } else if (pPos<n&&p.charAt(pPos)=='*') {
                sStar=sPos;
                pStar=pPos;
                pPos++;
            //֮ǰƥ�������*��ƥ�����ַ����ٴ��������ƥ�����*ƥ�������ַ�
            }else if(sStar!=-1){
                pPos=pStar;
                sPos=++sStar;
            //��������ƥ��ʧ��
            }else {
                return false;
            }
        }

        //�ַ���ƥ����ϣ����ַ�ģʽΪ�ջ�ʣ��'*'����ƥ��ɹ�
        while(pPos<n&&p.charAt(pPos)=='*') {
            pPos++;
        }
        return pPos==n;
    }

    public static void main(String[] args) {
        match matcher = new match();
        Scanner sc = new Scanner(System.in);
        System.out.println("�������ַ�����");
        String s = sc.next();
        System.out.println("�������ַ�ģʽ��");
        String p = sc.next();
        boolean result = matcher.isMatch(s, p);
        System.out.println("ƥ������" + result);
    }

}
