package GroupTwo;

import java.util.*;

public class ShortestPath {
    static HashMap<Integer,String> dictionary=new HashMap<>();
    private int V; // 图中节点的数量
    private LinkedList<Integer>[] adjacencyList; // 邻接链表表示图的数据结构
    public ShortestPath(int v) {
        V = v;
        adjacencyList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }
    // 添加边
    public void addEdge(int src, int dest) {
        adjacencyList[src].add(dest);
        adjacencyList[dest].add(src);
    }
    // 计算源点到所有节点的最短路径
    public void shortestPath(int source) {
        int[] distance = new int[V]; // 存储源点到各节点的最短路径长度
        ArrayList<Integer>[] path=new ArrayList[V];//存储源点到各节点路径
        Arrays.fill(distance, Integer.MAX_VALUE); // 初始化距离为无穷大
        distance[source] = 0; // 源点到自身的距离为0
        path[0]=new ArrayList<>();
        path[0].add(0);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            // 遍历当前节点的邻接节点
            for (int neighbor : adjacencyList[currentNode]) {
                if (distance[neighbor] == Integer.MAX_VALUE) {
                    distance[neighbor] = distance[currentNode] + 1;
                    if(path[neighbor]==null) {
                        path[neighbor]=new ArrayList<>(path[currentNode]);
                    }
                    path[neighbor].add(currentNode);
                    queue.add(neighbor);
                }
            }
        }
        // 打印最短路径
        for (int i = 1; i < V; i++) {
            System.out.print("从湖北到" + dictionary.get(i) + "需要跨越" + (distance[i]-1)+"个省，路径为：");
            for(int j=1;j<path[i].size();j++) {
                System.out.print(dictionary.get(path[i].get(j))+"->");
            }
            System.out.print(dictionary.get(i));
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int V = 30; // 节点数量
        ShortestPath graph = new ShortestPath(V);
//       将数字与省份对应起来
        dictionary.put(0,"湖北");
        dictionary.put(1,"黑龙江");
        dictionary.put(2,"吉林");
        dictionary.put(3,"辽宁");
        dictionary.put(4,"内蒙古");
        dictionary.put(5,"河北");
        dictionary.put(6,"北京");
        dictionary.put(7,"天津");
        dictionary.put(8,"山西");
        dictionary.put(9,"陕西");
        dictionary.put(10,"宁夏");
        dictionary.put(11,"甘肃");
        dictionary.put(12,"新疆");
        dictionary.put(13,"山东");
        dictionary.put(14,"河南");
        dictionary.put(15,"青海");
        dictionary.put(16,"江苏");
        dictionary.put(17,"安徽");
        dictionary.put(18,"重庆");
        dictionary.put(19,"四川");
        dictionary.put(20,"西藏");
        dictionary.put(21,"浙江");
        dictionary.put(22,"江西");
        dictionary.put(23,"湖南");
        dictionary.put(24,"贵州");
        dictionary.put(25,"云南");
        dictionary.put(26,"福建");
        dictionary.put(27,"广东");
        dictionary.put(28,"广西");
        dictionary.put(29,"上海");
        // 添加图的边
        graph.addEdge(0, 9);
        graph.addEdge(0, 14);
        graph.addEdge(0, 22);
        graph.addEdge(0, 23);
        graph.addEdge(0, 18);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 9);
        graph.addEdge(4, 10);
        graph.addEdge(4, 11);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(5, 8);
        graph.addEdge(5, 14);
        graph.addEdge(6, 7);
        graph.addEdge(8, 9);
        graph.addEdge(8, 14);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(9, 19);
        graph.addEdge(9, 18);
        graph.addEdge(10, 11);
        graph.addEdge(11, 12);
        graph.addEdge(11, 15);
        graph.addEdge(11, 19);
        graph.addEdge(11, 20);
        graph.addEdge(12, 15);
        graph.addEdge(12, 20);
        graph.addEdge(13, 14);
        graph.addEdge(13, 16);
        graph.addEdge(13, 17);
        graph.addEdge(14, 16);
        graph.addEdge(14, 17);
        graph.addEdge(15, 19);
        graph.addEdge(15, 20);
        graph.addEdge(16, 17);
        graph.addEdge(16, 21);
        graph.addEdge(16, 29);
        graph.addEdge(17, 21);
        graph.addEdge(17, 22);
        graph.addEdge(18, 19);
        graph.addEdge(18, 23);
        graph.addEdge(18, 24);
        graph.addEdge(19, 20);
        graph.addEdge(19, 24);
        graph.addEdge(19, 25);
        graph.addEdge(20, 25);
        graph.addEdge(21, 22);
        graph.addEdge(21, 26);
        graph.addEdge(21, 29);
        graph.addEdge(22, 23);
        graph.addEdge(22, 27);
        graph.addEdge(23, 24);
        graph.addEdge(23, 27);
        graph.addEdge(23, 28);

        int source = 0; // 源点
        graph.shortestPath(source);
    }
}

