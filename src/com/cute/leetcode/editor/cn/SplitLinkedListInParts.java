//给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
//
// 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。 
//
// 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。 
//
// 返回一个符合上述规则的链表的列表。 
//
// 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ] 
//
// 示例 1： 
//
// 
//输入: 
//root = [1, 2, 3], k = 5
//输出: [[1],[2],[3],[],[]]
//解释:
//输入输出各部分都应该是链表，而不是数组。
//例如, 输入的结点 root 的 val= 1, root.next.val = 2, \root.next.next.val = 3, 且 root.ne
//xt.next.next = null。
//第一个输出 output[0] 是 output[0].val = 1, output[0].next = null。
//最后一个元素 output[4] 为 null, 它代表了最后一个部分为空链表。
// 
//
// 示例 2： 
//
// 
//输入: 
//root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
//输出: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
//解释:
//输入被分成了几个连续的部分，并且每部分的长度相差不超过1.前面部分的长度大于等于后面部分的长度。
// 
//
// 
//
// 提示: 
//
// 
// root 的长度范围： [0, 1000]. 
// 输入的每个节点的大小范围：[0, 999]. 
// k 的取值范围： [1, 50]. 
// 
//
// 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplitLinkedListInParts {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        SplitLinkedListInParts.ListNode node1 = new SplitLinkedListInParts().new ListNode(1);
        SplitLinkedListInParts.ListNode node2 = new SplitLinkedListInParts().new ListNode(2);
        SplitLinkedListInParts.ListNode node3 = new SplitLinkedListInParts().new ListNode(3);
        SplitLinkedListInParts.ListNode node4 = new SplitLinkedListInParts().new ListNode(4);
        SplitLinkedListInParts.ListNode node5 = new SplitLinkedListInParts().new ListNode(5);
        SplitLinkedListInParts.ListNode node6 = new SplitLinkedListInParts().new ListNode(6);
        SplitLinkedListInParts.ListNode node7 = new SplitLinkedListInParts().new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        Solution solution = new SplitLinkedListInParts().new Solution();
        solution.splitListToParts(node1, 3);
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
        public ListNode[] splitListToParts(ListNode root, int k) {
            //定义长度为k的数组
            ListNode[] res = new ListNode[k];
            //corner case
            if (root == null) {
                Arrays.fill(res, null);
                return res;
            }
            //获得链表长度
            int len = 0;
            ListNode dummy = root;
            while (dummy != null) {
                len += 1;
                dummy = dummy.next;
            }
            ListNode ins = root;
            //k >= len 数组每个元素只能存一个listnode 或者null
            if (k >= len) {
                for (int i = 0; i < len; i++) {
                    if (ins != null) {
                        res[i] = new ListNode(ins.val);
                        ins = ins.next;
                    }
                }
                for (int i = len; i < k; i++) {
                    res[i] = null;
                }
                return res;
            }
            //可以存多个 前mod个元素 要有std+ 1个listnode 之后的k- mod个元素 只有std个元素
            int mod = len % k;
            int std = len / k;
            ListNode slow = root;
            ListNode fast = slow;
            for (int i = 0; i < k; i++) {
               if (i  < mod){
                   //快指针移动std+1 个位置
                   for (int j = 1; j < std+1; j++) {
                       fast = fast.next;
                   }
                   ListNode temp = fast.next;
                   fast.next = null;
                   res[i] = slow;
                   slow = temp;
                   fast = slow;
               }else {
                   //快指针移动std+1 个位置
                   for (int j = 1; j < std; j++) {
                       fast = fast.next;
                   }
                   ListNode temp = fast.next;
                   fast.next = null;
                   res[i] = slow;
                   slow = temp;
                   fast = slow;
               }
            }
            return res;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}