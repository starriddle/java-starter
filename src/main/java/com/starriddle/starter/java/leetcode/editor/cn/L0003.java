//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window 
// 👍 3948 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

import java.util.HashMap;

/**
 * 3
 * 无重复字符的最长子串
 * longest-substring-without-repeating-characters
 */
public class L0003 {

    public static void main(String[] args) {
        L0003 instance = new L0003();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
        String s = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int start = 0, maxLength = 0, size = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i ++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                int index = map.get(c);
                if(index >= start) {
                    maxLength = Math.max((i - start), maxLength);
                    start = index +1;
                    if(maxLength >= (size - start)) {
                        return maxLength;
                    }
                }
            }
            map.put(s.charAt(i), i);
        }
        return size - start;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
