//编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。
//分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。 
//
// 示例: 
//
// 输入: head = 3->5->8->5->10->2->1, x = 5
//输出: 3->1->2->10->5->5->8
// 
// Related Topics 链表 双指针 
// 👍 21 👎 0

package com.cute.leetcode.editor.cn;

public class PartitionListLcci {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Solution solution = new PartitionListLcci().new Solution();
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
        public ListNode partition(ListNode head, int x) {
            if (head == null) {
                return head;
            }
            ListNode l = new ListNode(-1);
            ListNode r = new ListNode(-1);
            ListNode templ = l;
            ListNode tempr = r;
            while (head != null) {
                if (head.val < x) {
                    templ.next = head;
                    templ = templ.next;
                } else {
                    tempr.next = head;
                    tempr = tempr.next;
                }
                head = head.next;
            }
            tempr.next = null;
            templ.next = r.next;
            return l.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}