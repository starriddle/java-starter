//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 452 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 69
 * x 的平方根
 * sqrtx
 */
public class L0069 {

    public static void main(String[] args) {
        L0069 instance = new L0069();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 4) {
            return 1;
        }
        int start = 1, mid = x / 2, end = x;
        while (start < mid) {
            long ret = (long)mid * mid;
            if (ret == x) {
                break;
            }
            if (ret > x) {
                end = mid;
            } else {
                start = mid;
            }
            mid = (start + end) / 2;
        }
        return mid;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
