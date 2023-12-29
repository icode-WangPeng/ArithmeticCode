package GroupOne.editTimes;

class editTimes_memento {
    int[][] memo;

    //dp 函数解法，即递归解法
    private int dp(String word1, int i,String word2,int j){
        // word1 走完了，word2 还没走完，则把 word2 剩余部分删除-->由于此时i已经是-1，两者相差的长度为 j+1
        if (i == -1) return j+1;
        if (j == -1) return i+1;
        if (memo[i][j]!=-1) return memo[i][j];
        //两个相等,则不用做任何修改
        if (word1.charAt(i) == word2.charAt(j)){
            memo[i][j] = dp(word1,i-1,word2,j-1);
        }else {
            // 分别对应删除、增加、替换操作，每次编辑距离都要 +1
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
