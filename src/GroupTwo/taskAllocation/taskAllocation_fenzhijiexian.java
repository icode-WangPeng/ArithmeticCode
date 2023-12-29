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
    //ÿ�����ֵ֮��
    public static int bestSoFar=sumOfMax(cost, cost.length);

    public static void main(String[] args) {
        getResult();
    }

    //�ڵ��������װ�����
    private static void getResult(){
        ArrayList<Integer> result = new ArrayList<>();
        // ��ʼ�����ȶ��У�����
        PriorityQueue<Node> S = new PriorityQueue<>();
        // ��ʼ��һ����״̬�ռ����Ľ�㣬�������
        Node root = new Node();
        // �����һ��Ŀ�����ֵ
        root.lowerBound = getLowerBound(root);
        // ���ȶ����м������㣬Ȼ��׼���Ӹ���㿪ʼ��״̬�ռ�������������ȱ���
        S.add(root);
        while (!S.isEmpty()) {
            Node node = S.remove(); // ̰�Ĳ��ԣ�ȡ��lowerBound��С�Ľ��
            if (node.lowerBound >= bestSoFar) continue; //��֦
            for (Node child : allFeasibleChildren(node)) { // ��ÿһ��**����չ**���ӽ��
                if (isACompleteSolution(child)) { // �������ӽ���Ѿ���Ҷ�ӽ��
                    bestSoFar = Math.min(bestSoFar, getLowerBound(child)); // ����best_so_far
                    if(getLowerBound(child)==bestSoFar)
                        for (int i=1;i<=cost.length;i++){
                            result.add(child.partialSolution.get(i));

                        }
                } else {
                    S.add(child); //���뵽���ȶ�����
                }
            }
        }
        System.out.println("��֧���޷�:");
        for (int i=0;i< cost.length;i++)
            System.out.println("��"+(i+1)+"���˰�������"+result.get(i));
        System.out.print("��С����Ϊ��"+bestSoFar);
    }

    //���㵱ǰ�����½����ֵ(node&cost����)
    private static int getLowerBound(Node node) {
        int lb = 0;
        // 1. ����ֵ = �Ѿ�����Ĺ�������+δ����Ĺ�������
        for (int key: node.partialSolution.keySet()    //����ѡ���������ۼ���
        ) {
            int val = node.partialSolution.get(key);
            lb += cost[key-1][val-1];
        }
        for (int i = node.depth + 1; i <= cost.length; i++) {   //������Ϊ4
            int min = 10000;   //���Ƚ���Сֵ���ô�һ��
            for (int j = 0; j < cost.length; j++) {    //  ѡ��û�б�ѡ������񲢻�ȡ�����
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

    //���ڵ㴮�������γɱ�������
    private static Iterable<Node> allFeasibleChildren(Node node)  {
        // nodeList���������صĿɵ������󣬰�������node������**����չ**�ӽ��
        ArrayList nodeList = new ArrayList();
        // 1. ȷ��node����ȿ��Ա�֤��һ����չ�ӽ��
        int depth = node.depth + 1;
        if (depth > cost.length){
            return null;
        }

        // 2. �����п��ܵĺ��ӽ��б���
        for (int i = 1; i < cost.length+1; i++) {
            // ��������ǿ���չ�ģ��������ظ��ģ���ǰ���������Ĺ�����
            if (feasible(node, i)) {
                // 0. �������ӽ�㣺ע��ά�����ӽ��������ڲ�����:
                Node childNode = new Node();
                // 1. partialSolution   ��ǰ��Ľڵ���뵽partialSolution�е�ͬʱ��һ������ȵĲ���ͻ�Ľڵ�
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

    //����չ---�����л�δ�������ýڵ�
    private static boolean feasible(Node n, int i) {
        return !n.partialSolution.containsValue(i);
    }

    //��ȡ��������---�Ƿ������Ҷ�ӽڵ�
    private static boolean isACompleteSolution(Node n) {
        return n.depth == cost.length;
    }

    //ÿ�����ֵ֮��
    private static int sumOfMax(int[][] a,int n){
        int sum=0;
        int b[]=new int[n];        //�洢ÿ�����ֵ ע�⣺b[]�ķ�ΧӦ��������
        for (int i = 0; i < n; i++) {
            b[i]=0;        //��ʼ��һ����Сֵ����a[i][j]���бȽϣ�ȡ�����ֵ
            for (int j = 0; j < n; j++) {
                if(b[i]<a[i][j]) {    //����ifѭ���������ֵ
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
