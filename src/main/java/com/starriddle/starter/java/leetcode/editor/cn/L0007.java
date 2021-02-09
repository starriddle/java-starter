//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学 
// 👍 2008 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 7
 * 整数反转
 * reverse-integer
 */
public class L0007 {

    public static void main(String[] args) {
        L0007 instance = new L0007();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reverse(int x) {
        long from = x, to = 0;
        int flag = 1;
        if (from<0) {
            from = -from;
            flag = -flag;
        }
        do {
            long num = from % 10;
            from /= 10;
            to = to*10 + num;
        } while (from != 0);
        to *= flag;
        if (to > Integer.MAX_VALUE || to < Integer.MIN_VALUE) {
            to = 0;
        }
        return (int)to;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
