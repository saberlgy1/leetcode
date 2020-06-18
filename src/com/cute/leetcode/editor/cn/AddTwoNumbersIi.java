//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 进阶： 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class AddTwoNumbersIi {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        AddTwoNumbersIi.ListNode node1 = new AddTwoNumbersIi().new ListNode(1);
        AddTwoNumbersIi.ListNode node2 = new AddTwoNumbersIi().new ListNode(9);
        AddTwoNumbersIi.ListNode node3 = new AddTwoNumbersIi().new ListNode(9);
        AddTwoNumbersIi.ListNode node4 = new AddTwoNumbersIi().new ListNode(3);
        AddTwoNumbersIi.ListNode node5 = new AddTwoNumbersIi().new ListNode(5);
        AddTwoNumbersIi.ListNode node6 = new AddTwoNumbersIi().new ListNode(6);
        AddTwoNumbersIi.ListNode node7 = new AddTwoNumbersIi().new ListNode(4);
//        RemoveDuplicatesFromSortedList.ListNode node6 = new RemoveDuplicatesFromSortedList().new ListNode(4);
//        RemoveDuplicatesFromSortedList.ListNode node7 = new RemoveDuplicatesFromSortedList().new ListNode(5);

        //node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node5.next = node6;
//        node6.next = node7;
        Solution solution = new AddTwoNumbersIi().new Solution();
        solution.addTwoNumbers(node1, node2);
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
            //corner case
            //题目非空所以不用判断
            //反转两个链表 保证链表最低位对齐
            ListNode lr1 = reverse(l1);
            ListNode lr2 = reverse(l2);
            //链表相加
            //设定标志位，判断是否进位
            int flag = 0;
            ListNode res = new ListNode(-1);
            ListNode dummy = res;
            while (lr1 != null && lr2 != null) {
                int number = lr1.val + lr2.val + flag;
                if (number >= 10) {
                    flag = 1;
                } else {
                    flag = 0;
                }
                res.next = new ListNode(number % 10);
                res = res.next;
                lr1 = lr1.next;
                lr2 = lr2.next;
            }
            while (lr1 != null){
                int number = lr1.val + flag;
                if (number >= 10) {
                    flag = 1;
                } else {
                    flag = 0;
                }
                res.next = new ListNode(number % 10);
                res = res.next;
                lr1 = lr1.next;
            }
            while (lr2 != null){
                int number = lr2.val + flag;
                if (number >= 10) {
                    flag = 1;
                } else {
                    flag = 0;
                }
                res.next = new ListNode(number % 10);
                res = res.next;
                lr2 = lr2.next;
            }
            //判断最终进位
            if (flag != 0){
                res.next = new ListNode(flag);
            }
            //反转链表
            return reverse(dummy.next);
        }


        public ListNode reverse(ListNode head){
            if (head == null || head.next == null){
                return  head;
            }
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null){
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            return pre;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}