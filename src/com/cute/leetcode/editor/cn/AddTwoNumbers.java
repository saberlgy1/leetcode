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

package com.cute.leetcode.editor.cn;

public class AddTwoNumbers {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new AddTwoNumbers().new ListNode(1);
        ListNode node2 = new AddTwoNumbers().new ListNode(5);
        ListNode node3 = new AddTwoNumbers().new ListNode(3);
        ListNode node4 = new AddTwoNumbers().new ListNode(9);
        ListNode node5 = new AddTwoNumbers().new ListNode(9);
        ListNode node6 = new AddTwoNumbers().new ListNode(4);
//        node1.next = node2;
//        node2.next = node3;
        node4.next = node5;
//        node5.next = node6;
        Solution solution = new AddTwoNumbers().new Solution();
        System.out.println(solution.addTwoNumbers(node1, node4));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode temp = new ListNode(0);
            ListNode res = temp;
            int flag = 0;
            while (l1 != null && l2 != null) {

                if (l1.val + l2.val + flag >= 10) {
                    temp.val = (l1.val + l2.val + flag) % 10;
                    flag = 1;
                    temp.next = new ListNode(0);
                    temp = temp.next;
                    l1 = l1.next;
                    l2 = l2.next;
                } else {
                    temp.val = l1.val + l2.val + flag;
                    flag = 0;
                    l1 = l1.next;
                    l2 = l2.next;
                    if (l1 != null || l2 != null){
                        temp.next = new ListNode(0);
                        temp = temp.next;
                    }
                }

            }
            while (l1 != null){
                if (l1.val + flag >= 10) {
                    temp.val = (l1.val + flag) % 10;
                    flag = 1;
                    temp.next = new ListNode(0);
                    temp = temp.next;
                    l1 = l1.next;
                } else {
                    temp.val = l1.val + flag;
                    flag = 0;
                    l1 = l1.next;
                    if (l1 != null){
                        temp.next = new ListNode(0);
                        temp = temp.next;
                    }
                }
            }
            while (l2 != null){
                if (l2.val + flag >= 10) {
                    temp.val = (l2.val + flag) % 10;
                    flag = 1;
                    l2 = l2.next;
                    temp.next = new ListNode(0);
                    temp = temp.next;

                } else {
                    temp.val = l2.val + flag;
                    flag = 0;
                    l2 = l2.next;
                    if (l2 != null){
                        temp.next = new ListNode(0);
                        temp = temp.next;
                    }
                }
            }
            if (flag  == 1) {
                temp.val = flag;
            }
            return res;



        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}