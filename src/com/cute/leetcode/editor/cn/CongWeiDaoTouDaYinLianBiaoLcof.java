//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class CongWeiDaoTouDaYinLianBiaoLcof {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new CongWeiDaoTouDaYinLianBiaoLcof().new ListNode(1);
        ListNode node2 = new CongWeiDaoTouDaYinLianBiaoLcof().new ListNode(3);
        ListNode node3 = new CongWeiDaoTouDaYinLianBiaoLcof().new ListNode(2);
        node1.next = node2;
        node2.next = node3;
        Solution solution = new CongWeiDaoTouDaYinLianBiaoLcof().new Solution();
        solution.reversePrint(node1);
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
        public int[] reversePrint(ListNode head) {
            //corner case
            if (head == null){
                return new int[0];
            }
            //定义长度
            int len = 0;
            //反转链表
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null){
                ListNode temp = cur.next;
                len +=1;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            int[] arr = new int[len];
            for (int i = 0 ; i < len; i++){
                arr[i] = pre.val;
                pre = pre.next;
            }
            return arr;
        }

        //迭代反转链表 主题干已经排除边界情况


        //递归反转链表
        public ListNode reverseNodeR(ListNode head){
            if (head == null || head.next == null){
                return head;
            }
            ListNode p = reverseNodeR(head.next);
            head.next.next = head;
            head.next =null;
            return head;

        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}