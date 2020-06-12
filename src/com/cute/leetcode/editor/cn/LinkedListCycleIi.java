//给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。 
//
// 说明：不允许修改给定的链表。 
//
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：tail connects to node index 1
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
//输出：tail connects to node index 0
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
//输出：no cycle
//解释：链表中没有环。
// 
//
// 
//
// 
//
// 进阶： 
//你是否可以不用额外空间解决此题？ 
// Related Topics 链表 双指针

package com.cute.leetcode.editor.cn;

import java.util.List;

public class LinkedListCycleIi {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new LinkedListCycleIi().new ListNode(3);
        ListNode node2 = new LinkedListCycleIi().new ListNode(2);
        ListNode node3 = new LinkedListCycleIi().new ListNode(0);
        ListNode node4 = new LinkedListCycleIi().new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        Solution solution = new LinkedListCycleIi().new Solution();
        solution.detectCycle(node1);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            //快慢指针
            ListNode fast = head;
            ListNode slow = head;
            if (head == null) {
                return head;
            }
//            while (fast != slow) {
//                if (fast == null || fast.next == null) {
//                    return null;
//                }
//                fast = fast.next.next;
//                slow = slow.next;
//
//            }
            do{
                if(fast == null || fast.next == null){
                    return null;
                }
                fast = fast.next.next;
                slow = slow.next;
            }while(fast != slow);
//            while (head.next != temp.next) {
//                head = head.next;
//                temp = temp.next;
//            }
//            ListNode ptr1 = head;
//            ListNode ptr2 = temp;
//            while (fast != head) {
//                fast = fast.next;
//                head = head.next;
//            }
            fast = head;
            while(fast != slow){
                fast = fast.next;
                slow = slow.next;
            }
            return fast;

           // return fast;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}