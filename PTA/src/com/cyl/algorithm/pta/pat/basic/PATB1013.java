/*
 * PAT (Basic Level) Practice （中文）
 *
 * 1013 数素数 （20 分）
 *
 * 令 P​i​​ 表示第 i 个素数。现任给两个正整数 M≤N≤10​000​​，请输出 P​M​​ 到 P​N​​ 的所有素数。
 *
 * 输入格式：
 * 输入在一行中给出 M 和 N，其间以空格分隔。
 * 输出格式：
 * 输出从 P​M​​ 到 P​N​​ 的所有素数，每 10 个数字占 1 行，其间以空格分隔，但行末不得有多余空格。
 *
 * 输入样例：
 * 5 27
 * 输出样例：
 * 11 13 17 19 23 29 31 37 41 43
 * 47 53 59 61 67 71 73 79 83 89
 * 97 101 103
 */
package com.cyl.algorithm.pta.pat.basic;

import java.util.Scanner;

/**
 * 关键点：得到一个符合条件的素数后立即输出，
 * 如果先获取到所有素数，然后再一次性输出符合条件的素数时，
 * 执行2次循环会导致执行时间超出题目要求(PTA在线测试200ms)
 */
public class PATB1013 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        scanner.close();
        long[] p = new long[n];
        p[0] = 2;
        int index = 0;
        for (long x = 2; index < n; x+=2) {
            boolean isPrime = true;
            if (index>0) {
                for (int i = 1; i < index && p[i] * p[i] <= x; i++) {
                    if (x % p[i] == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            if (isPrime){
                p[index]=x;
                if ((index+1)>=m) {
                    if (index+1 == m) {

                    }else if ((index+1-m)%10==0){
                        System.out.println();
                    }else {
                        System.out.print(" ");
                    }
                    System.out.print(x);
                }
                if (index==0){
                    x=1;
                }
                index++;
            }
        }
    }
}
