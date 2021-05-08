//给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。 
//
// 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你
//设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。 
//
// 返回分配方案中尽可能 最小 的 最大工作时间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：jobs = [3,2,3], k = 3
//输出：3
//解释：给每位工人分配一项工作，最大工作时间是 3 。
// 
//
// 示例 2： 
//
// 
//输入：jobs = [1,2,4,7,8], k = 2
//输出：11
//解释：按下述方式分配工作：
//1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
//2 号工人：4、7（工作时间 = 4 + 7 = 11）
//最大工作时间是 11 。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= jobs.length <= 12 
// 1 <= jobs[i] <= 107 
// 
// Related Topics 递归 回溯算法 
// 👍 74 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class FindMinimumTimeToFinishAllJobs {
    public static void main(String[] args) {
        Solution solution = new FindMinimumTimeToFinishAllJobs().new Solution();
        int[] jobs = new int[]{6518448,8819833,7991995,7454298,2087579,380625,4031400,2905811,4901241,8480231,7750692,3544254};
        System.out.println(solution.minimumTimeRequired(jobs, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumTimeRequired(int[] jobs, int k) {
            //二分查找，如果limit满足就找limit左边，然后找到最小值
            //定义左右边界，左边界 int最小值，右边界总和
            //先排序
            //corner case

            Arrays.sort(jobs);
            int max = Arrays.stream(jobs).sum(), total = max;
            int min = jobs[0];
            int limit = max;
            while (min < max) {
                int mid = min + (max - min + 1) / 2;
                //生命一个工作组，大小为k，即预定k个工人刚好一人一组
                int[] workloads = new int[k];
                //深度优先判断是否满足
                boolean flag = dfs(mid, k, total, jobs, 0,workloads);
                if (flag) {
                    limit = mid;
                    max = mid - 1;
                } else {
                    min = mid;
                }
            }
            return limit;


        }


        private boolean dfs(int limit, int k, int total, int[] jobs, int i, int[] workloads) {
            //cornre case
            if (limit * k < total || limit < jobs[jobs.length - 1]) {
                return false;
            }
            //从大的开始分配，优先保证大的分配完成
            //目前想到的判断全部遍历的方法
            //这个方法也只是从大到小，无法满足所有方案
            //深度优先还需要新的方法

            /* 错误遍历
            while (checkVisited(visited)) {
                for (int i = jobs.length - 1; i >= 0; i--) {
                    if (!visited[i] && temp + jobs[i] <= limit) {
                        temp = temp + jobs[i];
                        visited[i] = true;
                    }
                }
                temp = 0;
                count ++;
            }*/
            if (i >= jobs.length){
                return true;
            }
            int cur = jobs[i];
            for (int j = 0; j < workloads.length; ++j) {
                if (workloads[j] + cur <= limit) {
                    workloads[j] += cur;
                    if (dfs(limit,k,total,jobs, i + 1,workloads )) {
                        return true;
                    }
                    workloads[j] -= cur;
                }
                // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
                // 或者当前工作恰能使该工人的工作量达到了上限
                // 这两种情况下我们无需尝试继续分配工作
                if (workloads[j] == 0 || workloads[j] + cur == limit) {
                    break;
                }
            }
            return false;
        }

        private boolean checkVisited(boolean[] visited){
            for (boolean flag: visited
                 ) {
                if (!flag){
                    return true;
                }
            }
            return false;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}