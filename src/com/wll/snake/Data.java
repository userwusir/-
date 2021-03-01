package com.wll.snake;

import javax.swing.*;
import java.net.URL;

//存储外部数据
public class Data {
    //顶栏图片   URL：定位图片地址   ImageIcon：图片
    public static URL headerUrl = Data.class.getResource("/static/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);
    //身体图片
    public static URL bodyUrl = Data.class.getResource("/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //头部图片：上下左右
    public static URL upUrl = Data.class.getResource("/static/up.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static URL downUrl = Data.class.getResource("/static/down.png");
    public static ImageIcon down = new ImageIcon(downUrl);
    public static URL leftUrl = Data.class.getResource("/static/left.png");
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static URL rightUrl = Data.class.getResource("/static/right.png");
    public static ImageIcon right = new ImageIcon(rightUrl);
    //食物图片
    public static URL foodUrl = Data.class.getResource("/static/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}
