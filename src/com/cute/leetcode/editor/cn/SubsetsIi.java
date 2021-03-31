//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//] 
// Related Topics 数组 回溯算法 
// 👍 390 👎 0

package com.cute.leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.*;

public class SubsetsIi {
    public static void main(String[] args) {
        Solution solution = new SubsetsIi().new Solution();
        int[] nums  = new int[]{1,2,2,2,2};
        solution.subsetsWithDup(nums);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public List<List<Integer>> subsetsWithDup(int[] nums) {
            /*//参照第一题使用set做存储，然后转list 有问题 4，4，1和 4，1，4 应该属于相同子集，所以数组先排序
            //ps:  这个方法是真的蠢
            //优化：去重转换为 如果有相同子集，则代表将除了首个连续的与运算为1的子集留下后，
            // 其余所有相同子集，除了首位的与运算结果为1 的位置前一位都是与运算为0的结果
            // 这些子集不加入结果集则达到了去重的目的，省略了set去重的部分，减少了n的运算

            //M1：位运算
            *//*Set<List<Integer>> set = new HashSet<>();
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < 1 << nums.length; i++) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < nums.length; j++) {
                    if ((i & (1 << j)) != 0) {
                        list.add(nums[j]);
                    }
                }
                set.add(list);

            }
            res.addAll(set);

            return res;*//*
            //M2：前序遍历 深度优先 回溯算法
            Arrays.sort(nums);
            dfs( nums,0, new ArrayList<>(),res);

            return res;*/
            //每日一题重新做
            //corner case
            if (nums == null){
                return  new ArrayList<List<Integer>>();
            }
            //防止重复子集所以先排序
            //Arrays.sort(nums);
            //生成所有子集扔到set里面，然后转成list
            //没啥意义，第一个想到的还是这个方法，毫无进步,
            //全集个数（i << nums.length）
/*            Set<List<Integer>> set = new HashSet<>();
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0 ; i < (1 << nums.length); i++){
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < nums.length; j++) {
                    if ((i & (1 << j)) != 0) {
                        list.add(nums[j]);
                    }
                }
                set.add(list);
            }
            res.addAll(set);
            return res;*/
            //优化1 使用dfs去重，并无大的优化
/*            Arrays.sort(nums);
            Set<List<Integer>> ans = new HashSet<>();
            List<Integer> cur = new ArrayList<>();
            dfs(nums, 0, cur, ans);
            return new ArrayList<>(ans);*/
        //优化2 dfs内部回溯

            Arrays.sort(nums);
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> cur = new ArrayList<>();
            dfs(nums, 0, cur, ans);
            return new ArrayList<>(ans);




        }
        private void dfs(int[] nums, int u, List<Integer> cur, List<List<Integer>> ans) {
            // 所有位置都决策完成，将当前方案放入结果集
            int n = nums.length;
            if (n == u) {
                ans.add(new ArrayList<>(cur));
                return;
            }

            // 记录当前位置是什么数值（令数值为 t），并找出数值为 t 的连续一段
            int t = nums[u];
            int last = u;
            while (last < n && nums[last] == nums[u]) {
                last++;
            }

            // 不选当前位置的元素，直接跳到 last 往下决策
            dfs(nums, last, cur, ans);

            // 决策选择不同个数的 t 的情况：选择 1 个、2 个、3 个 ... k 个
            for (int i = u; i < last; i++) {
                cur.add(nums[i]);
                dfs(nums, last, cur, ans);
            }

            // 回溯对数值 t 的选择
            for (int i = u; i < last; i++) {
                cur.remove(cur.size() - 1);
            }
        }

/*        private void dfs(int[] nums, int u, List<Integer> cur, Set<List<Integer>> ans) {
            // 所有位置都决策完成，将当前方案放入结果集
            if (nums.length == u) {
                ans.add(new ArrayList<>(cur));
                return;
            }

            // 选择当前位置的元素，往下决策
            cur.add(nums[u]);
            dfs(nums, u + 1, cur, ans);

            // 不选当前位置的元素（回溯），往下决策
            cur.remove(cur.size() - 1);
            dfs(nums, u + 1, cur, ans);
        }*/

        /* public void dfs(int n, int[] nums) {
             if (n == nums.length) {
                 List<Integer> t = new ArrayList<>(list);
                 set.add(t);
                 return;
             }
             list.add(nums[n]);
             dfs(n + 1, nums);
             list.remove(list.size() - 1);
             dfs(n + 1, nums);
         }*/
        //使用dfs内部去重，优化set转list的时间
//        public void dfs(int[] nums, int start, ArrayList<Integer> temp, List<List<Integer>> ans) {
//            ans.add(new ArrayList<>(temp));
//            for (int i = start; i < nums.length; i++) {
//                //和上个数字相等就跳过
//                if (i > start && nums[i] == nums[i - 1]) {
//                    continue;
//                }
//                temp.add(nums[i]);
//                dfs(nums, i + 1, temp, ans);
//                //自动更新temp保证从头开始的temp为空
//                temp.remove(temp.size() - 1);
//            }
//
//        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}