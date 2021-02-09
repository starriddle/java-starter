//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学 
// 👍 4568 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 2
 * 两数相加
 * add-two-numbers
 */
public class L0002 {

    public static void main(String[] args) {
        L0002 instance = new L0002();
        instance.test();
    }

    void test(){
        Solution solution = new Solution();
        // TODO
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(7);
        l2.next = new ListNode(0);
        l2.next.next = new ListNode(8);

        l1 = solution.addTwoNumbers(l1,l2);
        System.out.print(l1.val);
        while (l1.next != null) {
            l1 = l1.next;
            System.out.print(" -> " + l1.val);
        }
        System.out.println();
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        node.next = new ListNode(0);
        ListNode list = node.next;
        do {
            int v1 = 0;
            if(l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            int v2 = 0;
            if(l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }
            if(node.next == null) {
                node.next = new ListNode(0);
            }
            node = node.next;
            int sum = node.val + v1 + v2;
            node.val = sum % 10;
            if(sum >= 10) {
                node.next = new ListNode(1);
            }
        } while(l1 != null || l2 != null);
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
