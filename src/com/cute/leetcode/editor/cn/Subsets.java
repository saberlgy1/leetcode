//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯算法 
// 👍 1008 👎 0

package com.cute.leetcode.editor.cn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        Solution solution = new Subsets().new Solution();
        int[] nums = new int[]{1, 2, 3};
        solution.subsets(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        List<Integer> t = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();


        public List<List<Integer>> subsets(int[] nums) {


            List<List<Integer>> res = new ArrayList<>();
             dfs(0,nums);
             return ans;
            //Cn0 + ... Cnn = 子集长度 = 2^n = 2 << n
            // 二进制与运算
            //从0 开始一直到2^n 分别每一位与1 进行与运算 然后添加到子集中
           /* for (int i = 0; i < 1 << nums.length; i++) {
                //清空子集
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < nums.length; j++) {
                    if ((i & (1 << j)) != 0) {
                        list.add(nums[j]);
                    }
                }
                res.add(list);
            }
            return res;*/


            //递归类似前序遍历

        }
        public void dfs(int n, int[] nums){
            if (n == nums.length ){
                ans.add(new ArrayList<Integer>(t));
                return;
            }
            t.add(nums[n]);
            dfs(n + 1, nums);
            t.remove(t.size() - 1);
            dfs(n + 1, nums);

        }

    }




//leetcode submit region end(Prohibit modification and deletion)

}