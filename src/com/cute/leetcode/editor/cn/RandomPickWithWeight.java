//给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 
//i 的概率与 w[i] 成正比。 
//
// 
// 
//
// 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 +
// 3) = 0.75（即，75%）。 
//
// 也就是说，选取下标 i 的概率为 w[i] / sum(w) 。 
//
// 
//
// 示例 1： 
//
// 输入：
//["Solution","pickIndex"]
//[[[1]],[]]
//输出：
//[null,0]
//解释：
//Solution solution = new Solution([1]);
//solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。 
//
// 示例 2： 
//
// 输入：
//["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
//[[[1,3]],[],[],[],[],[]]
//输出：
//[null,1,1,1,1,0]
//解释：
//Solution solution = new Solution([1, 3]);
//solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
//solution.pickIndex(); // 返回 1
//solution.pickIndex(); // 返回 1
//solution.pickIndex(); // 返回 1
//solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
//
//由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
//[null,1,1,1,1,0]
//[null,1,1,1,1,1]
//[null,1,1,1,0,0]
//[null,1,1,1,0,1]
//[null,1,0,1,0,0]
//......
//诸若此类。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= w.length <= 10000 
// 1 <= w[i] <= 10^5 
// pickIndex 将被调用不超过 10000 次 
// 
// Related Topics 数学 二分查找 前缀和 随机化 👍 128 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomPickWithWeight {
    public static void main(String[] args) {
        Solution solution = new RandomPickWithWeight().new Solution(new int[]{1, 3, 1});
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //思路一：统计概率
    //固定区间，生成随机数，然后二分查找区间
    //这道题出得题意太模糊了
    //整体难的反而是题目的理解
    //看了题解以后发现，本题需要得出来一个基本符合概率分布的结果集
    //通过前缀和可以得到每个索引代表的范围
    //由此可以得出pickIndex函数的意义是为了获取每次落在不同范围内的索引值
    //索引范围0->n-1
    //通过random随机数获取一个重量值，二分查找到其对应的范围
    //然后返回索引位置即可
    //因为数量范围不大，暴力搜索也可以，二分查找不需要考虑二分范围越界问题
    class Solution {
        int[] sum;
        Random random = new Random();

        public Solution(int[] w) {
            sum = w;
            for (int i = 1; i < sum.length; i++) {
                sum[i] += sum[i - 1];
            }
        }

        public int pickIndex() {
            int i = random.nextInt(sum[sum.length - 1]) + 1;
            return binarySearch(i);
        }

        public int binarySearch(int i) {
            int l = 0, r = sum.length - 1;
            while (l < r) {
                int mid = l + r  >> 1;
                if (sum[mid] >= i) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        }
    }


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
//leetcode submit region end(Prohibit modification and deletion)

}