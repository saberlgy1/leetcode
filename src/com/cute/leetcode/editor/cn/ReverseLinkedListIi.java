//反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。 
//
// 说明: 
//1 ≤ m ≤ n ≤ 链表长度。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL, m = 2, n = 4
//输出: 1->4->3->2->5->NULL 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class ReverseLinkedListIi {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        ReverseLinkedListIi.ListNode node1 = new ReverseLinkedListIi().new ListNode(3);
        ReverseLinkedListIi.ListNode node2 = new ReverseLinkedListIi().new ListNode(5);
        ReverseLinkedListIi.ListNode node3 = new ReverseLinkedListIi().new ListNode(3);
        ReverseLinkedListIi.ListNode node4 = new ReverseLinkedListIi().new ListNode(4);
        ReverseLinkedListIi.ListNode node5 = new ReverseLinkedListIi().new ListNode(5);
        Solution solution = new ReverseLinkedListIi().new Solution();
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        solution.reverseBetween(node1, 2, 4);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode reverseBetween(ListNode head, int m, int n) {
            //            if (head == null || head.next == null) {
//                return head;
//            }
//            ListNode pre = null;
//            ListNode cur = head;
//            while (m > 1) {
//                pre = cur;
//                cur = cur.next;
//                m--;
//                n--;
//            }
//            ListNode start = pre;
//            ListNode tail = cur;
//
//         while ( n > 0){
//             ListNode temp = cur.next;
//             cur.next = pre;
//             pre = cur;
//             cur = temp;
//             n--;
//         }
//
//            if (start != null) {
//                start.next = pre;
//            } else {
//                head = pre;
//            }
//
//            tail.next = cur;
//            return  head;
//        }


            if (m == 1) {
                return reverseN(head, n);
            }
            head.next = reverseBetween(head.next, m - 1, n - 1);
            return head;
        }

        ListNode tail = null;

        public ListNode reverseN(ListNode head, int n) {

            if (n == 1) {
                tail = head.next;
                return head;
            }
            ListNode last = reverseN(head.next, n - 1);
            head.next.next = head;
            head.next = tail;
            return last;


        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}