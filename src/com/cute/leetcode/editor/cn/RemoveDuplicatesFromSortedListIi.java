
package com.cute.leetcode.editor.cn;

import java.util.LinkedHashSet;//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
//
// 示例 1: 
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
// 
//
// 示例 2: 
//
// 输入: 1->1->1->2->3
//输出: 2->3 
// Related Topics 链表


import java.util.HashSet;

public class RemoveDuplicatesFromSortedListIi {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListIi.ListNode node1 = new RemoveDuplicatesFromSortedListIi().new ListNode(1);
        RemoveDuplicatesFromSortedListIi.ListNode node2 = new RemoveDuplicatesFromSortedListIi().new ListNode(1);
//        RemoveDuplicatesFromSortedListIi.ListNode node3 = new RemoveDuplicatesFromSortedListIi().new ListNode(3);
//        RemoveDuplicatesFromSortedListIi.ListNode node4 = new RemoveDuplicatesFromSortedListIi().new ListNode(3);
//        RemoveDuplicatesFromSortedListIi.ListNode node5 = new RemoveDuplicatesFromSortedListIi().new ListNode(4);
//        RemoveDuplicatesFromSortedListIi.ListNode node6 = new RemoveDuplicatesFromSortedListIi().new ListNode(4);
//        RemoveDuplicatesFromSortedListIi.ListNode node7 = new RemoveDuplicatesFromSortedListIi().new ListNode(5);

        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
        Solution solution = new RemoveDuplicatesFromSortedListIi().new Solution();
        solution.deleteDuplicates(node1);
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
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(-1);
            if (head == null || null == head.next) {
                return head;
            }
            dummy.next = head;
            ListNode first = dummy;
            ListNode last = first.next;
            while (last != null && last.next != null) {
                if (first.next.val != last.next.val) {
                    last = last.next;
                    first = first.next;
                } else {
                    while (last.next != null && first.next.val == last.next.val){
                        last = last.next;
                    }
                    first.next = last.next;
                    last = last.next;
                }
            }
            return dummy.next;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}