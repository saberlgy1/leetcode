//给定链表头结点 head，该链表上的每个结点都有一个 唯一的整型值 。 
//
// 同时给定列表 G，该列表是上述链表中整型值的一个子集。 
//
// 返回列表 G 中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表 G 中）构成的集合。 
//
// 
//
// 示例 1： 
//
// 输入: 
//head: 0->1->2->3
//G = [0, 1, 3]
//输出: 2
//解释: 
//链表中,0 和 1 是相连接的，且 G 中不包含 2，所以 [0, 1] 是 G 的一个组件，同理 [3] 也是一个组件，故返回 2。 
//
// 示例 2： 
//
// 输入: 
//head: 0->1->2->3->4
//G = [0, 3, 1, 4]
//输出: 2
//解释: 
//链表中，0 和 1 是相连接的，3 和 4 是相连接的，所以 [0, 1] 和 [3, 4] 是两个组件，故返回 2。 
//
// 
//
// 提示： 
//
// 
// 如果 N 是给定链表 head 的长度，1 <= N <= 10000。 
// 链表中每个结点的值所在范围为 [0, N - 1]。 
// 1 <= G.length <= 10000 
// G 是链表中所有结点的值的一个子集. 
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;

public class LinkedListComponents {


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new LinkedListComponents().new ListNode(3);
        ListNode node2 = new LinkedListComponents().new ListNode(4);
        ListNode node3 = new LinkedListComponents().new ListNode(0);
        ListNode node4 = new LinkedListComponents().new ListNode(2);
        ListNode node5 = new LinkedListComponents().new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        int[] G = new int[]{ 4};
        Solution solution = new LinkedListComponents().new Solution();
        solution.numComponents(node1, G);
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
        public int numComponents(ListNode head, int[] G) {
            int n = 0;
            if (head == null || G.length == 0) {
                return n;
            }
            int h =  G.length;
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i <  G.length; i++) {
                set.add(G[i]);
            }
            //快慢指针
//            ListNode slow = head;
//            ListNode fast = head;
//            while (slow != null && fast != null) {
//                while (fast != null && set.contains(fast.val)) {
//                    fast = fast.next;
//                }
//                if (set.contains(slow.val)){
//                    n += 1;
//                }
//                if (fast == null){
//                    return n;
//                }
//                slow = fast.next;
//                fast = fast.next;
//            }
            ListNode cur = head;
            while (cur != null) {
                if (set.contains(cur.val) &&
                        (cur.next == null || !set.contains(cur.next.val)))
                    n++;
                cur = cur.next;
            }

            return n;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}