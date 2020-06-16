//对链表进行插入排序。 
//
// 
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。 
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。 
//
// 
//
// 插入排序算法： 
//
// 
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。 
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。 
// 重复直到所有输入数据插入完为止。 
// 
//
// 
//
// 示例 1： 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2： 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
// 
// Related Topics 排序 链表

package com.cute.leetcode.editor.cn;

public class InsertionSortList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
//        ListNode node1 = new InsertionSortList().new ListNode(4);
//        ListNode node2 = new InsertionSortList().new ListNode(2);
//        ListNode node3 = new InsertionSortList().new ListNode(1);
//        ListNode node4 = new InsertionSortList().new ListNode(1);
//        Solution solution = new InsertionSortList().new Solution();
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        solution.insertionSortList(node1);

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
        public ListNode insertionSortList(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode dummy = new ListNode(Integer.MIN_VALUE);
            dummy.next = head;
            ListNode pre = dummy;
            ListNode cur = pre.next;
            while (cur != null) {
                if (cur.val < pre.val){
                    ListNode temp = dummy;
                    while (temp.next.val < cur.val){
                        temp = temp.next;
                    }
                    pre.next =cur.next;
                    cur.next = temp.next;
                    temp.next = cur;
                    cur = pre.next;

                }else{
                    pre = pre.next;
                    cur = cur.next;
                }

            }
            return  dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}