//给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。 
//
// 删除完毕后，请你返回最终结果链表的头节点。 
//
// 
//
// 你可以返回任何满足题目要求的答案。 
//
// （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。） 
//
// 示例 1： 
//
// 输入：head = [1,2,-3,3,1]
//输出：[3,1]
//提示：答案 [1,2,1] 也是正确的。
// 
//
// 示例 2： 
//
// 输入：head = [1,2,3,-3,4]
//输出：[1,2,4]
// 
//
// 示例 3： 
//
// 输入：head = [1,2,3,-3,-2]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 给你的链表中可能有 1 到 1000 个节点。 
// 对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000. 
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

import java.util.HashMap;

public class RemoveZeroSumConsecutiveNodesFromLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new RemoveZeroSumConsecutiveNodesFromLinkedList().new ListNode(0);
        ListNode node2 = new RemoveZeroSumConsecutiveNodesFromLinkedList().new ListNode(2);
        ListNode node3 = new RemoveZeroSumConsecutiveNodesFromLinkedList().new ListNode(-3);
        ListNode node4 = new RemoveZeroSumConsecutiveNodesFromLinkedList().new ListNode(3);
        ListNode node5 = new RemoveZeroSumConsecutiveNodesFromLinkedList().new ListNode(1);
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
        Solution solution = new RemoveZeroSumConsecutiveNodesFromLinkedList().new Solution();
        solution.removeZeroSumSublists(node1);
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
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            HashMap<Integer, ListNode> map = new HashMap<>();
            int sum = 0;
            ListNode temp = dummy;
            while (temp != null) {
                sum += temp.val;
                map.put(sum, temp);
                temp = temp.next;
            }
            if (sum == 0) {
                return null;
            }
            ListNode res = dummy;
            int result = 0;
            while (res != null) {
                result += res.val;
                res.next = map.get(result).next;
                res = res.next;

            }
            return dummy.next;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}