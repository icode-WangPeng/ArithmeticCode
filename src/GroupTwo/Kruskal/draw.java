package GroupTwo.Kruskal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author icode-wp
 * @Package GroupTwo.Kruskal
 * @date 2023/12/20 15:11
 */
public class draw{
    // 定义一个辅助类，用于表示节点和边
  static  class Node {
        int x; // 节点的x坐标
        int y; // 节点的y坐标
        String label; // 节点的标签

        public Node(int x, int y, String label) {
            this.x = x;
            this.y = y;
            this.label = label;
        }
    }

   static class Edge {
        Node from; // 边的起点
        Node to; // 边的终点
        int weight; // 边的权重

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


    // 定义一个绘图面板，用于显示最小生成树
   static class DrawPanel extends JPanel {
        // 存储所有的边
        ArrayList<Edge> graph;

        // 存储最小生成树的边
        ArrayList<Edge> result;

        public DrawPanel(ArrayList<Edge> graph, ArrayList<Edge> result) {
            this.graph = graph;
            this.result = result;
        }

        // 重写paintComponent方法，用于绘制节点和边
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 设置字体和颜色
            g2d.setFont(new Font("Arial", Font.BOLD, 15));
            g2d.setColor(Color.BLACK);

            // 绘制所有的节点和边
            for (Edge edge : graph) {
                Node u = edge.from;
                Node v = edge.to;
                int w = edge.weight;

                // 绘制节点
                g2d.fillOval(u.x, u.y, 10, 10);
                g2d.fillOval(v.x, v.y, 10, 10);

                // 绘制标签
                g2d.drawString(u.label, u.x + 10, u.y + 10);
                g2d.drawString(v.label, v.x + 10, v.y + 10);

                // 绘制边
                g2d.drawLine(u.x + 5, u.y + 5, v.x + 5, v.y + 5);
                // 绘制权重
                g2d.drawString(String.valueOf(w), (u.x + v.x) / 2 + 10, (u.y + v.y) / 2 + 10);
            }

            // 设置颜色为红色
            g2d.setColor(Color.RED);

            // 绘制最小生成树的边
            for (Edge edge : result) {
                Node u = edge.from;
                Node v = edge.to;

                // 绘制边
                g2d.drawLine(u.x + 5, u.y + 5, v.x + 5, v.y + 5);
            }
        }
    }


    // 根据输入的图和最小生成树，形成图片
    public static void drawMinTree(List<Kruskal.Edge> graph1, List<Kruskal.Edge> result1){
        // 创建一个窗口
        JFrame frame = new JFrame("最小生成树");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 定义一个字典，用于存储每个顶点的编号和对应的节点
        // 预定义9个节点坐标
        // 若节点个数超过9个，则需自己添加坐标
        HashMap<Integer, Node> nodeDict = new HashMap<>();
        nodeDict.put(1, new Node(100, 250, "A"));
        nodeDict.put(2, new Node(300, 100, "B"));
        nodeDict.put(3, new Node(500, 200, "C"));
        nodeDict.put(4, new Node(100, 300, "D"));
        nodeDict.put(5, new Node(300, 300, "E"));
        nodeDict.put(6, new Node(400, 120, "F"));
        nodeDict.put(7, new Node(300, 220, "G"));
        nodeDict.put(8, new Node(200, 150, "H"));
        nodeDict.put(9, new Node(550, 150, "I"));


        // 定义一个列表，用于存储所有的边
        ArrayList<Edge> graph = new ArrayList<>();
        for (Kruskal.Edge edge : graph1) {
            graph.add(new Edge(nodeDict.get(edge.from+1), nodeDict.get(edge.to+1), edge.weight));
        }

        // 定义一个列表，用于存储最小生成树的边
        ArrayList<Edge> result = new ArrayList<>();
        for (Kruskal.Edge edge : result1){
            result.add(new Edge(nodeDict.get(edge.from+1),nodeDict.get(edge.to+1), edge.weight));
        }

        // 创建一个绘图面板
        DrawPanel panel = new DrawPanel(graph, result);

        // 将面板添加到窗口中
        frame.add(panel);

        // 设置窗口可见
        frame.setVisible(true);

    }


}
