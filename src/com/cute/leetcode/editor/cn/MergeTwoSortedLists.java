//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class MergeTwoSortedLists {

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
        MergeTwoSortedLists.ListNode node1 = new MergeTwoSortedLists().new ListNode(1);
        MergeTwoSortedLists.ListNode node2 = new MergeTwoSortedLists().new ListNode(2);
        MergeTwoSortedLists.ListNode node3 = new MergeTwoSortedLists().new ListNode(4);
        MergeTwoSortedLists.ListNode node4 = new MergeTwoSortedLists().new ListNode(1);
        MergeTwoSortedLists.ListNode node5 = new MergeTwoSortedLists().new ListNode(3);
        MergeTwoSortedLists.ListNode node6 = new MergeTwoSortedLists().new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node4.next = node5;
        node5.next = node6;
        Solution solution = new MergeTwoSortedLists().new Solution();
        solution.mergeTwoLists(node1, node4);
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
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

//            ListNode temp = new ListNode(0);
//            ListNode res = temp;
//            while (l1 != null && l2 != null) {
//                if (l1.val <= l2.val) {
//                    temp.next = l1;
//                    temp = temp.next;
//                    l1 = l1.next;
//                } else {
//                    temp.next = l2;
//                    temp = temp.next;
//                    l2 = l2.next;
//                }
//            }
//            if (null != l1) {
//                temp.next = l1;
//            }
//            if (null != l2) {
//                temp.next = l2;
//            }
//            return res.next;
            ListNode res = null;
            if (l1 == null || l2 == null){
                res = l1 == null? l2:l1;
                l1 = null;
                l2 = null;
            }else {
                if (l1.val <= l2.val){
                    res = l1;
                    l1 = l1.next;
                }else{
                    res = l2;
                    l2 = l2.next;
                }
            }
            if (l1!= null || l2 != null){
                res.next = mergeTwoLists(l1,l2);
            }
            return res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}