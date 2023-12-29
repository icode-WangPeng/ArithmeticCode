package GroupTwo.taskAllocation;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * &#064;author: YAR <BR/>
 * &#064;time: 2023/12/19 14:59 <BR/>
 * &#064;className: taskAllocation_fenzhijiexian <BR/>
 * &#064;description
 ***/
public class taskAllocation_fenzhijiexian {
    public static int[][] cost = Matrix.inputArray();
    //每行最大值之和
    public static int bestSoFar=sumOfMax(cost, cost.length);

    public static void main(String[] args) {
        getResult();
    }

    //节点遍历与结果装填输出
    private static void getResult(){
        ArrayList<Integer> result = new ArrayList<>();
        // 初始化优先队列，备用
        PriorityQueue<Node> S = new PriorityQueue<>();
        // 初始化一个空状态空间树的结点，即根结点
        Node root = new Node();
        // 计算第一个目标估计值
        root.lowerBound = getLowerBound(root);
        // 优先队列中加入根结点，然后准备从根结点开始对状态空间树进行深度优先遍历
        S.add(root);
        while (!S.isEmpty()) {
            Node node = S.remove(); // 贪心策略：取出lowerBound最小的结点
            if (node.lowerBound >= bestSoFar) continue; //剪枝
            for (Node child : allFeasibleChildren(node)) { // 对每一个**可扩展**的子结点
                if (isACompleteSolution(child)) { // 如果这个子结点已经是叶子结点
                    bestSoFar = Math.min(bestSoFar, getLowerBound(child)); // 更新best_so_far
                    if(getLowerBound(child)==bestSoFar)
                        for (int i=1;i<=cost.length;i++){
                            result.add(child.partialSolution.get(i));

                        }
                } else {
                    S.add(child); //加入到优先队列中
                }
            }
        }
        System.out.println("分支界限法:");
        for (int i=0;i< cost.length;i++)
            System.out.println("第"+(i+1)+"个人安排任务"+result.get(i));
        System.out.print("最小代价为："+bestSoFar);
    }

    //计算当前结点的下界估计值(node&cost矩阵)
    private static int getLowerBound(Node node) {
        int lb = 0;
        // 1. 估计值 = 已经分配的工作代价+未分配的工作代价
        for (int key: node.partialSolution.keySet()    //将已选择的任务代价加上
        ) {
            int val = node.partialSolution.get(key);
            lb += cost[key-1][val-1];
        }
        for (int i = node.depth + 1; i <= cost.length; i++) {   //深度最大为4
            int min = 10000;   //首先将最小值设置大一点
            for (int j = 0; j < cost.length; j++) {    //  选择还没有被选择的任务并获取其代价
                if (!node.partialSolution.containsValue(j)){
                    if (cost[i - 1][j] < min){
                        min = cost[i - 1][j];
                    }
                }
            }
            lb += min;
        }
        return lb;
    }

    //将节点串联起来形成遍历方案
    private static Iterable<Node> allFeasibleChildren(Node node)  {
        // nodeList是用来返回的可迭代对象，包含参数node的所有**可扩展**子结点
        ArrayList nodeList = new ArrayList();
        // 1. 确保node的深度可以保证进一步扩展子结点
        int depth = node.depth + 1;
        if (depth > cost.length){
            return null;
        }

        // 2. 对所有可能的孩子进行遍历
        for (int i = 1; i < cost.length+1; i++) {
            // 如果孩子是可扩展的（即不做重复的，先前别人做过的工作）
            if (feasible(node, i)) {
                // 0. 创建孩子结点：注意维护孩子结点的所有内部属性:
                Node childNode = new Node();
                // 1. partialSolution   将前面的节点加入到partialSolution中的同时加一个本深度的不冲突的节点
                childNode.partialSolution.putAll(node.partialSolution);
                childNode.partialSolution.put(depth,i);
                // 2. depth
                childNode.depth = depth;
                // 3. lowerBound
                childNode.lowerBound = getLowerBound(childNode);

                nodeList.add(childNode);
            }
        }
        return nodeList;
    }

    //可扩展---方案中还未遍历到该节点
    private static boolean feasible(Node n, int i) {
        return !n.partialSolution.containsValue(i);
    }

    //获取方案结束---是否遍历到叶子节点
    private static boolean isACompleteSolution(Node n) {
        return n.depth == cost.length;
    }

    //每行最大值之和
    private static int sumOfMax(int[][] a,int n){
        int sum=0;
        int b[]=new int[n];        //存储每行最大值 注意：b[]的范围应该是行数
        for (int i = 0; i < n; i++) {
            b[i]=0;        //初始化一个最小值，与a[i][j]进行比较，取得最大值
            for (int j = 0; j < n; j++) {
                if(b[i]<a[i][j]) {    //利用if循环，求最大值
                    b[i]=a[i][j];
                }
            }
        }
        for (int i = 0; i < n; i++) {
            sum+=b[i];
        }
        return sum;
    }
}
