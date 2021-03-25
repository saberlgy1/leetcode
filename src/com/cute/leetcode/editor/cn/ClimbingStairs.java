//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1554 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        System.out.println(solution.climbStairs(45));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();

        public int climbStairs(int n) {
            //方法1 递归 time limit exceeded
            if (n == 0) {
                return 0;
            }
            ;
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            //优化，将已经计算过的值存入map减少二次计算
            int x = 0, y = 0;
            if (map.containsKey(n - 1)) {
                x = map.get(n - 1);
            } else {
                x = climbStairs(n - 1);
                map.put(n - 1, x);
            }
            if (map.containsKey(n - 2)) {
                y = map.get(n - 2);
            } else {
                y = climbStairs(n - 2);
                map.put(n - 2, y);
            }

            return x + y;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}