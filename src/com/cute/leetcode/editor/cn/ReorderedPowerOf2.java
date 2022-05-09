//给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。 
//
// 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：1
//输出：true
// 
//
// 示例 2： 
//
// 输入：10
//输出：false
// 
//
// 示例 3： 
//
// 输入：16
//输出：true
// 
//
// 示例 4： 
//
// 输入：24
//输出：false
// 
//
// 示例 5： 
//
// 输入：46
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= N <= 10^9 
// 
// Related Topics 数学 计数 枚举 排序 👍 137 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ReorderedPowerOf2 {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        static Set<String> target = new HashSet<>();

        static {
            int init = 1;
            for (int i = 0; i <= 31; i++) {
                String temp = String.valueOf(init);
                StringBuilder sb = getString(temp);
                target.add(sb.toString());
                init *= 2;
            }
        }

        public static boolean reorderedPowerOf2(int n) {

            return target.contains(getString(String.valueOf(n)).toString());

        }

        private static StringBuilder getString(String temp) {
            PriorityQueue<Character> priorityQueue = new PriorityQueue<>();
            for (Character c : temp.toCharArray()) {
                priorityQueue.add(c);
            }
            StringBuilder sb = new StringBuilder();
            while (!priorityQueue.isEmpty()) {
                sb.append(priorityQueue.poll());
            }
            return sb;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}