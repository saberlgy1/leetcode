//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;

public class ReorderList {

    public class ListNode {
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
        ListNode node1 = new ReorderList().new ListNode(1);
        ListNode node2 = new ReorderList().new ListNode(2);
        ListNode node3 = new ReorderList().new ListNode(3);
        ListNode node4 = new ReorderList().new ListNode(4);
        ListNode node5 = new ReorderList().new ListNode(5);
        ListNode node6 = new ReorderList().new ListNode(6);
        ListNode node7 = new ReorderList().new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Solution solution = new ReorderList().new Solution();
        solution.reorderList(node1);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        //        public void reorderList(ListNode head) {
//            if (head == null) {
//                return;
//            }
//            ArrayList<ListNode> list = new ArrayList<>();
//            while ( head != null){
//                list.add(head);
//                head= head.next;
//            }
//            int left = 0, right = list.size() - 1;
//
//            while (left < right){
//                list.get(left).next = list.get(right);
//                left ++;
//
//                if (left == right){
//                    break;
//                }
//                list.get(right).next = list.get(left);
//                right--;
//            }
//            //尾指针置空
//            list.get(left).next = null;
//
//
//        }
//        public void reorderList(ListNode head){
//            if (head == null || head.next == null || head.next.next == null) {
//                return;
//            }
//
//            int len = 0;
//            ListNode h = head;
//            while (h != null){
//                len ++;
//                h = h.next;
//            }
//            reorderListHelper(head,len);
//        }
//
//        public ListNode reorderListHelper(ListNode head, int len){
//            if (len == 1){
//                ListNode temp =  head.next;
//                head.next = null;
//                return  temp;
//            }
//            if (len == 2){
//                ListNode temp =  head.next.next;
//                head.next.next = null;
//                return  temp;
//            }
//            ListNode tail = reorderListHelper(head.next, len - 2);
//            ListNode subHead = head.next;//中间链表的头结点
//            head.next = tail;
//            ListNode outTail = tail.next;  //上一层 head 对应的 tail
//            tail.next = subHead;
//            return outTail;
//
//        }

        public void reorderList(ListNode head) {
            if (head == null || head.next == null || head.next.next == null) {
                return;
            }
            //找中点，链表分成两个
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            ListNode newHead = slow.next;
            slow.next = null;

            //第二个链表倒置
            newHead = reverseList(newHead);

            //链表节点依次连接
            while (newHead != null) {
                ListNode temp = newHead.next;
                newHead.next = head.next;
                head.next = newHead;
                head = newHead.next;
                newHead = temp;
            }

        }

        private ListNode reverseList(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode tail = head.next;
            head.next = null;
            while (tail != null) {
                ListNode temp = tail.next;
                tail.next = head;
                head = tail;
                tail = temp;
            }

            return head;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}