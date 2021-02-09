//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 8609 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1
 * 两数之和
 * two-sum
 */
public class L0001 {

    public static void main(String[] args) {
        L0001 instance = new L0001();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        String out = Arrays.toString(solution.twoSum(nums, target));
        System.out.println(out);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];

        // 1. 双重循环
        // for(int i = 0; i < nums.length; i ++) {
        //     for(int j = i+1; j < nums.length; j ++) {
        //         int sum = nums[i] + nums[j];
        //         if(sum == target) {
        //             ret[0] = i;
        //             ret[1] = j
        //             return ret;
        //         }
        //     }
        // }

        // 2. HashMap
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++) {
            if(map.containsKey(nums[i])) {
                ret[0] = map.get(nums[i]);
                ret[1] = i;
                return ret;
            }
            map.put(target - nums[i], i);
        }

        return ret;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

