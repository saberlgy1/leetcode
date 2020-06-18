//用一个 非空 单链表来表示一个非负整数，然后将这个整数加一。 
//
// 你可以假设这个整数除了 0 本身，没有任何前导的 0。 
//
// 这个整数的各个数位按照 高位在链表头部、低位在链表尾部 的顺序排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class PlusOneLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new PlusOneLinkedList().new ListNode(1);
        ListNode node2 = new PlusOneLinkedList().new ListNode(2);
        ListNode node3 = new PlusOneLinkedList().new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        Solution solution = new PlusOneLinkedList().new Solution();
        solution.plusOne(node1);
        System.out.println("111");
        System.out.println(Integer.MAX_VALUE);
        System.out.println("9876543210");
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
        public ListNode plusOne(ListNode head) {
            if (null == head) {
                return head;
            }
            //corner
            //整型 会超出限额 所以不能取出来单加 而是应该通过指针找到找到最靠右的不是9的一个数字 然后+ 1
            //设置头指针
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode slow = dummy;
            while (head != null) {
                if (head.val != 9) {
                    slow = head;
                }
                head = head.next;
            }
            ListNode fast = slow.next;
            while (fast!=null){
                fast.val = 0;
                fast = fast.next;
            }
            slow.val +=1;
            return dummy.val == 0? dummy.next:dummy;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}