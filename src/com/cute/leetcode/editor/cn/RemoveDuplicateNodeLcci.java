//编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。 
//
// 示例1: 
//
// 
// 输入：[1, 2, 3, 3, 2, 1]
// 输出：[1, 2, 3]
// 
//
// 示例2: 
//
// 
// 输入：[1, 1, 1, 1, 2]
// 输出：[1, 2]
// 
//
// 提示： 
//
// 
// 链表长度在[0, 20000]范围内。 
// 链表元素在[0, 20000]范围内。 
// 
//
// 进阶： 
//
// 如果不得使用临时缓冲区，该怎么解决？ 
// Related Topics 链表

package com.cute.leetcode.editor.cn;

import java.util.HashMap;

public class RemoveDuplicateNodeLcci {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new RemoveDuplicateNodeLcci().new ListNode(1);
        ListNode node2 = new RemoveDuplicateNodeLcci().new ListNode(2);
        ListNode node3 = new RemoveDuplicateNodeLcci().new ListNode(3);
        ListNode node4 = new RemoveDuplicateNodeLcci().new ListNode(3);
        ListNode node5 = new RemoveDuplicateNodeLcci().new ListNode(2);
        ListNode node6 = new RemoveDuplicateNodeLcci().new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        Solution solution = new RemoveDuplicateNodeLcci().new Solution();
        solution.removeDuplicateNodes(node1);
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
        public ListNode removeDuplicateNodes(ListNode head) {
            //hash
//            HashMap<Integer, ListNode> map = new HashMap<>();
//            ListNode dummy = new ListNode(-1);
//            dummy.next = head;
//            if (head == null) {
//                return head;
//            }
//            ListNode temp  = dummy;
//            while (head != null ) {
//                if (map.containsKey(head.val)){
//                    temp.next = head.next;
//                    head = head.next;
//                }else {
//                    map.put(head.val,head);
//                    temp =temp.next;
//                }
//
//            }
//            return dummy.next;
            //boolean数组
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode temp = dummy;
            boolean[] res = new boolean[20000];
            while (head != null){
                if (!res[head.val]){
                    res[head.val] = true;
                    head = head.next;
                    temp = temp.next;
                }else{
                    temp.next = head.next;
                    head = head.next;
                }
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}