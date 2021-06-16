//亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。 
//
// 游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。 
//
// 亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
// 
//
// 假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。 
//
// 
//
// 示例： 
//
// 
//输入：[5,3,4,5]
//输出：true
//解释：
//亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
//假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
//如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
//如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
//这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= piles.length <= 500 
// piles.length 是偶数。 
// 1 <= piles[i] <= 500 
// sum(piles) 是奇数。 
// 
// Related Topics 极小化极大 数学 动态规划 
// 👍 277 👎 0

package com.cute.leetcode.editor.cn;

public class StoneGame {
    public static void main(String[] args) {
        int[] nums = new int[]{5,3,4,5};
        Solution solution = new StoneGame().new Solution();
        System.out.println(solution.stoneGame(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：博弈论
        //偶数长度数组先手情况总能拿到首尾两端相对较大的元素，所以一定先手赢
       /* public boolean stoneGame(int[] piles) {
            return true;
        }*/
        //思路二：动态规划
        //可以先考虑一个子问题就是在当元素数组只有两个元素的时候先手取Math.max(piles[0],pilse[1])
        //递归向外同时扩展两侧区间[0, j + 1]
        //无论先手后手取得一定是当前区间端点最大值
        //可以扩大区间范围，使用l, r分别代表左右边界
        //如果先手取[l,r]内区间端点元素
        //取左边元素时，先手元素为piles[l-1],后手选取元素为Max[piles[l+1],piles[r]]
        //取右边元素是，先手元素为piles[r-1],后手选取元素为Max[piles[l],piles[r-1]]
        //先手会优先考虑取得最大差值的情况
        //我们假设a为先手元素，b 为后手元素，也就是说我们先手取得的是Math.max[(a1-b1),(a2-b2)]
        //a1a2 b1b2 分别代表先手取左右两边元素得分和对应后手的得分情况
        //如果按照递归思路的话可以简单定义一个递归函数
        //int(int l, int r,int[] piles)
        //表示在当前l,r为边界的时候取得的得分最大差值
        //观察到可变参数为l,r
        //所以可以定义一个dp[n+1][n+1]n为piles数组长度 表示在左右边界为ij的情况下，先手取得的最大值与后手取得的差值最大值，保证每次先手得分最高
        //  dp[i][j] = Math.max(a, b);
        //为了防止数组越界，也就是保证dp[i+1]和dp[j-1]一定有值
        public boolean stoneGame(int[] piles) {
            int n = piles.length;
            int[][] dp = new int[n + 1][n + 1];
            //
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int a = piles[i] - dp[i + 1][j];
                    int b = piles[j] - dp[i][j - 1];
                    dp[i][j] = Math.max(a,b);
                }
            }
            return dp[0][n-1] > 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}