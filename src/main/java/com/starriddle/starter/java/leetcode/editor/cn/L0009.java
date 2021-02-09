//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。 
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学 
// 👍 1141 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 9
 * 回文数
 * palindrome-number
 */
public class L0009 {

    public static void main(String[] args) {
        L0009 instance = new L0009();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(int x) {

        // 数字处理
//        if (x < 0) {
//            return false;
//        }
//        int y = 0, tmp = x;
//        while (tmp > 0) {
//             y = y * 10 + (tmp % 10);
//             tmp /= 10;
//        }
//        return y == x;

        // 字符串处理
//        if (x < 0) {
//            return false;
//        }
//        String str = String.valueOf(x);
//        int len = str.length();
//        for (int index = 0; index < len / 2; index++) {
//            if(str.charAt(index) != str.charAt(len -1 -index)) {
//                return false;
//            }
//        }
//        return true;

        // 数字处理 2
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        if (x % 10 == 0) {
            return false;
        }
        int y = 0;
        while (x > y) {
             y = y * 10 + (x % 10);
             x /= 10;
        }
        return x == y || x == y / 10;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
