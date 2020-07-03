//给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。 
//
// 返回删除后的链表的头节点。 
//
// 注意：此题对比原题有改动 
//
// 示例 1: 
//
// 输入: head = [4,5,1,9], val = 5
//输出: [4,1,9]
//解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
// 
//
// 示例 2: 
//
// 输入: head = [4,5,1,9], val = 1
//输出: [4,5,9]
//解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
// 
//
// 
//
// 说明： 
//
// 
// 题目保证链表中节点的值互不相同 
// 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点 
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class ShanChuLianBiaoDeJieDianLcof {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ShanChuLianBiaoDeJieDianLcof().new ListNode(4);
        ListNode node2 = new ShanChuLianBiaoDeJieDianLcof().new ListNode(5);
        ListNode node3 = new ShanChuLianBiaoDeJieDianLcof().new ListNode(1);
        ListNode node4 = new ShanChuLianBiaoDeJieDianLcof().new ListNode(9);
        Solution solution = new ShanChuLianBiaoDeJieDianLcof().new Solution();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        solution.deleteNode(node1, 9);
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
        public ListNode deleteNode(ListNode head, int val) {
            if (head == null ) {
                return head;
            }
            if (head.val == val){
                return  head.next;
            }
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            while (dummy != null && dummy.next != null) {
                if (dummy.next.val == val) {
                    dummy.next = dummy.next.next;
                    return head;
                } else {
                    dummy = dummy.next;
                }
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}