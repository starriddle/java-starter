//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串 
// 👍 1684 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

import java.util.Stack;

/**
 * 20
 * 有效的括号
 * valid-parentheses
 */
public class L0020 {

    public static void main(String[] args) {
//        L0020_valid_parentheses instance = new L0020_valid_parentheses();
//        instance.test();
        System.out.println('('+0);
        System.out.println('['+0);
        System.out.println('{'+0);
        System.out.println(')'+0);
        System.out.println(']'+0);
        System.out.println('}'+0);
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        if (len % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        boolean ret = true;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            switch (c) {
                case ')':
                    if (stack.empty() || stack.pop() != '(') {
                        ret = false;
                    }
                    break;
                case ']':
                    if (stack.empty() || stack.pop() != '[') {
                        ret = false;
                    }
                    break;
                case '}':
                    if (stack.empty() || stack.pop() != '{') {
                        ret = false;
                    }
                    break;
                default:
                    stack.push(c);
            }
            if (!ret) {
                break;
            }
        }
        return ret && stack.empty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
