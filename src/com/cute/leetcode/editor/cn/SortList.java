//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。 
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表

package com.cute.leetcode.editor.cn;

import sun.tools.jstat.Literal;

public class SortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Solution solution = new SortList().new Solution();
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
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            int len = listNodeLength(head);
            // 哨兵节点，也有叫傀儡节点（处理链表问题的一般技巧）
            ListNode sentry = new ListNode(-1);
            sentry.next = head;

            // 循环 log n 次
            for (int i = 1; i < len; i <<= 1) {
                ListNode prev = sentry;
                ListNode curr = sentry.next;
                // 循环 n 次
                while (curr != null) {
                    ListNode left = curr;
                    ListNode right = split(left, i);
                    curr = split(right, i);
                    prev.next = mergeTwoLists(left, right);

                    while (prev.next != null) {
                        prev = prev.next;
                    }
                }
            }
            return sentry.next;

        }

        public ListNode split(ListNode head, int step) {

            if (head == null) return null;

            for (int i = 1; head.next != null && i < step; i++) {
                head = head.next;
            }

            ListNode right = head.next;
            head.next = null;
            return right;
        }

        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode sentry = new ListNode(-1);
            ListNode curr = sentry;

            while (l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                } else {
                    curr.next = l2;
                    l2 = l2.next;
                }

                curr = curr.next;
            }

            curr.next = l1 != null ? l1 : l2;
            return sentry.next;
        }
        // 获取链表的长度
        private int listNodeLength(ListNode head) {
            int length = 0;
            ListNode curr = head;

            while (curr != null) {
                length++;
                curr = curr.next;
            }

            return length;
        }

    }




//leetcode submit region end(Prohibit modification and deletion)

}