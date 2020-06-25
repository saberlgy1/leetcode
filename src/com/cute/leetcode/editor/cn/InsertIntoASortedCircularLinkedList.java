//给定循环升序列表中的一个点，写一个函数向这个列表中插入一个新元素，使这个列表仍然是循环升序的。给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中
//最小元素的指针。 
//
// 如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。 
//
// 如果列表为空（给定的节点是 null），你需要创建一个循环有序列表并返回这个点。否则。请返回原先给定的节点。 
//
// 下面的例子可以帮你更好的理解这个问题： 
//
// 
//
// 
// 
//在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2。 
//
// 
//
// 
// 
//
// 新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3。 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

public class InsertIntoASortedCircularLinkedList {

    public class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    ;

    public static void main(String[] args) {

        Solution solution = new InsertIntoASortedCircularLinkedList().new Solution();
        Node node1 = new InsertIntoASortedCircularLinkedList().new Node(3);
        Node node2 = new InsertIntoASortedCircularLinkedList().new Node(5);
        Node node3 = new InsertIntoASortedCircularLinkedList().new Node(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        //Node node1 = new InsertIntoASortedCircularLinkedList().new Node();
        solution.insert(node1, 5);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

    class Solution {
        public Node insert(Node head, int insertVal) {
            Node dummy = new Node(-1, head);
            //corner case
            if (head == null || head.val == 0) {
                head = new Node(insertVal);
                head.next = head;
                return head;
            }
            Node slow = head;
            Node fast = head.next;
            while (fast != head) {
                if ((insertVal >= slow.val && insertVal < fast.val) || (slow.val > fast.val && slow.val <= insertVal) || (slow.val > fast.val && fast.val >= insertVal)) {
                    Node temp = fast;
                    slow.next = new Node(insertVal, temp);
                    return dummy.next;
                } else {
                    fast = fast.next;
                    slow = slow.next;
                }
            }
            Node temp = fast;
            slow.next = new Node(insertVal, temp);
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}