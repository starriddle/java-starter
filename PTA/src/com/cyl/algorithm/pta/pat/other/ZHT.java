package com.cyl.algorithm.pta.pat.other;

/*
 * 九宫格——纵横图
 *
 * |  |  |  |  | 1|  | 4| 2| 6|
 * |  |  | 8| 4|  |  | 5| 3|  |
 * |  | 2|  |  |  |  |  |  |  |
 * |  |  |  | 8|  |  |  |  | 4|
 * | 2| 6|  |  |  | 9|  |  | 7|
 * |  |  |  | 7|  |  |  |  | 5|
 * |  | 5|  |  |  |  |  |  |  |
 * |  |  | 1| 5|  |  | 6| 4|  |
 * |  |  |  |  | 3|  | 7| 5| 1|
 *
 */

/**
 * @author CYL
 * @date 2018-11-01
 */
public class ZHT {

    private static Point[][] points = new Point[9][9]; // 纵横图
    private static int lastX = 8; // 最后一个需要设置值的坐标
    private static int lastY = 8; // 最后一个需要设置值的坐标

    public static void main(String[] args){

        initZHT(); // 初始化 九宫图
        printZHT();
        System.out.println("开始解题……");
        boolean success;
        int startX=0, startY=0;
        do {
            long start = System.currentTimeMillis();
            success = calcZHT(startX,startY); // 九宫图 求解
            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.print("耗时 " + (end-start) + " 毫秒，");
            if (success) {
                System.out.println("解题成功：");
                printZHT();
                startX = lastX;
                startY = lastY;
                System.out.println("继续解题……");
            } else {
                System.out.println("解题失败！");
            }
        } while (success);
    }

    private static void printZHT() {
        System.out.println("┌──-──┬──-──┬──-──┐");
        for (int i = 0; i < 9; i ++) {
            System.out.print("│");
            for (int j = 0; j < 9; j ++) {
                System.out.print(" " + points[i][j].getValue() + " ");
                if (j==2 || j==5 || j==8){
                    System.out.print("│");
                }
            }
            System.out.println();
            if (i==2 || i==5){
                System.out.println("├──-──┼──-──┼──-──┤");
            }
        }
        System.out.println("└──-──┴──-──┴──-──┘");
        System.out.println();
    }

    private static boolean calcZHT(int x, int y) {
        boolean next = true; // 前进/倒退
        while (x >= 0 && x < 9) {
            if(points[x][y].isVariable()) {
                int value = points[x][y].getValue();
                while(true) {
                    value ++;
                    if (value>9) {
                        points[x][y].setValue(0);// 所有数字均不符合，重置，推到前一坐标
                        next = false;
                        break;
                    }
                    if(checkZHT(x,y,value)) { // 检查 value 是否符合要求
                        points[x][y].setValue(value);
                        next = true;
                        break;
                    }
                }
            }

            // 定位至下一个坐标，前进或者后退
            y = next ? y+1 : y-1;
            if (y==9) {
                x++;
                y=0;
            }
            if (y==-1){
                x--;
                y=8;
            }
        }
        return x >=0 ;
    }

    private static boolean checkZHT(int x, int y, int value) {
        for (int i = 0; i < 9; i ++) {
            // 同行比较
            if (points[x][i].getValue() == value) {
                return false;
            }
            // 同列比较
            if (points[i][y].getValue() == value) {
                return false;
            }
        }
        // 九宫格内比较
        int startX = x/3*3;
        int startY = y/3*3;
        for (int i = startX; i < startX+3; i ++) {
            for (int j = startY; j < startY+3; j ++) {
                if (points[i][j].getValue() == value) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void initZHT() {
        int[][] init = {
//
//                {0,0,0,0,1,0,4,2,6},
//                {0,0,8,4,0,0,5,3,0},
//                {0,2,0,0,0,0,0,0,0},
//
//                {0,0,0,8,0,0,0,0,4},
//                {2,6,0,0,0,9,0,0,7},
//                {0,0,0,7,0,0,0,0,5},
//
//                {0,5,0,0,0,0,0,0,0},
//                {0,0,1,5,0,0,6,4,0},
//                {0,0,0,0,3,0,7,5,1}

                {9,3,0,1,6,0,0,0,0},
                {5,0,6,7,0,0,0,3,0},
                {4,0,1,0,0,0,0,0,0},

                {1,0,0,0,0,0,8,0,0},
                {0,0,2,9,1,6,3,0,0},
                {0,0,3,0,0,0,0,0,4},

                {0,0,0,0,0,0,2,0,3},
                {0,1,0,0,0,3,5,0,9},
                {0,0,0,0,9,7,0,8,1}

        };

        lastY = 6;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (init[i][j] == 0) {
                    points[i][j] = new Point();
                } else {
                    points[i][j] = new Point(false,init[i][j]);
                }
            }
        }
    }
}

class Point {

    /**
     * 是否 可变/未设置固定值
     */
    private boolean variable;

    /**
     * 值
     */
    private int value;

    Point() {
        this.variable = true;
        this.value = 0;
    }

    Point(boolean variable, int value) {
        this.variable = variable;
        this.value = value;
    }

    boolean isVariable() {
        return variable;
    }

    void setVariable(boolean variable) {
        this.variable = variable;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
