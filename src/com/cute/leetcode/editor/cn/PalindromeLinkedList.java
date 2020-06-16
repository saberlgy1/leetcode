//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针

package com.cute.leetcode.editor.cn;

import java.util.Stack;

public class PalindromeLinkedList {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        PalindromeLinkedList.ListNode node1 = new PalindromeLinkedList().new ListNode(1);
        PalindromeLinkedList.ListNode node2 = new PalindromeLinkedList().new ListNode(2);
        PalindromeLinkedList.ListNode node3 = new PalindromeLinkedList().new ListNode(3);
        PalindromeLinkedList.ListNode node4 = new PalindromeLinkedList().new ListNode(3);
        PalindromeLinkedList.ListNode node5 = new PalindromeLinkedList().new ListNode(2);
        PalindromeLinkedList.ListNode node6 = new PalindromeLinkedList().new ListNode(1);
        //PalindromeLinkedList.ListNode node7 = new PalindromeLinkedList().new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        //node6.next = node7;
        Solution solution = new PalindromeLinkedList().new Solution();
        System.out.println(solution.isPalindrome(node1));
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
        public boolean isPalindrome(ListNode head) {
            if (head == null) {
                return true;
            }
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode right = reverse(slow);
            while (right != null) {
                if (head.val != right.val) {
                    return false;
                }else {
                    head = head.next;
                    right = right.next;
                }
            }
            return true;
        }


        public ListNode reverse(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
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