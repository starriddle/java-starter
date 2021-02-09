//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表 
// 👍 352 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 83
 * 删除排序链表中的重复元素
 * remove-duplicates-from-sorted-list
 */
public class L0083 {

    public static void main(String[] args) {
        L0083 instance = new L0083();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head != null) {
            ListNode end = head, point = head.next;
            while (point != null) {
                if (end.val != point.val) {
                    end.next = point;
                    end = end.next;
                }
                point = point.next;
            }
            end.next = null;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
