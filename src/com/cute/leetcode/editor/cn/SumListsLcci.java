//给定两个用链表表示的整数，每个节点包含一个数位。 
// 这些数位是反向存放的，也就是个位排在链表首部。 
// 编写函数对这两个整数求和，并用链表形式返回结果。 
//
// 
//
// 示例： 
//
// 
//输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
//输出：2 -> 1 -> 9，即912
// 
//
// 进阶：假设这些数位是正向存放的，请再做一遍。 
//
// 示例： 
//
// 
//输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
//输出：9 -> 1 -> 2，即912
// 
// Related Topics 链表 数学

package com.cute.leetcode.editor.cn;

public class SumListsLcci {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new SumListsLcci().new ListNode(5);
        ListNode node2 = new SumListsLcci().new ListNode(6);
        ListNode node3 = new SumListsLcci().new ListNode(4);
        ListNode node4 = new SumListsLcci().new ListNode(2);
        ListNode node5 = new SumListsLcci().new ListNode(4);
        ListNode node6 = new SumListsLcci().new ListNode(3);
        node1.next  = node2;
        node2.next = node3;
        node4.next = node5;
        node5.next = node6;
        Solution solution = new SumListsLcci().new Solution();
        solution.addTwoNumbers(node1,node4);
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //逆序链表
            //逆序链表
            ListNode dummy = new ListNode(-1);
            ListNode head = dummy;
            dummy.next = head;
            int flag = 0;
            while (l1 != null && l2!= null){
                if (l1.val + l2.val+ flag >= 10){
                    head.next =new ListNode ((l1.val + l2.val + flag) %10);
                    flag = 1;
                    head = head.next;

                }else{
                    head.next =new ListNode (l1.val + l2.val+ flag);
                    flag = 0;
                    head = head.next;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            while (l1!= null){
                if (l1.val +flag >=10){
                    head.next =new ListNode ((l1.val + flag) %10);
                    flag = 1;
                    head = head.next;
                }else{
                    head.next =new ListNode (l1.val + flag);
                    flag = 0;
                    head = head.next;
                }
                l1 = l1.next;

            }
            while (l2!= null){
                if (l2.val +flag >=10){
                    head.next =new ListNode ((l2.val + flag) %10);
                    flag = 1;
                    head = head.next;
                }else{
                    head.next =new ListNode (l2.val + flag);
                    flag = 0;
                    head = head.next;
                }
                l2 = l2.next;
            }
            if (flag != 0){
                head.next = new ListNode(1);
            }
            return dummy.next;

            //正序链表





        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}