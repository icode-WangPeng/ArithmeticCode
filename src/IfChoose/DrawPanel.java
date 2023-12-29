package IfChoose;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawPanel extends JPanel {

    // 存储所有的边
    ArrayList<Edge> graph;
//    超市的选址
    Node address;


    public DrawPanel(ArrayList<Edge> graph,Node address){
        this.graph = graph;
        this.address=address;
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


//            绘制箭头和边
            drawArrow(g2d,u.x+5,u.y+5,v.x+5,v.y+5,8);

            // 绘制权重
            if(u.label.charAt(0)<v.label.charAt(0))
                g2d.drawString(String.valueOf(w), (u.x + v.x) / 2 + 10, (u.y + v.y) / 2 + 10);
            else
                g2d.drawString("("+w+")", (u.x + v.x) / 2 + 20, (u.y + v.y) / 2 + 10);
        }

        // 设置颜色为红色
        g2d.setColor(Color.RED);
        g2d.fillOval(address.x,address.y,10,10);
        g2d.drawString("The address of the supermarket",address.x-100,address.y+30);

    }
    public static void drawMinTree(List<main.Edge> graph1, int add){
        // 创建一个窗口
        JFrame frame = new JFrame("超市选址");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 定义一个字典，用于存储每个顶点的编号和对应的节点
        // 预定义9个节点坐标
        // 若节点个数超过9个，则需自己添加坐标
        HashMap<Integer, Node> nodeDict = new HashMap<>();
        nodeDict.put(1, new Node(100, 350, "A"));
        nodeDict.put(2, new Node(300, 100, "B"));
        nodeDict.put(3, new Node(500, 200, "C"));
        nodeDict.put(4, new Node(350, 450, "D"));
        nodeDict.put(5, new Node(300, 500, "E"));
        nodeDict.put(6, new Node(400, 620, "F"));
        nodeDict.put(7, new Node(300, 220, "G"));
        nodeDict.put(8, new Node(200, 150, "H"));
        nodeDict.put(9, new Node(550, 450, "I"));

        // 定义一个列表，用于存储所有的边
        ArrayList<Edge> graph = new ArrayList<>();
        for (main.Edge edge : graph1) {
            graph.add(new Edge(nodeDict.get(edge.from), nodeDict.get(edge.to), edge.weight));
        }

        Node address=nodeDict.get(add);
        // 创建一个绘图面板
        DrawPanel panel = new DrawPanel(graph,address);

        // 将面板添加到窗口中
        frame.add(panel);

        // 设置窗口可见
        frame.setVisible(true);
    }
//    绘制箭头
    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2, int arrowSize) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int dx = (int) (arrowSize * Math.cos(angle));
        int dy = (int) (arrowSize * Math.sin(angle));

        // 计算箭头三角形的三个点
        x2-=dx;
        y2-=dy;
        int[] xPoints = {x2, x2 - dx - dy, x2 - dx + dy};
        int[] yPoints = {y2, y2 - dy + dx, y2 - dy - dx};

        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.drawLine(x1 +dx, y1 +dy, x2 , y2 );
    }
}


