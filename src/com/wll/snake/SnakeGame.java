package com.wll.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class SnakeGame extends JPanel implements KeyListener, ActionListener {
    int length; //蛇的长度
    int[] snakeX = new int[600]; //蛇坐标X
    int[] snakeY = new int[500]; //蛇坐标Y
    String nowDirection;
    String direction = "RIGHT"; //蛇头方向：RIGHT：右  LEFT：左  UP：上  DOWN：下
    boolean isStart = false;
    Timer timer = new Timer(100, this);//刷新间隔
    int foodX; //食物X坐标
    int foodY; //食物Y坐标
    Random random = new Random();
    boolean isFail = false; //游戏是否结束
    int score; //游戏分数

    //构造方法


    public SnakeGame() {
        init();
        this.setFocusable(true); //获取焦点
        this.addKeyListener(this);//键盘监听
        timer.start();
    }

    //初始化
    public void init() {
        nowDirection = direction;
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        foodX = 25 + 25 * random.nextInt(30);
        foodY = 75 + 25 * random.nextInt(20);
        score = 0;
    }

    @Override
    //画组件
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //清屏
        this.setBackground(Color.white);
        Data.header.paintIcon(this, g, 25, 10);
        g.fillRect(25, 75, 850, 600);

        //画蛇 蛇头
        switch (direction) {
            case "RIGHT" -> Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "LEFT" -> Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "UP" -> Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
            case "DOWN" -> Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        //身体
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        //画食物
        Data.food.paintIcon(this, g, foodX, foodY);

        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑", Font.BOLD, 18));
        g.drawString("长度：" + length, 750, 35);
        g.drawString("分数：" + score, 750, 50);
        //游戏开始提示
        if (!isStart) {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("请按下空格开始游戏！", 250, 350);
        }
        //游戏失败提示
        if (isFail) {
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("失败，请按下空格重新开始！", 200, 350);
        }
    }


    @Override
    //键盘监听
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();//获取键值
        if (key == KeyEvent.VK_SPACE) {//如果是空格
            if (isFail) {
                isFail = false;
                init();//重新开始
            } else {
                isStart = !isStart;
            }
            repaint();
        }
        if (key == KeyEvent.VK_LEFT&&!nowDirection.equals("RIGHT")) {
            direction = "LEFT";
            nowDirection = "LEFT";
        } else if (key == KeyEvent.VK_RIGHT&&!nowDirection.equals("LEFT")) {
            direction = "RIGHT";
            nowDirection = "RIGHT";
        } else if (key == KeyEvent.VK_UP&&!nowDirection.equals("DOWN")) {
            direction = "UP";
            nowDirection = "UP";
        } else if (key == KeyEvent.VK_DOWN&&!nowDirection.equals("UP")) {
            direction = "DOWN";
            nowDirection = "DOWN";
        }

    }

    @Override
    //定时执行操作
    public void actionPerformed(ActionEvent e) {
        if (isStart && !isFail) {
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            switch (direction) {
                case "RIGHT" -> {
                    snakeX[0] = snakeX[0] + 25;
                    if (snakeX[0] > 850) snakeX[0] = 25;
                }
                case "LEFT" -> {
                    snakeX[0] = snakeX[0] - 25;
                    if (snakeX[0] < 25) snakeX[0] = 850;
                }
                case "UP" -> {
                    snakeY[0] = snakeY[0] - 25;
                    if (snakeY[0] < 75) snakeY[0] = 650;
                }
                case "DOWN" -> {
                    snakeY[0] = snakeY[0] + 25;
                    if (snakeY[0] > 650) snakeY[0] = 75;
                }
            }
            //吃食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;
                //添加至蛇尾
                snakeX[length - 1] = snakeX[length - 2] * 2 - snakeX[length - 3];
                snakeY[length - 1] = snakeY[length - 2] * 2 - snakeY[length - 3];
                score += 100;
                foodX = 25 + 25 * random.nextInt(30);
                foodY = 75 + 25 * random.nextInt(20);
            }
            //死亡判断
            for (int i = 1; i < length; i++) {
                if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
                    isFail = true;
                    break;
                }
            }
            repaint();//不断更新页面实现动画
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
