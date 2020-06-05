//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class RemoveDuplicatesFromSortedList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        RemoveDuplicatesFromSortedList.ListNode node1 = new RemoveDuplicatesFromSortedList().new ListNode(1);
        RemoveDuplicatesFromSortedList.ListNode node2 = new RemoveDuplicatesFromSortedList().new ListNode(1);
        RemoveDuplicatesFromSortedList.ListNode node3 = new RemoveDuplicatesFromSortedList().new ListNode(2);
        RemoveDuplicatesFromSortedList.ListNode node4 = new RemoveDuplicatesFromSortedList().new ListNode(3);
        RemoveDuplicatesFromSortedList.ListNode node5 = new RemoveDuplicatesFromSortedList().new ListNode(3);
//        RemoveDuplicatesFromSortedList.ListNode node6 = new RemoveDuplicatesFromSortedList().new ListNode(4);
//        RemoveDuplicatesFromSortedList.ListNode node7 = new RemoveDuplicatesFromSortedList().new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
        solution.deleteDuplicates(node1);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public
     */
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
//            ListNode dummy = new ListNode(-1);
//            ListNode temp = dummy;
//            HashMap<Integer, Integer> map = new LinkedHashMap<>();
//            while (null != head) {
//                if (!map.containsKey(head.val)) {
//                    map.put(head.val,0);
//                    head = head.next;
//                }else {
//                    head = head.next;
//                }
//            }
//            for (Integer i: map.keySet()){
//                temp.next = new ListNode(i);
//                temp = temp.next;
//            }
//            return dummy.next;
//            ListNode dummy = new ListNode(-1);
//            dummy.next = head;
//            if (head == null || head.next == null) {
//                return head;
//            }
//            ListNode temp = head.next;
//            while (temp != null) {
//                while (temp != null && head.val == temp.val) {
//                    temp = temp.next;
//                }
//                head.next = temp;
//               // temp = temp.next;
//                head = head.next;
//            }
//            return dummy.next;
            ListNode res = head;
            while (res != null && res.next != null) {
                if (res.val == res.next.val){
                    res.next = res.next.next;
                }else {
                    res = res.next;
                }
            }
            return  head;



        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}