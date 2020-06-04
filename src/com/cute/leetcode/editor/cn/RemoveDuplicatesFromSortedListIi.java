
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
        Solution solution = new RemoveDuplicatesFromSortedListIi().new Solution();
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
            ListNode res = new ListNode(0);
            ListNode temp = head;
            res.next = temp;
            if (null == head.next){
                return res.next;
            }
            ListNode last = head.next;
            while (head.next != null) {
                while (head.val == last.val) {
                    last = last.next;
                }
                head = last;

                last = last.next;
            }
            return res.next;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}