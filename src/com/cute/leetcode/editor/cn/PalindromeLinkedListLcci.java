//编写一个函数，检查输入的链表是否是回文的。 
//
// 
//
// 示例 1： 
//
// 输入： 1->2
//输出： false 
// 
//
// 示例 2： 
//
// 输入： 1->2->2->1
//输出： true 
// 
//
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

import java.util.Stack;

public class PalindromeLinkedListLcci {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new PalindromeLinkedListLcci().new ListNode(1);
        ListNode node2 = new PalindromeLinkedListLcci().new ListNode(0);
        ListNode node3 = new PalindromeLinkedListLcci().new ListNode(3);
        ListNode node4 = new PalindromeLinkedListLcci().new ListNode(4);
        ListNode node5 = new PalindromeLinkedListLcci().new ListNode(0);
        ListNode node6 = new PalindromeLinkedListLcci().new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        Solution solution = new PalindromeLinkedListLcci().new Solution();
        solution.isPalindrome(node1);
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
        public boolean isPalindrome(ListNode head) {

            //corner case
            if (head == null || head.next == null) {
                return true;
            }
            ListNode slow = head;
            ListNode fast = head;
            //栈
            //栈
//            Stack<Integer> stack = new Stack<>();
//            while (fast != null && fast.next != null) {
//                fast = fast.next.next;
//                stack.push(slow.val);
//                if (fast != null && fast.next == null) {
//                    slow = slow.next.next;
//                } else {
//                    slow = slow.next;
//                }
//
//            }
//            while (!stack.isEmpty() && slow != null) {
//                if (stack.peek() != slow.val) {
//                    return false;
//                } else {
//                    slow = slow.next;
//                    stack.pop();
//                }
//            }
//            return stack.isEmpty();
            //反转链表法
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            //反转链表
            ListNode temp = reverse(slow);
            while (temp != null) {
                if (head.val == temp.val) {
                    head = head.next;
                    temp = temp.next;
                }else{
                    return false;
                }
            }
            return true;
        }
        //reverse函数
        public  ListNode reverse(ListNode head) {
            if (head == null || head.next == null){
                return head;
            }
            ListNode dummy = null;
            ListNode pre = dummy;
            ListNode cur = head;
            while (cur!= null){
                ListNode temp = cur.next;
                cur.next = pre;
                pre =cur;
                cur = temp;
            }
            return pre;
        }

    }




//leetcode submit region end(Prohibit modification and deletion)

}