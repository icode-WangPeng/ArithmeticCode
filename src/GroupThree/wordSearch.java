package GroupThree;
import java.util.*;
public class wordSearch {
    // 基于回溯法
    // 递归+剪枝
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(exist(board,word.toCharArray(),i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *若字符！=board[i][j]说明该坐标路径是走不通的
     * @param board 要查找的数组
     * @param word 要查找的单词数组
     * @param i 行
     * @param j 列
     * @param index 当前查找的第几个字符
     */
    public static boolean exist(char[][]board,char[]word,int i,int j,int index){
        //越界；单词不相等
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || board[i][j]!=word[index])return false;
        //每个字符都查找完
        if(index==word.length-1)return true;
        char c=board[i][j]; // 存储原值
        //表示该字符已经找过
        board[i][j]='.';
        boolean res=exist(board,word,i+1,j,index+1)||
                exist(board,word,i-1,j,index+1)||
                exist(board,word,i,j+1,index+1)||
                exist(board,word,i,j-1,index+1);
        board[i][j]=c;
        return res;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入二维字符数组 board
        System.out.println("请输入二维数组的行数:");
        int rows = scanner.nextInt();
        System.out.println("请输入二维数组的列数:");
        int cols = scanner.nextInt();

        char[][] board = new char[rows][cols];
        System.out.println("请输入二维数组:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = scanner.next().charAt(0);
            }
        }
        // 输入要查询的单词 word
        System.out.println("请输入要查询的单词:");
        String word = scanner.next();
        // 创建 wordSearch 实例并进行搜索

        boolean exists = wordSearch.exist(board, word);
        if (exists) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
