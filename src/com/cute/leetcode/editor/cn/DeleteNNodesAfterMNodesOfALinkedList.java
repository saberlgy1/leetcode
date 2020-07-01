//给定链表 head 和两个整数 m 和 n. 遍历该链表并按照如下方式删除节点: 
//
// 
// 开始时以头节点作为当前节点. 
// 保留以当前节点开始的前 m 个节点. 
// 删除接下来的 n 个节点. 
// 重复步骤 2 和 3, 直到到达链表结尾. 
// 
//
// 在删除了指定结点之后, 返回修改过后的链表的头节点. 
//
// 进阶问题: 你能通过就地修改链表的方式解决这个问题吗? 
//
// 
//
// 示例 1: 
//
// 
//
// 输入: head = [1,2,3,4,5,6,7,8,9,10,11,12,13], m = 2, n = 3
//输出: [1,2,6,7,11,12]
//解析: 保留前(m = 2)个结点,  也就是以黑色节点表示的从链表头结点开始的结点(1 ->2).
//删除接下来的(n = 3)个结点(3 -> 4 -> 5), 在图中以红色结点表示.
//继续相同的操作, 直到链表的末尾.
//返回删除结点之后的链表的头结点. 
//
// 示例 2: 
//
// 
//
// 输入: head = [1,2,3,4,5,6,7,8,9,10,11], m = 1, n = 3
//输出: [1,5,9]
//解析: 返回删除结点之后的链表的头结点. 
//
// 示例 3: 
//
// 输入: head = [1,2,3,4,5,6,7,8,9,10,11], m = 3, n = 1
//输出: [1,2,3,5,6,7,9,10,11]
// 
//
// 示例 4: 
//
// 输入: head = [9,3,7,7,9,10,8,2], m = 1, n = 2
//输出: [9,7,8]
// 
//
// 
//
// 提示: 
//
// 
// 1 <= 链表结点数 <= 10^4. 
// [1 <= 链表的每一个结点值 <=10^6]. 
// 1 <= m,n <= 1000 
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class DeleteNNodesAfterMNodesOfALinkedList {

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
        ListNode node1 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(1);
        ListNode node2 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(2);
        ListNode node3 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(3);
        ListNode node4 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(4);
        ListNode node5 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(5);
        ListNode node6 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(6);
        ListNode node7 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(7);
        ListNode node8 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(8);
        ListNode node9 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(9);
        ListNode node10 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(10);
        ListNode node11 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(11);
        ListNode node12 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(12);
        ListNode node13 = new DeleteNNodesAfterMNodesOfALinkedList().new ListNode(13);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;
        node11.next = node12;
        node12.next = node13;
        Solution solution = new DeleteNNodesAfterMNodesOfALinkedList().new Solution();
        solution.deleteNodes(node1,2 ,3);
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
        public ListNode deleteNodes(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0, head);
            if (head == null) {
                return head;
            }
            for (int l = m - 1; l > 0 && head != null; l--) {
                head = head.next;
            }
            if (head == null) {
                return dummy.next;
            }
            for (int l = n; l > 0 && head.next != null; l--) {
                head.next = head.next.next;
            }
            if (head.next == null) {
                return dummy.next;
            } else {
                head = head.next;
                deleteNodes(head, m, n);
            }
            return dummy.next;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}