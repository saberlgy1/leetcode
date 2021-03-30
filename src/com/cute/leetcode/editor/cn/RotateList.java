//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
// 
//
// 示例 2: 
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL 
// Related Topics 链表 双指针

package com.cute.leetcode.editor.cn;

import java.util.List;

public class RotateList {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Solution solution = new RotateList().new Solution();
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
        public ListNode rotateRight(ListNode head, int k) {
            /*if (head == null) {
                return null;
            }
            ListNode first = head;
            ListNode temp = first;
            int n = 1;
            while (temp.next != null) {
                temp = temp.next;
                n++;
            }
            int p = k % n;
            temp.next = first;
            for (int i = 0; i < n - p; i++) {
                first = first.next;
                temp = temp.next;

            }
            temp.next = null;
            return first;*/
            //每日一题重新做
            if (head == null){
                return  null;
            }
            int n = 1;
            ListNode temp = head;
            ListNode first = head;
            while (temp.next != null){
                temp = temp.next;
                n++;
            }
            //最终移动多少 ，移动节点数n次= 恢复鸳鸯
            int movation = k % n;
            //尾节点连接到头节点
            temp.next = first;
            for (int i = 0; i < n -  movation; i++) {
                first = first.next;
                temp = temp.next;
            }
            temp.next = null;
            return first;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}