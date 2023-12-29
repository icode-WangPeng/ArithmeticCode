package IfChoose;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawPanel extends JPanel {

    // �洢���еı�
    ArrayList<Edge> graph;
//    ���е�ѡַ
    Node address;


    public DrawPanel(ArrayList<Edge> graph,Node address){
        this.graph = graph;
        this.address=address;
    }

    // ��дpaintComponent���������ڻ��ƽڵ�ͱ�
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // �����������ɫ
        g2d.setFont(new Font("Arial", Font.BOLD, 15));
        g2d.setColor(Color.BLACK);

        // �������еĽڵ�ͱ�
        for (Edge edge : graph) {
            Node u = edge.from;
            Node v = edge.to;
            int w = edge.weight;

            // ���ƽڵ�
            g2d.fillOval(u.x, u.y, 10, 10);
            g2d.fillOval(v.x, v.y, 10, 10);

            // ���Ʊ�ǩ
            g2d.drawString(u.label, u.x + 10, u.y + 10);
            g2d.drawString(v.label, v.x + 10, v.y + 10);


//            ���Ƽ�ͷ�ͱ�
            drawArrow(g2d,u.x+5,u.y+5,v.x+5,v.y+5,8);

            // ����Ȩ��
            if(u.label.charAt(0)<v.label.charAt(0))
                g2d.drawString(String.valueOf(w), (u.x + v.x) / 2 + 10, (u.y + v.y) / 2 + 10);
            else
                g2d.drawString("("+w+")", (u.x + v.x) / 2 + 20, (u.y + v.y) / 2 + 10);
        }

        // ������ɫΪ��ɫ
        g2d.setColor(Color.RED);
        g2d.fillOval(address.x,address.y,10,10);
        g2d.drawString("The address of the supermarket",address.x-100,address.y+30);

    }
    public static void drawMinTree(List<main.Edge> graph1, int add){
        // ����һ������
        JFrame frame = new JFrame("����ѡַ");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ����һ���ֵ䣬���ڴ洢ÿ������ı�źͶ�Ӧ�Ľڵ�
        // Ԥ����9���ڵ�����
        // ���ڵ��������9���������Լ��������
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

        // ����һ���б����ڴ洢���еı�
        ArrayList<Edge> graph = new ArrayList<>();
        for (main.Edge edge : graph1) {
            graph.add(new Edge(nodeDict.get(edge.from), nodeDict.get(edge.to), edge.weight));
        }

        Node address=nodeDict.get(add);
        // ����һ����ͼ���
        DrawPanel panel = new DrawPanel(graph,address);

        // �������ӵ�������
        frame.add(panel);

        // ���ô��ڿɼ�
        frame.setVisible(true);
    }
//    ���Ƽ�ͷ
    private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2, int arrowSize) {
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int dx = (int) (arrowSize * Math.cos(angle));
        int dy = (int) (arrowSize * Math.sin(angle));

        // �����ͷ�����ε�������
        x2-=dx;
        y2-=dy;
        int[] xPoints = {x2, x2 - dx - dy, x2 - dx + dy};
        int[] yPoints = {y2, y2 - dy + dx, y2 - dy - dx};

        g2d.setColor(Color.BLACK);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.drawLine(x1 +dx, y1 +dy, x2 , y2 );
    }
}


