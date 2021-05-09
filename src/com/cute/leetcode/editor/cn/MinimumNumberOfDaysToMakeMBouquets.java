//给你一个整数数组 bloomDay，以及两个整数 m 和 k 。 
//
// 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。 
//
// 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。 
//
// 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
//输出：3
//解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
//现在需要制作 3 束花，每束只需要 1 朵。
//1 天后：[x, _, _, _, _]   // 只能制作 1 束花
//2 天后：[x, _, _, _, x]   // 只能制作 2 束花
//3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
// 
//
// 示例 2： 
//
// 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
//输出：-1
//解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
// 
//
// 示例 3： 
//
// 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
//输出：12
//解释：要制作 2 束花，每束需要 3 朵。
//花园在 7 天后和 12 天后的情况如下：
//7 天后：[x, x, x, x, _, x, x]
//可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
//12 天后：[x, x, x, x, x, x, x]
//显然，我们可以用不同的方式制作两束花。
// 
//
// 示例 4： 
//
// 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
//输出：1000000000
//解释：需要等 1000000000 天才能采到花来制作花束
// 
//
// 示例 5： 
//
// 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// bloomDay.length == n 
// 1 <= n <= 10^5 
// 1 <= bloomDay[i] <= 10^9 
// 1 <= m <= 10^6 
// 1 <= k <= n 
// 
// Related Topics 数组 二分查找 
// 👍 100 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class MinimumNumberOfDaysToMakeMBouquets {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfDaysToMakeMBouquets().new Solution();
        int[] bloomday = new int[]{
                1, 10, 3, 10, 2
        };
        System.out.println(solution.minDays(bloomday, 3, 1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDays(int[] bloomDay, int m, int k) {
            //O(n + n+ nlgn)
            //corner case
            if (bloomDay.length < m * k) {
                return -1;
            }
            //上边界 是数组最大值
            //下边界是数组最小值
            //int min = Arrays.stream(bloomDay).min().getAsInt();
            //优化1min从1开始即可
            int min = 1;
            int max = Arrays.stream(bloomDay).max().getAsInt();
            int mid = max - (max - min + 1) / 2;
            //二分查找判断是否满足
            while (min < max) {
                mid = max - (max - min + 1) / 2;
                int count = 0;
                //优化3 不实用额外空间记录，直接算即可
                /*boolean[] merged = new boolean[bloomDay.length];
                for (int i = 0; i < bloomDay.length; i++
                ) {
                    //判断哪些花可以作为打包的花
                    if (bloomDay[i] <= mid) {
                        merged[i] = true;
                    }
                }*/
                //判断是否满足k朵花连续切数量足够打包
                //递归调用即可
                //if (dfs(m, count, k, merged, 0, k))
                if (dfs(m, k, bloomDay,mid)){
                    max = mid;
                } else {
                    min = mid + 1;
                }
            }
            return max;

        }

        private boolean dfs(int m,  int k, int[] bloomDay,int days) {
            //优化2 dfs可以不用递归
            /*if (count >= m) {
                return true;
            }
            if (index > merged.length) {
                return false;
            }
            if (temp == 0) {
                count++;
                temp = k;
            }
            if (index < merged.length &&merged[index]) {
                temp--;
            } else {
                temp = k;
            }

            index++;
            return dfs(m, count, k, merged, index, temp);*/
            int bouquets = 0;
            int flowers = 0;
            int length = bloomDay.length;
            for (int i = 0; i < length && bouquets < m; i++) {
                if (bloomDay[i] <= days) {
                    flowers++;
                    if (flowers == k) {
                        bouquets++;
                        flowers = 0;
                    }
                } else {
                    flowers = 0;
                }
            }
            return bouquets >= m;


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}