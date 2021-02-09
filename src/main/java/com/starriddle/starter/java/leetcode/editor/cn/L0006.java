//将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。 
//
// 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下： 
//
//L   C   I   R
//E T O E S I I G
//E   D   H   N
// 
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。 
//
// 请你实现这个将字符串进行指定行数变换的函数： 
//
// string convert(string s, int numRows); 
//
// 示例 1: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 3
//输出: "LCIRETOESIIGEDHN"
// 
//
// 示例 2: 
//
// 输入: s = "LEETCODEISHIRING", numRows = 4
//输出: "LDREOEIIECIHNTSG"
//解释:
//
//L     D     R
//E   O E   I I
//E C   I H   N
//T     S     G 
// Related Topics 字符串 
// 👍 739 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 6
 * Z 字形变换
 * zigzag-conversion
 */
public class L0006 {

    public static void main(String[] args) {
        L0006 instance = new L0006();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
        String s= "LEETCODEISHIRING";
        int numRows = 4;
        String ret = "LDREOEIIECIHNTSG";
        System.out.println(ret.equals(solution.convert(s, numRows)));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convert(String s, int numRows) {
        StringBuilder[] rows = new StringBuilder[numRows];
        if (s == null || s.length() <= 2 || numRows < 2 || numRows >= s.length()) {
            return s;
        }
        for (int row = 0; row < numRows; row++) {
            rows[row] = new StringBuilder().append(s.charAt(row));
        }
        for (int index = numRows, row = numRows -2, step = -1; index < s.length(); index++) {
            rows[row].append(s.charAt(index));
            if (row == 0 || row == numRows - 1) {
                step = -step;
            }
            row += step;
        }
        for (int row = 1; row < numRows; row++) {
            rows[row] = rows[0].append(rows[row]);
        }
        return rows[0].toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
