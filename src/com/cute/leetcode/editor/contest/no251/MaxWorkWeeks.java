package com.cute.leetcode.editor.contest.no251;

import java.util.*;
/**
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 *
 * 你可以按下面两个规则参与项目中的工作：
 *
 * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 *
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。
 *
 *
 *
 * 示例 1：
 *
 * 输入：milestones = [1,2,3]
 * 输出：6
 * 解释：一种可能的情形是：
 * ​​​​- 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * 总周数是 6 。
 * 示例 2：
 *
 * 输入：milestones = [5,2,1]
 * 输出：7
 * 解释：一种可能的情形是：
 * - 第 1 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 2 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 3 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 4 周，你参与并完成项目 1 中的一个阶段任务。
 * - 第 5 周，你参与并完成项目 0 中的一个阶段任务。
 * - 第 6 周，你参与并完成项目 2 中的一个阶段任务。
 * - 第 7 周，你参与并完成项目 0 中的一个阶段任务。
 * 总周数是 7 。
 * 注意，你不能在第 8 周参与完成项目 0 中的最后一个阶段任务，因为这会违反规则。
 * 因此，项目 0 中会有一个阶段任务维持未完成状态。
 *
 *
 * 提示：
 *
 * n == milestones.length
 * 1 <= n <= 105
 * 1 <= milestones[i] <= 109
 */

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
