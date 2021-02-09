//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 616 👎 0

package com.starriddle.starter.java.leetcode.editor.cn;

/**
 * 155
 * 最小栈
 * min-stack
 */
public class L0155 {

    public static void main(String[] args) {
        L0155 instance = new L0155();
        instance.test();
    }

    void test() {
        // Solution solution = new Solution();
        // TODO
    }

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

        int val;
        MinStack next;
        MinStack min;

    /** initialize your data structure here. */
    public MinStack() {

    }
    
    public void push(int x) {
        MinStack stack = new MinStack();
        stack.val = x;
        if (next==null) {
            stack.min = stack;
            next = stack;
        } else {
            stack.min = next.min.val > x ? stack : next.min;
            stack.next = next;
            next = stack;
        }
    }
    
    public void pop() {
        next = next.next;
    }
    
    public int top() {
        return next.val;
    }
    
    public int getMin() {
        return next.min.val;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
