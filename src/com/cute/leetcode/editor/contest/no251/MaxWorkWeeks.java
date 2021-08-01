package com.cute.leetcode.editor.contest.no251;

import java.util.*;

/**
 * @program: leetcode
 * @description: 251周赛2 连续工作周数
 * @author: lgy
 * @create: 2021-08-01 10:54
 **/

public class MaxWorkWeeks {
    public static void main(String[] args) {
        //int[] test = new int[]{1, 2, 3, 6, 8, 9};
        //int[] test = new int[]{1, 2, 5};
        int[] test = new int[]{5, 7, 5, 7, 9, 7};
        //int[] test = new int[]{1, 2, 3};
        System.out.println(new MaxWorkWeeks().new Solution().numberOfWeeks(test));
    }

    class Solution {
        //贪心法感觉可以找到最长的一个任务集，每次和他交换即可
        //这种贪心好像无法满足要求最长周数
        //应该是从最大解开始找到其余元素和为当前最大元素使用的最少情况
        //贪心的还是不够贪
        //其实只要相信最大解如果可以满足情况，那么其余任务一定都可以满足
        long res = 0;

        public long numberOfWeeks(int[] milestones) {
            int max = milestones[0];
            long sum = 0L;
            for (int i = 0; i < milestones.length; i++) {
                sum += milestones[i];
                max = Math.max(max, milestones[i]);
            }
            long remain = sum - max;
            return Math.min(sum, 2 * remain + 1);
        }
    }
}
