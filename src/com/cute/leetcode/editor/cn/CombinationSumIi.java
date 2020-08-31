//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 344 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new CombinationSumIi().new Solution();
        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
        solution.combinationSum2(nums, 8);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            //corner case
            if (candidates.length == 0) {
                return new ArrayList<>();
            }
            Arrays.sort(candidates);
            //排序没啥意义、不如直接n叉树
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            HashSet<List<Integer>> set = new HashSet<>();
            backSearch(set, res, list, candidates, target, 0);
            return res;
        }

        public void backSearch(HashSet<List<Integer>> set, List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
            if (target == 0 && !set.contains(list)) {
                set.add(list);
                res.add(list);
                return;
            }
            if (start >= candidates.length) {
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] <= target) {
                    if (i > start ) {
                        continue;
                    }
                    List<Integer> cur = new ArrayList<>(list);
                    cur.add(candidates[i]);
                    backSearch(set, res, cur, candidates, target - candidates[i], i + 1);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}