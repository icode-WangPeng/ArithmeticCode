package GroupTwo.AlgorithmComparison;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
* @author icode-wp
* @date 2023/12/25 9:05
* @description swing画点图
*/

public class Mypanel extends JPanel {

    List<Point> list;
    Point a;    //最接近点对a,b
    Point b;


    public Mypanel(List<Point> list, Point a, Point b) {
        this.list = list;
        this.a = a;
        this.b = b;
    }

    //重写paintComponent方法
    @Override
    public void paintComponent(Graphics g) {
        //调用父类方法
        super.paintComponent(g);
        //设置画笔颜色为黑色
        g.setColor(Color.BLACK);

        //绘制x轴和y轴  横坐标从-40到40，纵坐标从-22到22，每个点相距20
        g.drawLine(820, 20, 820, 900); //y轴
        g.drawLine(20, 460, 1620, 460); //x轴

        //绘制x轴和y轴的刻度
        int ky = -22; //y轴刻度值
        for (int i = 0; i <= 44; i++) {
//            g.drawLine(20 + i * 20, 415, 20 + i * 20, 425); //x轴刻度
            g.drawLine(815, 20 + i * 20, 825, 20 + i * 20); //y轴刻度
            //绘制刻度值
            if (ky == 0) {
                ky++;
                continue;

            }
//            g.drawString(String.valueOf(ky), 20 + i * 20, 440); //x轴刻度值
            g.drawString(String.valueOf(ky), 840, 900 - i * 20); //y轴刻度值
            ky++;
        }

        int kx = -40; //x轴刻度值
        for (int i = 0; i <= 80; i++) {
            g.drawLine(20 + i * 20, 455, 20 + i * 20, 465); //x轴刻度
            //绘制刻度值
            if (kx == 0) {
                kx++;
                continue;

            }
            g.drawString(String.valueOf(kx), 20 + i * 20, 480); //x轴刻度值
            kx++;
        }

        //设置画笔颜色为红色
        g.setColor(Color.RED);

        //绘制随机点
        for (int i = 0; i < list.size(); i++) {
            g.fillOval((int) (820 + list.get(i).getX() * 20 - 2), (int) (460 - list.get(i).getY() * 20 - 2), 4, 4);
        }
        g.setColor(Color.BLUE); //设置画笔颜色为蓝色
        g.drawLine((int) (820 + a.getX() * 20), (int) (460 - a.getY() * 20), (int) (820 + b.getX() * 20), (int) (460 - b.getY() * 20)); //绘制直线
    }

    public void draw() {
        // 创建JFrame对象
        JFrame frame = new JFrame("平面两点最小距离");
        // 设置关闭方式
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置大小
        frame.setSize(1800, 1000);
        // 设置居中显示
        frame.setLocationRelativeTo(null);
        // 创建MyPanel对象
//        MyPanel panel = new MyPanel(list, a, b);
        Mypanel panel=new Mypanel(list,a,b);
        // 添加panel到frame中
        frame.add(panel);
        // 设置frame可见
        frame.setVisible(true);
    }
}

