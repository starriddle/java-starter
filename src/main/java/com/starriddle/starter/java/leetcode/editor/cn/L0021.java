//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1143 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 21
 * 合并两个有序链表
 * merge-two-sorted-lists
 */
public class L0021 {

    public static void main(String[] args) {
        L0021 instance = new L0021();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode list = new ListNode();
        ListNode node = list;
        while (true) {
            if (l1 == null) {
                node.next = l2;
                break;
            }
            if (l2 == null) {
                node.next = l1;
                break;
            }
            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }
        return list.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
