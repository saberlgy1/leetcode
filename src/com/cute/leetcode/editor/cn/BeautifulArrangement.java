//假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
//我们就称这个数组为一个优美的排列。条件：
//
//
// 第 i 位的数字能被 i 整除
// i 能被第 i 位上的数字整除
//
//
// 现在给定一个整数 N，请问可以构造多少个优美的排列？
//
// 示例1:
//
//
//输入: 2
//输出: 2
//解释:
//
//第 1 个优美的排列是 [1, 2]:
//  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
//
//第 2 个优美的排列是 [2, 1]:
//  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
//
//
// 说明:
//
//
// N 是一个正整数，并且不会超过15。
//
// Related Topics 位运算 数组 动态规划 回溯 状态压缩
// 👍 174 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class BeautifulArrangement {
    public static void main(String[] args) {
        Solution solution = new BeautifulArrangement().new Solution();
        System.out.println(solution.countArrangement(2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：dfs+记忆化
        //定义满足条件可以放置在i位置的元素
        //定义match数组，简化可放入元素的搜索过程
        //dfs跳出递归条件为所有元素都已经放置
        //k % i == 0 ||i % k == 0
        /*int res = 0;
        List<Integer>[] match;

        public int countArrangement(int n) {
            match = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                int idx = 1;
                match[i] = new ArrayList<>();
                while (idx <= n) {
                    if (idx % i == 0 || i % idx == 0) {
                        match[i].add(idx);
                    }
                    idx++;
                }
            }
            boolean[] vis = new boolean[n + 1];
            dfs(1, n, vis);
            return res;
        }

        public void dfs(int index, int n, boolean[] vis) {
            if (index == n + 1) {
                res += 1;
                return;
            }
            for (int num : match[index]
            ) {
               if (!vis[num]){
                   vis[num] = true;
                   dfs(index + 1, n, vis);
                   vis[num] = false;
               }
            }
        }*/
        //思路二：动态规划
        //毫无疑问上述递归可以转换为dp方程
        //通过观察dfs变量
        //可以发现我们可以定义一个二维dp方程（上述递归思路n固定不变可以不用放在递归方程中，故定义二维dp方程）
        //f[i][j]
        //接下来需要考虑的事如何将vis数组定义为其中一个维度
        //根据题目输入限制可发现最大为15
        //所有元素均要输入
        //因此我们可以定义一个二进制数。共15位
        //对应数位为1，则表示该元素已经使用
        //因此f[i][j]的定义也就清晰了起来，表示在使用i个元素的时候所有字母使用的状态为j的方案数
        //j为二进制表示的元素使用数量和状态
        //初始化f[0][0]固定为1，表示使用0个元素，每个元素都未使用的方案共有1种
        //f[i][j] = \sumf[i-1][state & (1 << (k-1))]
        /*public int countArrangement(int n) {
            int mask = 1 << n;
            int[][] dp = new int[n + 1][mask];
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int state = 0; state < mask; state++) {
                    for (int k = 1; k <= n; k++) {
                        if ((state >> (k - 1) & 1) == 0) {
                            continue;
                        }
                        if (k % i != 0 && i % k != 0) {
                            continue;
                        }
                        dp[i][state] += dp[i - 1][state & ~(1 << (k - 1))];
                    }
                }
            }
            return dp[n][mask - 1];
        }*/
        //思路三：压缩优化
        //这个属实是没想到的
        //三叶大佬yyds
        //定义f[state] 表示当前选择数值情况为 state 时的所有方案的数量
        //初始化f[0]=1
        //f[state] += f[state & (~(1 << i))];
        public int countArrangement(int n) {
            int mask = 1 << n;
            int[] f = new int[mask];
            f[0] = 1;
            // 枚举所有的状态
            for (int state = 1; state < mask; state++) {
                // 计算 state 有多少个 1（也就是当前排序长度为多少）
                int cnt = Integer.bitCount(state);
                // 枚举最后一位数值为多少
                for (int i = 0; i < n; i++) {
                    // 数值在 state 中必须是 1
                    if (((state >> i) & 1) == 0) continue;
                    // 数值（i + 1）和位置（cnt）之间满足任一整除关系
                    if ((i + 1) % cnt != 0 && cnt % (i + 1) != 0) continue;
                    // state & (~(1 << i)) 代表将 state 中所选数值的位置置零
                    f[state] += f[state & (~(1 << i))];
                }
            }
            return f[mask - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}