package GroupTwo;

import java.util.*;

public class ShortestPath {
    static HashMap<Integer,String> dictionary=new HashMap<>();
    private int V; // ͼ�нڵ������
    private LinkedList<Integer>[] adjacencyList; // �ڽ������ʾͼ�����ݽṹ
    public ShortestPath(int v) {
        V = v;
        adjacencyList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }
    // ��ӱ�
    public void addEdge(int src, int dest) {
        adjacencyList[src].add(dest);
        adjacencyList[dest].add(src);
    }
    // ����Դ�㵽���нڵ�����·��
    public void shortestPath(int source) {
        int[] distance = new int[V]; // �洢Դ�㵽���ڵ�����·������
        ArrayList<Integer>[] path=new ArrayList[V];//�洢Դ�㵽���ڵ�·��
        Arrays.fill(distance, Integer.MAX_VALUE); // ��ʼ������Ϊ�����
        distance[source] = 0; // Դ�㵽����ľ���Ϊ0
        path[0]=new ArrayList<>();
        path[0].add(0);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            // ������ǰ�ڵ���ڽӽڵ�
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
        // ��ӡ���·��
        for (int i = 1; i < V; i++) {
            System.out.print("�Ӻ�����" + dictionary.get(i) + "��Ҫ��Խ" + (distance[i]-1)+"��ʡ��·��Ϊ��");
            for(int j=1;j<path[i].size();j++) {
                System.out.print(dictionary.get(path[i].get(j))+"->");
            }
            System.out.print(dictionary.get(i));
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int V = 30; // �ڵ�����
        ShortestPath graph = new ShortestPath(V);
//       ��������ʡ�ݶ�Ӧ����
        dictionary.put(0,"����");
        dictionary.put(1,"������");
        dictionary.put(2,"����");
        dictionary.put(3,"����");
        dictionary.put(4,"���ɹ�");
        dictionary.put(5,"�ӱ�");
        dictionary.put(6,"����");
        dictionary.put(7,"���");
        dictionary.put(8,"ɽ��");
        dictionary.put(9,"����");
        dictionary.put(10,"����");
        dictionary.put(11,"����");
        dictionary.put(12,"�½�");
        dictionary.put(13,"ɽ��");
        dictionary.put(14,"����");
        dictionary.put(15,"�ຣ");
        dictionary.put(16,"����");
        dictionary.put(17,"����");
        dictionary.put(18,"����");
        dictionary.put(19,"�Ĵ�");
        dictionary.put(20,"����");
        dictionary.put(21,"�㽭");
        dictionary.put(22,"����");
        dictionary.put(23,"����");
        dictionary.put(24,"����");
        dictionary.put(25,"����");
        dictionary.put(26,"����");
        dictionary.put(27,"�㶫");
        dictionary.put(28,"����");
        dictionary.put(29,"�Ϻ�");
        // ���ͼ�ı�
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

        int source = 0; // Դ��
        graph.shortestPath(source);
    }
}

