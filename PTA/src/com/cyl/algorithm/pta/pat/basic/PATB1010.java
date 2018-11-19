/*
 * PAT (Basic Level) Practice （中文）
 *
 * 1010 一元多项式求导 （25 分）
 *
 * 设计函数求一元多项式的导数。（注：x​^n​​（n为整数）的一阶导数为 n·x^(​n−1)​​。）
 *
 * 输入格式:
 * 以指数递降方式输入多项式非零项系数和指数（绝对值均为不超过 1000 的整数）。数字间以空格分隔。
 * 输出格式:
 * 以与输入相同的格式输出导数多项式非零项的系数和指数。数字间以空格分隔，但结尾不能有多余空格。
 * 注意“零多项式”的指数和系数都是 0，但是表示为 0 0。
 *
 * 输入样例:
 * 3 4 -5 2 6 1 -2 0
 * 输出样例:
 * 12 3 -10 1 6 0
 */
package com.cyl.algorithm.pta.pat.basic;

import java.util.Scanner;

public class PATB1010 {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        scanner.close();

        //以空格分开，可能多个空格
        String[] ss = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<ss.length;i+=2){
            int a = Integer.valueOf(ss[i]);
            int n = Integer.valueOf(ss[i+1]);
            // 跳过常数项
            if (n!=0)  {
                sb.append(" "+(a*n)+" "+(n-1));
            }
        }
        String result = sb.toString().trim();
        // 只有常数项，即零多项式
        if (result.isEmpty()){
            result = "0 0";
        }
        System.out.println(result);
    }
}
