package com.cute.leetcode.editor.catagory.dp.backpackage;


/**
 * @program: leetcode
 * @description: 经典01背包问题
 * @author: lgy
 * @create: 2021-06-09 19:46
 **/

public class ClassicPackage {

    public static void main(String[] args) {

    }


    class Solution {
        //TODO
        //思路一：动态规划
        //根据题目分析可以求得当进行dfs递归的时候需要四个参数
        //总容量T 数量N 遍历到的背包i 剩余容量v
        //int(T,N,i,v)
        //分析可知共两个可变参数 i,v
        //定义dp数组 dp[value.lenght][N+1]
        //dp[i][j] 表示遍历到第i件物品时，在j的容量限制下的获得的最大价值
        //最终求解dp[value.length][N+1]
        //动态方程推倒过程可以做如下推倒
        //边界情况当i = 0 也就是只考虑第一件存储的情况
        //dp[0][j] = (w[0]<=j?w[0]:0)
        //第一件之后的求解过程
        //如果取最后一件物品，则dp[i-1][j-w[i]]+v[i]表示拿掉最后一件物品剩余的物品最大价值，加上最后一个元素的价值
        //与不取(取到i-1个元素的最大值)dp[i-1][j]做比较即可
        //dp[i][j] = Math.max(dp[i-1][j], dp[i][j-w[i]] + v[i])
        public int getMaxValue(int[] value, int[] weights, int n) {
            int[][] dp = new int[value.length][n + 1];
            //取第一件
            for (int i = 0; i < n; i++){
                dp[0][i] = (weights[0]<=i)?value[0]:0;
            }
            return 0;
        }
    }


}
