//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 1271 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        Solution solution = new Permutations().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
          /*  //无需排序，无重复直接深度优先遍历就可以
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> ans = new ArrayList<>();
            for (int num : nums) {
                ans.add(num);
            }

            int first = 0;
            dfs(nums,ans,res, first);
            return  res;
        }
        private void dfs(int[] nums, List<Integer> ans, List<List<Integer>> res, int first){
            if (first == nums.length){
                res.add(new ArrayList<Integer>(ans));
            }
            for (int i = first ; i < nums.length; i++){
                Collections.swap(ans,first,i);
                dfs(nums, ans,res,first + 1);
                Collections.swap(ans,first,i);
            }


        }*/
            List<List<Integer>> res = new ArrayList<>();
            List<Integer> ans = new ArrayList<>();
            for (int num : nums
            ) {
                ans.add(num);
            }
            int first = 0;
            backTrack(nums, ans, res, first);
            return res;
        }
        private void backTrack(int[] nums, List<Integer> ans, List<List<Integer>> res, int first){
            if (first == nums.length){
                res.add(new ArrayList<>(ans));
            }
            for (int i = first; i < nums.length; i++){
                Collections.swap(ans,first,i);
                backTrack(nums, ans,res,i+1);
                Collections.swap(ans,first,i);

            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    }