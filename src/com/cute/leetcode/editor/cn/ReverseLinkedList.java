//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class ReverseLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ReverseLinkedList().new ListNode(1);
        ListNode node2 = new ReverseLinkedList().new ListNode(2);
        ListNode node3 = new ReverseLinkedList().new ListNode(3);
        ListNode node4 = new ReverseLinkedList().new ListNode(4);
        ListNode node5 = new ReverseLinkedList().new ListNode(5);
        Solution solution = new ReverseLinkedList().new Solution();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        solution.reverseList(node1);

    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     */
    class Solution {
        public ListNode reverseList(ListNode head) {
            //递归解法
            if (head == null || head.next == null){
                return head;
            }
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return  p;




//            //迭代解法
//            ListNode pre = null;
//            ListNode cur = head;
//            while (cur != null) {
//                ListNode temp = cur.next;
//                cur.next = pre;
//                pre = cur;
//                cur = temp;
//            }
//            return pre;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}