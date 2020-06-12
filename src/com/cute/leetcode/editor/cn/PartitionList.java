//给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。 
//
// 你应当保留两个分区中每个节点的初始相对位置。 
//
// 示例: 
//
// 输入: head = 1->4->3->2->5->2, x = 3
//输出: 1->2->2->4->3->5
// 
// Related Topics 链表 双指针

package com.cute.leetcode.editor.cn;

public class PartitionList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

        PartitionList.ListNode node1 = new PartitionList().new ListNode(2);
        PartitionList.ListNode node2 = new PartitionList().new ListNode(1);
        PartitionList.ListNode node3 = new PartitionList().new ListNode(3);
        PartitionList.ListNode node4 = new PartitionList().new ListNode(2);
        PartitionList.ListNode node5 = new PartitionList().new ListNode(5);
        PartitionList.ListNode node6 = new PartitionList().new ListNode(2);

        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;

        Solution solution = new PartitionList().new Solution();
        solution.partition(node1, 1);
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
        public ListNode partition(ListNode head, int x) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode t1 = new ListNode(-1);

            ListNode t2 = new ListNode(-1);
            ListNode dummy1 = t1;
            ListNode dummy2 = t2;
            while (head != null) {
                if (head.val < x) {
                    //  t1.next = new ListNode(head.val);
                    t1.next = head;
                    t1 = t1.next;
                } else {
                     t2.next = new ListNode(head.val);
                   // t2.next = head;
                    t2 = t2.next;
                }
                head = head.next;
            }
            t2.next = null;
            t1.next = dummy2.next;
            return dummy1.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}