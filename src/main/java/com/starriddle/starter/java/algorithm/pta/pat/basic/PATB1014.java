/*
 * PAT (Basic Level) Practice （中文）
 *
 * 1014 福尔摩斯的约会 （20 分）
 *
 * 大侦探福尔摩斯接到一张奇怪的字条：
 * 我们约会吧！ 3485djDkxh4hhGE 2984akDfkkkkggEdsb s&hgsfdk d&Hyscvnm。
 * 大侦探很快就明白了，字条上奇怪的乱码实际上就是约会的时间星期四 14:04，
 * 因为前面两字符串中第 1 对相同的大写英文字母（大小写有区分）是第 4 个字母 D，代表星期四；
 * 第 2 对相同的字符是 E ，那是第 5 个英文字母，代表一天里的第 14 个钟头
 * （于是一天的 0 点到 23 点由数字 0 到 9、以及大写字母 A 到 N 表示）；
 * 后面两字符串第 1 对相同的英文字母 s 出现在第 4 个位置（从 0 开始计数）上，代表第 4 分钟。
 * 现给定两对字符串，请帮助福尔摩斯解码得到约会的时间。
 *
 * 输入格式：
 * 输入在 4 行中分别给出 4 个非空、不包含空格、且长度不超过 60 的字符串。
 * 输出格式：
 * 在一行中输出约会的时间，格式为 DAY HH:MM，其中 DAY 是某星期的 3 字符缩写，
 * 即 MON 表示星期一，TUE 表示星期二，WED 表示星期三，THU 表示星期四，
 * FRI 表示星期五，SAT 表示星期六，SUN 表示星期日。题目输入保证每个测试存在唯一解。
 *
 * 输入样例：
 * 3485djDkxh4hhGE
 * 2984akDfkkkkggEdsb
 * s&hgsfdk
 * d&Hyscvnm
 * 输出样例：
 * THU 14:04
 */
package com.starriddle.starter.java.algorithm.pta.pat.basic;

import java.util.Scanner;

public class PATB1014 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        String str3 = scanner.nextLine();
        String str4 = scanner.nextLine();
        scanner.close();

        String[] days={"MON","TUE","WED","THU","FRI","SAT","SUN"};
        int day=-1,hour=-1,minite=-1;
        for (int i=0; i<str1.length()&&i<str2.length(); i++){
            if (str1.charAt(i)==str2.charAt(i)){
                char c = str1.charAt(i);
                if (day<0 && c>='A' && c<='G'){
                    day=c-'A';
                    continue;
                }
                if (day>=0 && c>='0' && c<='9'){
                    hour = c-'0';
                    break;
                }
                if (day>=0 && c>='A' && c<='N'){
                    hour = c-'A'+10;
                    break;
                }
            }
        }
        for (int i=0; i<str3.length()&&i<str4.length(); i++){
            if (str3.charAt(i)==str4.charAt(i)){
                char c = str3.charAt(i);
                if (c>='A'&&c<='Z' || c>='a'&&c<='z'){
                    minite=i;
                    break;
                }
            }
        }
        System.out.printf(days[day]+" %02d:%02d", hour, minite);
    }
}
