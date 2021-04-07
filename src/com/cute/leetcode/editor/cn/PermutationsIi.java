//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 661 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new PermutationsIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            //无需排序，无重复直接深度优先遍历就可以
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> ans = new ArrayList<>();
            boolean[] visited = new boolean[nums.length];
            Arrays.sort(nums);
            int first = 0;
            dfs(nums, ans, res, first, visited);
            return res;
        }

        private void dfs(int[] nums, List<Integer> ans, List<List<Integer>> res, int first, boolean[] visited) {
            if (first == nums.length) {
                res.add(new ArrayList<Integer>(ans));
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                    continue;
                }
                ans.add(nums[i]);
                visited[i] = true;
                dfs(nums, ans, res, first + 1, visited);
                visited[i] = false;
                ans.remove(first);

            }


        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}