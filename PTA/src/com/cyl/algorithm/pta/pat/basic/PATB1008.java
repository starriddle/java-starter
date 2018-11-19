/*
 * PAT (Basic Level) Practice （中文）
 *
 * 1008 数组元素循环右移问题 （20 分）
 *
 * 一个数组A中存有N（>0）个整数，在不允许使用另外数组的前提下，将每个整数循环向右移M（≥0）个位置，
 * 即将A中的数据由（A​0​​A​1​​⋯A​N−1​​）变换为（A​N−M​​⋯A​N−1​​A​0​​A​1​​⋯A​N−M−1​​）（最后M个数循环移至最前面的M个位置）。
 * 如果需要考虑程序移动数据的次数尽量少，要如何设计移动的方法？
 *
 * 输入格式:
 * 每个输入包含一个测试用例，第1行输入N（1≤N≤100）和M（≥0）；第2行输入N个整数，之间用空格分隔。
 * 输出格式:
 * 在一行中输出循环右移M位以后的整数序列，之间用空格分隔，序列结尾不能有多余空格。
 *
 * 输入样例:
 * 6 2
 * 1 2 3 4 5 6
 * 输出样例:
 * 5 6 1 2 3 4
 */
package com.cyl.algorithm.pta.pat.basic;

import java.util.Scanner;

public class PATB1008 {

    public static void main(String[] args){
        // 输入
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] array = new int[n];
        for (int i=0 ; i<n ; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        // 计算
        m %= n;
        if (m!=0) {
            // 计算移动轮数：最大公约数
            int count = m;
            while (n % count != 0) {
                n = n % count;
                n = count + n;
                count = n - count;
                n = n - count;
            }
            n = array.length;
            // 移动
            for (int i = 0; i < count; i++) {
                int index = i;
                while (true) {
                    index = (index + m) % n;
                    if (index == i) {
                        break;
                    }
                    int tmp = array[index];
                    array[index] = array[i];
                    array[i] = tmp;
                }
            }
        }
        // 输出
        StringBuilder sb = new StringBuilder(""+array[0]);
        for (int i=1; i<array.length; i++){
            sb.append(" "+array[i]);
        }
        System.out.println(sb.toString());
    }
}
