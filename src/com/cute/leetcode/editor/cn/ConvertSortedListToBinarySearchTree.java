//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。 
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。 
//
// 示例: 
//
// 给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
// 
// Related Topics 深度优先搜索 链表

package com.cute.leetcode.editor.cn;


import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBinarySearchTree {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        Solution solution = new ConvertSortedListToBinarySearchTree().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        //因为有序 所有中间节点为根节点
        //找到根部节点
        List<Integer> value = new ArrayList<>();
        public ListNode findMiddle(ListNode head) {
            ListNode pre = null;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            if (pre != null) {
                pre.next = null;
            }
            return slow;
        }

        //递归转换
        public TreeNode convertToBST(int left, int right) {
            if (left > right) {
                return null;
            }
            int mid = (left + right)/2;

            TreeNode node = new TreeNode(value.get(mid));
            if(left == right){
                return  node;
            }
            node.left = convertToBST(left, mid -1);
            node.right = convertToBST(mid + 1, right);
            return  node;

        }


        public TreeNode sortedListToBST(ListNode head) {
            //递归
//        if (head == null){
//            return  null;
//        }
//        ListNode mid = this.findMiddle(head);
//        TreeNode node = new TreeNode(mid.val);
//        node.left = sortedListToBST(head);
//        node.right = sortedListToBST(mid.next);
//        return node;
            while (head != null) {
                this.value.add(head.val);
                head = head.next;
            }
           return convertToBST(0, this.value.size() - 1);


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}