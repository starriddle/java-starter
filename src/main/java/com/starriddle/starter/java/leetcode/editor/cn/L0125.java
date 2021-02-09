//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串 
// 👍 254 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 125
 * 验证回文串
 * valid-palindrome
 */
public class L0125 {

    public static void main(String[] args) {
        L0125 instance = new L0125();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        if (s==null || s.isBlank()) {
            return true;
        }
        s = s.toLowerCase();
        for (int left = 0, right = s.length()-1; left < right; ) {
            char leftChar = s.charAt(left), rightChar = s.charAt(right);
            if (leftChar >= 'a' && leftChar <= 'z' || leftChar >= '0' && leftChar <= '9') {
                if (rightChar >= 'a' && rightChar <= 'z' || rightChar >= '0' && rightChar <= '9') {
                    if (leftChar == rightChar) {
                        left ++;
                        right --;
                    } else {
                        return false;
                    }
                } else {
                    right --;
                }
            } else {
                left ++;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
