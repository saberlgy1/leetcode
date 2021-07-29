//在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。 
//
// 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记； 
//
// 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。 
//
// 
//
// 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。 
//
// 
//
// 示例 1： 
//
// 输入：label = 14
//输出：[1,3,4,14]
// 
//
// 示例 2： 
//
// 输入：label = 26
//输出：[1,2,6,10,26]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= label <= 10^6 
// 
// Related Topics 树 数学 二叉树 
// 👍 88 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PathInZigzagLabelledBinaryTree {
    public static void main(String[] args) {
        Solution solution = new PathInZigzagLabelledBinaryTree().new Solution();
        System.out.println(solution.pathInZigZagTree(14));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：数学+满二叉树性质
        //正常子节点是当前节点值2n,2n+1
        //然后根据奇数行和偶数行做替换即可
        //最后reverse即可
        public List<Integer> pathInZigZagTree(int label) {
            List<Integer> res = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();
            while (label != 0) {
                stack.add(label);
                if (label % 2 == 0) {
                    label /= 2;
                } else {
                    label = (label - 1) / 2;
                }
            }
            boolean isOdd = stack.size() % 2 != 0;
            int max = 1;
            while (!stack.isEmpty()) {

                int temp = stack.pop();
                if (!isOdd) {
                    temp = max + max * 2 - 1 - temp;
                }
                res.add(temp);
                max = 2 * max;
                isOdd = !isOdd;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}