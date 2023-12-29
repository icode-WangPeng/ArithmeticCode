package GroupTwo.AlgorithmComparison;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
* @author icode-wp
* @date 2023/12/25 9:05
* @description swing����ͼ
*/

public class Mypanel extends JPanel {

    List<Point> list;
    Point a;    //��ӽ����a,b
    Point b;


    public Mypanel(List<Point> list, Point a, Point b) {
        this.list = list;
        this.a = a;
        this.b = b;
    }

    //��дpaintComponent����
    @Override
    public void paintComponent(Graphics g) {
        //���ø��෽��
        super.paintComponent(g);
        //���û�����ɫΪ��ɫ
        g.setColor(Color.BLACK);

        //����x���y��  �������-40��40���������-22��22��ÿ�������20
        g.drawLine(820, 20, 820, 900); //y��
        g.drawLine(20, 460, 1620, 460); //x��

        //����x���y��Ŀ̶�
        int ky = -22; //y��̶�ֵ
        for (int i = 0; i <= 44; i++) {
//            g.drawLine(20 + i * 20, 415, 20 + i * 20, 425); //x��̶�
            g.drawLine(815, 20 + i * 20, 825, 20 + i * 20); //y��̶�
            //���ƿ̶�ֵ
            if (ky == 0) {
                ky++;
                continue;

            }
//            g.drawString(String.valueOf(ky), 20 + i * 20, 440); //x��̶�ֵ
            g.drawString(String.valueOf(ky), 840, 900 - i * 20); //y��̶�ֵ
            ky++;
        }

        int kx = -40; //x��̶�ֵ
        for (int i = 0; i <= 80; i++) {
            g.drawLine(20 + i * 20, 455, 20 + i * 20, 465); //x��̶�
            //���ƿ̶�ֵ
            if (kx == 0) {
                kx++;
                continue;

            }
            g.drawString(String.valueOf(kx), 20 + i * 20, 480); //x��̶�ֵ
            kx++;
        }

        //���û�����ɫΪ��ɫ
        g.setColor(Color.RED);

        //���������
        for (int i = 0; i < list.size(); i++) {
            g.fillOval((int) (820 + list.get(i).getX() * 20 - 2), (int) (460 - list.get(i).getY() * 20 - 2), 4, 4);
        }
        g.setColor(Color.BLUE); //���û�����ɫΪ��ɫ
        g.drawLine((int) (820 + a.getX() * 20), (int) (460 - a.getY() * 20), (int) (820 + b.getX() * 20), (int) (460 - b.getY() * 20)); //����ֱ��
    }

    public void draw() {
        // ����JFrame����
        JFrame frame = new JFrame("ƽ��������С����");
        // ���ùرշ�ʽ
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ���ô�С
        frame.setSize(1800, 1000);
        // ���þ�����ʾ
        frame.setLocationRelativeTo(null);
        // ����MyPanel����
//        MyPanel panel = new MyPanel(list, a, b);
        Mypanel panel=new Mypanel(list,a,b);
        // ���panel��frame��
        frame.add(panel);
        // ����frame�ɼ�
        frame.setVisible(true);
    }
}

