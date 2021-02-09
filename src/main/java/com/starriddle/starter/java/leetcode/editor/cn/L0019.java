//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针 
// 👍 887 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 19
 * 删除链表的倒数第N个节点
 * remove-nth-node-from-end-of-list
 */
public class L0019 {

    public static void main(String[] args) {
        L0019 instance = new L0019();
        instance.test();
    }

    void test() {
        Solution solution = new Solution();
        // TODO
        int n = 2;
        ListNode head = new ListNode(1);
        ListNode tail = head;
        for (int i = 2; i < 6; i++) {
            tail.next = new ListNode(i);
            tail = tail.next;
        }
        head = solution.removeNthFromEnd(head, n);
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // time = 0 ms, 100%
        ListNode prev = null, point = null, node = head;
        while (node != null) {
            if (point == null) {
                if (n == 1) {
                    point = head;
                }
            } else {
                prev = point;
                point = point.next;
            }
            node = node.next;
            n --;
        }
        if (prev == null) {
            return head.next;
        } else {
            prev.next = point.next;
            return head;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
