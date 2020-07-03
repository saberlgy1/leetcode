//输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，
//它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。 
//
// 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 k = 2.
//
//返回链表 4->5. 
// Related Topics 链表 双指针

package com.cute.leetcode.editor.cn;

public class LianBiaoZhongDaoShuDiKgeJieDianLcof {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new LianBiaoZhongDaoShuDiKgeJieDianLcof().new ListNode(1);
        ListNode node2 = new LianBiaoZhongDaoShuDiKgeJieDianLcof().new ListNode(2);
        ListNode node3 = new LianBiaoZhongDaoShuDiKgeJieDianLcof().new ListNode(3);
        ListNode node4 = new LianBiaoZhongDaoShuDiKgeJieDianLcof().new ListNode(4);
        ListNode node5 = new LianBiaoZhongDaoShuDiKgeJieDianLcof().new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Solution solution = new LianBiaoZhongDaoShuDiKgeJieDianLcof().new Solution();
        solution.getKthFromEnd(node1,2);
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
        public ListNode getKthFromEnd(ListNode head, int k) {
            ListNode fast = head;
            ListNode slow = head;
            while (k != 0 && fast != null){
                k--;
                fast =fast.next;
            }
            //corner case
            if ( k != 0){
                return null;
            }
            while (fast != null){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}