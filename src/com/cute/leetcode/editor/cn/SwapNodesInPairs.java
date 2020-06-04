//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 
//
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class SwapNodesInPairs {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Solution solution = new SwapNodesInPairs().new Solution();
        ListNode node1 = new SwapNodesInPairs().new ListNode(1);
        ListNode node2 = new SwapNodesInPairs().new ListNode(2);
        ListNode node3 = new SwapNodesInPairs().new ListNode(3);
        ListNode node4 = new SwapNodesInPairs().new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        solution.swapPairs(node1);
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
//    public ListNode swapPairs(ListNode head) {
//           if (head == null || head.next == null){
//               return  head;
//           }
//        ListNode firstNode = head;
//        ListNode secondNode = head.next;
//        firstNode.next = swapPairs(secondNode.next);
//        secondNode.next = firstNode;
//        return secondNode;
//    }

        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode temp = dummy;
            while (head != null && head.next != null) {
               ListNode first = head;
               ListNode second = head.next;
               temp.next = second;
               first.next =  second.next;
               second.next = first;
               temp = first;
               head = first.next;
            }

            return dummy.next;


        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}