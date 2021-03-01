package com.wll.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeStart {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("贪吃蛇");
        jFrame.setSize(900,720);//窗口大小

        //        int w = jFrame.getSize().width;
        //        int h = jFrame.getSize().height;
        //        Toolkit kit = Toolkit.getDefaultToolkit();
        //        Dimension dimension = kit.getScreenSize();
        //        int x = (dimension.width - w) / 2;
        //        int y = (dimension.height - h) / 2;
        //        jFrame.setLocation(x,y);//设置窗口居中
        jFrame.setLocationRelativeTo(jFrame.getOwner());//设置窗口居中

        jFrame.setResizable(false);//窗口大小不可拖动
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口可关闭
        jFrame.add(new SnakeGame());
        jFrame.setVisible(true);
    }
}
