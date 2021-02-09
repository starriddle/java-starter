//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 3
//输出: [1,3,3,1]
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(k) 空间复杂度吗？ 
// Related Topics 数组 
// 👍 164 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 119
 * 杨辉三角 II
 * pascals-triangle-ii
 */
public class L0119 {

    public static void main(String[] args) {
        L0119 instance = new L0119();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>(rowIndex+1);
        for (int row = 0; row <= rowIndex; row++) {
            list.add(row, 1);
            for (int index = row-1; index > 0 ; index--) {
                list.set(index, list.get(index) + list.get(index-1));
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
