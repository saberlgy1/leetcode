//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。 
//
// 示例: 
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6 
// Related Topics 堆 链表 分治算法

package com.cute.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MergeKSortedLists.ListNode node1 = new MergeKSortedLists().new ListNode(1);
        MergeKSortedLists.ListNode node2 = new MergeKSortedLists().new ListNode(2);
        MergeKSortedLists.ListNode node3 = new MergeKSortedLists().new ListNode(4);
        MergeKSortedLists.ListNode node4 = new MergeKSortedLists().new ListNode(1);
        MergeKSortedLists.ListNode node5 = new MergeKSortedLists().new ListNode(3);
        MergeKSortedLists.ListNode node6 = new MergeKSortedLists().new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node4.next = node5;
        node5.next = node6;
        Solution solution = new MergeKSortedLists().new Solution();
        solution.mergeKLists(new ListNode[]{node1, node4});
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
        public ListNode mergeKLists(ListNode[] lists) {

            /**
             * 递归两两合并
             */
        if (lists.length== 1){
            return lists[0];
        }
        if (lists.length== 0){
            return null;
        }
//
//        ListNode res = lists[0];
//
//        for (int i = 1; i < lists.length; i++){
//           res = merge(res,lists[i]);
//        }
//        return res;

            /**
             * 堆
             */
//            PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
//                @Override
//                public int compare(ListNode o1, ListNode o2) {
//                    return o1.val - o2.val;
//                }
//            });
//            for (ListNode list : lists) {
//                if (list != null) {
//                    queue.add(list);
//                    //  lists[i] = lists[i].next;
//                }
//            }
//
//            ListNode temp = new ListNode(-1);
//            ListNode res = temp;
//            while (!queue.isEmpty()) {
//                temp.next = queue.poll();
//                temp = temp.next;
//                if (temp.next != null){
//                    queue.add(temp.next);
//                }
//            }
//            temp.next = null;
//            return res.next;

        return helper(lists, 0, lists.length - 1);



        }


        private ListNode helper(ListNode[] listNodes, int begin, int end){
            if (begin == end){
                return listNodes[begin];
            }
            int mid = begin + (end - begin) / 2;
            ListNode left = helper(listNodes, begin, mid);
            ListNode right = helper(listNodes, mid + 1, end);
            return merge(left,right);
        }

        private ListNode merge(ListNode l1, ListNode l2) {
            ListNode temp = new ListNode(0);
            ListNode res = temp;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    temp.next = l1;
                    temp = temp.next;
                    l1 = l1.next;
                } else {
                    temp.next = l2;
                    temp = temp.next;
                    l2 = l2.next;
                }
            }
            if (null != l1) {
                temp.next = l1;
            }
            if (null != l2) {
                temp.next = l2;
            }
            return res.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}