//给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。 
//
// 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR
// xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。 
//
// 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个
//查询的答案。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
//输出：[3,3,7]
//解释：
//1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
//2) 1 XOR 2 = 3.
//3) 5 XOR 2 = 7.
// 
//
// 示例 2： 
//
// 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
//输出：[15,-1,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length, queries.length <= 105 
// queries[i].length == 2 
// 0 <= nums[j], xi, mi <= 109 
// 
// Related Topics 位运算 字典树 
// 👍 112 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MaximumXorWithAnElementFromArray {
    public static void main(String[] args) {
        Solution solution = new MaximumXorWithAnElementFromArray().new Solution();
        int[][] queries = {{3,1},{1,3},{5,6}};
        int[] nums = new int[]{0,1,2,3,4};
        System.out.println(solution.maximizeXor(nums,queries));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maximizeXor(int[] nums, int[][] queries) {
            //我先暴力法写出来问题不大8
            //map存储一下结果，减少一些运算
            //嘿嘿 果然超时
//            Map<Integer, Integer> map = new HashMap<>();
            /*int[] answers = new int[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int min = -1;
                for (int j = 0; j < nums.length; j++){
                    if (nums[j] <= queries[i][1]){
                        min = Math.max(queries[i][0] ^ nums[j], min);
                    }
                }
                answers[i] =  min;
            }
            return answers;*/
            //字典树实现
            //最大字典数深度
            int L = 9;
            Arrays.sort(nums);
            int numQ = queries.length;
            int[][] newQueries = new int[numQ][3];
            //存储对应节点顺序
            for (int i = 0; i < numQ; ++i) {
                newQueries[i][0] = queries[i][0];
                newQueries[i][1] = queries[i][1];
                newQueries[i][2] = i;
            }
            Arrays.sort(newQueries, new Comparator<int[]>() {
                @Override
                public int compare(int[] query1, int[] query2) {
                    return query1[1] - query2[1];
                }
            });

            int[] ans = new int[numQ];
            Trie trie = new Trie();
            int idx = 0, n = nums.length;
            for (int[] query : newQueries) {
                int x = query[0], m = query[1], qid = query[2];
                while (idx < n && nums[idx] <= m) {
                    trie.insert(nums[idx]);
                    ++idx;
                }
                if (idx == 0) { // 字典树为空
                    ans[qid] = -1;
                } else {
                    ans[qid] = trie.getMaxXor(x);
                }
            }
            return ans;

        }
    }

    //前缀树
    class Trie {
        static final int L = 30;
        Trie[] children = new Trie[2];


        void insert(int val){
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                //判断当前位是否为1 如果为1 则放在1节点里面，如果为0 则放在0节点里面，并且根据是否存在子节点生成字节点
                int bit = (val >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                node = node.children[bit];
            }
        }

        public int getMaxXor(int val) {
            int ans = 0;
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                int bit = (val >> i) & 1;
                if (node.children[bit ^ 1] != null) {
                    ans |= 1 << i;
                    bit ^= 1;
                }
                node = node.children[bit];
            }
            return ans;
        }

    }


//leetcode submit region end(Prohibit modification and deletion)

}