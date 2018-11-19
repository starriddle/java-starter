/*
 * PAT (Basic Level) Practice （中文）
 *
 * 1007 素数对猜想 （20 分）
 *
 * 让我们定义d​n​​为：d​n​​=p​n+1​​−p​n​​，其中p​i​​是第i个素数。显然有d​1​​=1，且对于n>1有d​n​​是偶数。
 * “素数对猜想”认为“存在无穷多对相邻且差为2的素数”。
 *
 * 现给定任意正整数N(<10^​5​​)，请计算不超过N的满足猜想的素数对的个数。
 *
 * 输入格式:
 * 输入在一行给出正整数N。
 * 输出格式:
 * 在一行中输出不超过N的满足猜想的素数对的个数。
 *
 * 输入样例:
 * 20
 * 输出样例:
 * 4
 */
package com.cyl.algorithm.pta.pat.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class PATB1007 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int max = scanner.nextInt();
        scanner.close();
        int count =0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        if (max>=5) {
            for (int n=5;n<=max;n+=2){
                boolean isPrime = true;
                for (int i=0; i<list.size() && (list.get(i))*(list.get(i)) <= n; i++) {
                    if (n%(list.get(i))==0){
                        isPrime = false;
                        break;
                    }
                }
                if (isPrime){
                    if (n-list.get(list.size()-1) == 2){
                        count ++;
                    }
                    list.add(n);
                }
            }
        }
        System.out.println(count);
    }
}
