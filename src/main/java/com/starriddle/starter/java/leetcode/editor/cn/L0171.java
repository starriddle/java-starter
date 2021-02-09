//给定一个Excel表格中的列名称，返回其相应的列序号。 
//
// 例如， 
//
//     A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 
//    ...
// 
//
// 示例 1: 
//
// 输入: "A"
//输出: 1
// 
//
// 示例 2: 
//
// 输入: "AB"
//输出: 28
// 
//
// 示例 3: 
//
// 输入: "ZY"
//输出: 701 
//
// 致谢： 
//特别感谢 @ts 添加此问题并创建所有测试用例。 
// Related Topics 数学 
// 👍 162 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 171
 * Excel表列序号
 * excel-sheet-column-number
 */
public class L0171 {

    public static void main(String[] args) {
        L0171 instance = new L0171();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int titleToNumber(String s) {
        int number = 0;
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            number = number * 26 + c + 1 - 'A';
        }
        return number;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
