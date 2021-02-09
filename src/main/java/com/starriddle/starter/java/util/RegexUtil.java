package com.starriddle.starter.java.util;

/**
 * description
 *
 * @author CYL
 * @date 2019-08-01
 */
public class RegexUtil {
    public static void main(String[] args) {
        String regex = ".+[0-9]{17}[0-9Xx]";
        String name = "A33221119990112668X";
        System.out.println(name.matches(regex));
    }
}
