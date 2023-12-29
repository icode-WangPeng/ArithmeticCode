package GroupOne.editTimes;

class editTimes_memento {
    int[][] memo;

    //dp �����ⷨ�����ݹ�ⷨ
    private int dp(String word1, int i,String word2,int j){
        // word1 �����ˣ�word2 ��û���꣬��� word2 ʣ�ಿ��ɾ��-->���ڴ�ʱi�Ѿ���-1���������ĳ���Ϊ j+1
        if (i == -1) return j+1;
        if (j == -1) return i+1;
        if (memo[i][j]!=-1) return memo[i][j];
        //�������,�������κ��޸�
        if (word1.charAt(i) == word2.charAt(j)){
            memo[i][j] = dp(word1,i-1,word2,j-1);
        }else {
            // �ֱ��Ӧɾ�������ӡ��滻������ÿ�α༭���붼Ҫ +1
            memo[i][j] =  min(dp(word1,i-1,word2,j)+1,
                    dp(word1,i,word2,j-1)+1,
                    dp(word1,i-1,word2,j-1)+1);
        }
        return memo[i][j];
    }
    public int minDistance(String word1, String word2) {
        int m= word1.length(), n = word2.length();
        memo = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                memo[i][j] = -1;
            }
        }
        return dp(word1,m-1,word2,n-1);
    }
    private int min(int a,int b,int c){
        return Math.min(a,Math.min(b,c));
    }
}
