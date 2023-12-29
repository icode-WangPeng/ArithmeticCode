package GroupTwo.Kruskal;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author icode-wp
 * @Package GroupTwo
 * @date 2023/12/18 9:23
 */
public class Kruskal {
    // 边结构
    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    // 并查集结构
    class UnionFind {
        // 记录结合中连通量个数
        int count;
        // 根节点数组
        int[] parents;
        // 记录树的层级
        // 解决长链表的问题
        int[] rank;

        public UnionFind(int n) {
            // 最开始，联通量个数等于元素个数
            this.count = n;
            parents = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                // 初始时，所有节点本身就是根节点
                parents[i]=i;
                // 最开始所有层级都为1
                rank[i] = 1;

            }
        }

        // {a,b,c,d,e,f}
        // parent{0,0,1,3,3,5}
        // find函数用于寻找根节点
        public int find(int itemIndex) {

            int parentIndex = parents[itemIndex];
            // 如果此时元素下标==根结点下标，则此元素为根节点
            if (parentIndex == itemIndex) {
                return itemIndex;
            } else {
                // 继续寻找对应的父节点  最终递归找到根节点
                parents[itemIndex] = find(parents[itemIndex]);
                // 返回跟根节点下标
                return parents[itemIndex];
            }
        }

        // 通过判断两个结点的根节点是否相同来判断两个元素是否连通
        public boolean isConnected(int aIndex, int bIndex) {
            int aRoot = find(aIndex);
            int bRoot = find(bIndex);
            return aRoot == bRoot;
        }

        // 连接两个树
        public void union(int a, int b) {
            int aRoot = find(a);
            int bRoot = find(b);
            if (rank[aRoot] > rank[bRoot]) {
                parents[bRoot] = aRoot;
            } else if (rank[aRoot] < rank[bRoot]) {
                parents[aRoot] = bRoot;
            } else {
                parents[bRoot] = aRoot;
                rank[aRoot]++;
            }
            // 联通量减一
            count--;
        }

    }


    // 元素个数
    private int n;
    // 边列表
    private List<Edge> edgeList;
    // 引入并查集
    private UnionFind unionFind;

    private int ans;

    public Kruskal(int n,List<Edge> edgeList){
        this.n=n;
        this.edgeList=edgeList;
        // 初始化并查集
        this.unionFind=new UnionFind(n);
    }


    public List<Edge> MST(){

        List<Edge> resultList=new ArrayList<>();
        // 通过权重进行升序排序
        edgeList.sort((a, b) -> a.weight - b.weight);
        for (Edge edge:edgeList){
            // 判断是否连通
            if (!unionFind.isConnected(edge.from,edge.to)){
                // 将两个元素进行连通
                unionFind.union(edge.from,edge.to);
                char a= (char) (edge.from+65);
                char b= (char) (edge.to+65);
                System.out.println(a+"—"+b+":"+edge.weight);
                ans+=edge.weight;
                resultList.add(edge);

                // 若连通个数为一，则跳出循环
                if (unionFind.count==1){
                    break;
                }
            }
        }
        System.out.println(ans);
        return resultList;
    }


    public static int changeLetterToNum(String letter){
        return letter.charAt(0)-'A';
    }

    public static List<Edge> getEdgeList(int[][] nums){
        List<Edge> edgeList1=new ArrayList<>();
        for (int[] num : nums) {
            Edge edge = new Edge(num[0] - 1, num[1] - 1, num[2]);
            edgeList1.add(edge);
        }
        return edgeList1;
    }

    public static void main(String[] args) {

        // 创建一个Scanner对象，用于从命令行读取输入
        Scanner input = new Scanner(System.in);

        // 创建一个ArrayList对象，用于存储多条边
        ArrayList<Edge> edges = new ArrayList<>();

        // 提示用户输入多条边的信息，以0 0 0结束
        System.out.println("请输入多条边的前端点，后端点和权重，用空格隔开，以0 0 0结束");
        System.out.println("节点信息一定从1开始");

        // 使用一个循环来读取多条边的信息
        while (true) {
            // 从命令行读取三个整数，分别赋值给start，end和weight变量
            int start=input.nextInt();
            int end=input.nextInt();
            int weight = input.nextInt();

            // 判断是否输入了0 0 0，如果是，跳出循环
            if (start==0 && end==0 && weight == 0) {
                break;
            }

            // 创建一个Edge对象，用于存储一条边的信息
            Edge edge = new Edge(start-1, end-1, weight);

            // 将Edge对象添加到ArrayList中
            edges.add(edge);
        }


// ============== 以下是测试用例 ================

//        int[][]nums={
//                1 2 8
//                1 3 6
//                2 3 1
//        };
//        int[][] nums = {
//                1 2 4
//                1 8 8
//                2 3 8
//                2 8 11
//                3 4 7
//                3 6 4
//                3 9 2
//                4 5 9
//                4 6 14
//                5 6 10
//                6 7 2
//                7 8 1
//                7 9 6
//                8 9 7
//        };
        draw.drawMinTree(edges,new Kruskal(edges.size(),edges).MST());

    }

}
