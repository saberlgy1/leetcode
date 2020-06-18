//将一个 二叉搜索树 就地转化为一个 已排序的双向循环链表 。
//
// 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。 
//
// 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。 
//
// 
//
// 示例 1： 
//
// 输入：root = [4,2,5,1,3] 
//
//输出：[1,2,3,4,5]
//
//解释：下图显示了转化后的二叉搜索树，实线表示后继关系，虚线表示前驱关系。
//
// 
//
// 示例 2： 
//
// 输入：root = [2,1,3]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 输入：root = []
//输出：[]
//解释：输入是空树，所以输出也是空链表。
// 
//
// 示例 4： 
//
// 输入：root = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// -1000 <= Node.val <= 1000 
// Node.left.val < Node.val < Node.right.val 
// Node.val 的所有值都是独一无二的 
// 0 <= Number of Nodes <= 2000 
// 
// Related Topics 树 链表 分治算法

package com.cute.leetcode.editor.cn;

import java.util.Stack;
import java.util.List;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static void main(String[] args) {
        Node node1 = new ConvertBinarySearchTreeToSortedDoublyLinkedList().new Node(1, null, null);
        Node node2 = new ConvertBinarySearchTreeToSortedDoublyLinkedList().new Node(2, null, null);
        Node node3 = new ConvertBinarySearchTreeToSortedDoublyLinkedList().new Node(3, null, null);
        Node node4 = new ConvertBinarySearchTreeToSortedDoublyLinkedList().new Node(4, null, null);
        Node node5 = new ConvertBinarySearchTreeToSortedDoublyLinkedList().new Node(5, null, null);
        node4.left = node2;
        node4.right = node5;
        node2.left = node1;
        node2.right = node3;
        Solution solution = new ConvertBinarySearchTreeToSortedDoublyLinkedList().new Solution();
        solution.treeToDoublyList(node4);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

    class Solution {

        //定义最大节点、最小节点
        Node first = null;
        Node last = null;

        public void helper(Node node) {
            if (null != node) {
                helper(node.left);
                if (last != null) {
                    last.right = node;
                    node.left = last;
                } else {
                    first = node;
                }
                last = node;
                helper(node.right);
            }
        }


        public Node treeToDoublyList(Node root) {
            if (null == root) {
                return root;
            }
            //遍历根节点
            //二叉搜索树 为中序遍历 顺序 左➡️中➡️右
            //递归解法
//            helper(root);
//            //头尾节点相连
//            last.right = first;
//            first.left = last;
//            return first;
            //非递归解法
            //栈解决
            Stack<Node> stack = new Stack<>();
            //找到最小左子节点
            Node head = new Node(0, null, null);
            Node p = head;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                if (!stack.isEmpty()){
                    //弹出最小左子树
                    Node temp = stack.pop();
                    //如果p此时不在虚节点，那就应该左子树关联p节点
                    if (p != head){
                        temp.left = p;
                    }
                    p.right = temp;
                    p = p.right;
                    //判断是否有最小节点的右子树 如果有应该先放右子树 没有则从栈中取
                    root = temp.right;
                }
            }
            p.right = head.right;
            if (head.right != null){
                head.right.left = p;
            }
            return head.right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}