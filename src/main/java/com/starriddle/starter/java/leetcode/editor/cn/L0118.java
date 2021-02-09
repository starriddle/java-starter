//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组 
// 👍 331 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 118
 * 杨辉三角
 * pascals-triangle
 */
public class L0118 {

    public static void main(String[] args) {
        L0118 instance = new L0118();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>(numRows);
        for (int row = 0; row < numRows; row++) {
            List<Integer> rowList = new ArrayList<>(row + 1);
            list.add(rowList);
            List<Integer> prevList = row == 0 ? null : list.get(row - 1);
            for (int i = 0; i <= row; i++) {
                if (i==0 || i==row) {
                    rowList.add(1);
                } else {
                    rowList.add(prevList.get(i-1) + prevList.get(i));
                }
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
