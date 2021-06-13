package com.cute.leetcode.editor.catagory.dp.backpackage;


/**
 * @program: leetcode
 * @description: 经典01背包问题
 * @author: lgy
 * @create: 2021-06-09 19:46
 **/

public class ClassicPackage {

    public static void main(String[] args) {
        Solution solution = new ClassicPackage().new Solution();
        int[] values = new int[]{4, 2, 3};
        int[] weights = new int[]{4, 2, 3};
        System.out.println(solution.getMaxValue(values, weights, 7));


    }


    class Solution {
        //TODO
        //思路一：动态规划
        //根据题目分析可以求得当进行dfs递归的时候需要四个参数
        //总容量T 数量N 遍历到的背包i 剩余容量v
        //int(n,i,v)
        //分析可知共两个可变参数 i,v
        //定义dp数组 dp[value.lenght][N+1]
        //dp[i][j] 表示遍历到第i件物品时，在j的容量限制下的获得的最大价值
        //最终求解dp[value.length][N+1]
        //动态方程推倒过程可以做如下推倒
        //边界情况当i = 0 也就是只考虑取第一件的情况
        //dp[0][j] = (w[0]<=j?w[0]:0)
        //第一件之后的求解过程
        //如果取最后一件物品，则dp[i-1][j-w[i]]+v[i]表示拿掉最后一件物品剩余的物品最大价值，加上最后一个元素的价值
        //与不取(取到i-1个元素的最大值)dp[i-1][j]做比较即可
        //dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i])
        /*public int getMaxValue(int[] value, int[] weights, int n) {
            int[][] dp = new int[value.length][n + 1];
            //边界条件
            for (int i = 0; i < n; i++) {
                dp[0][i] = (weights[0] <= i) ? value[0] : 0;
            }
            for (int i = 1; i < value.length; i++) {
                int val = value[i];
                for (int j = 1; j <= n; j++) {
                    if (j >= weights[i]) {
                        //取选当前物品和不选当前物品的最大值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + val);
                    }
                }
            }
            return dp[value.length - 1][n];
        }*/
       /* public int getMaxValue(int[] values, int[] weights, int n) {
            int[][] dp = new int[2][n + 1];
            for (int i = 0; i < n; i++) {
                dp[0][i] = (weights[0] <= i) ? values[0] : 0;
            }
            for (int i = 1; i < values.length; i++) {
                for (int j = 0; j <= n; j++) {
                    //不选择当前物品
                    int tempF = dp[(i - 1) & 1][j];
                    //选择当前物品
                    int tempT = j >= weights[i] ? dp[(i - 1) & 1][j - weights[i]] + values[i] : 0;
                    dp[i & 1][j] = Math.max(tempF, tempT);
                }
            }
            return dp[(values.length - 1) & 1][n];
        }*/
        public int getMaxValue(int[] values, int[] weights, int n) {
            int[] dp = new int[n + 1];
            for (int i = 0; i < values.length; i++) {
                for (int j = n; j >= weights[i]; j--) {
                    // 不选该物品
                    int x = dp[j];
                    // 选择该物品
                    int y = dp[j - weights[i]] + values[i];
                    dp[j] = Math.max(x, y);
                }
            }
            return dp[n];
        }

    }


}
