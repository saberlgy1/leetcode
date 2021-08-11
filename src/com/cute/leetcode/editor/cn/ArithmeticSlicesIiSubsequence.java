//给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
//
// 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
//
//
// 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
// 再例如，[1, 1, 2, 5, 7] 不是等差序列。
//
//
// 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
//
//
// 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
//
//
// 题目数据保证答案是一个 32-bit 整数。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,4,6,8,10]
//输出：7
//解释：所有的等差子序列为：
//[2,4,6]
//[4,6,8]
//[6,8,10]
//[2,4,6,8]
//[4,6,8,10]
//[2,4,6,8,10]
//[2,6,10]
//
//
// 示例 2：
//
//
//输入：nums = [7,7,7,7,7]
//输出：16
//解释：数组中的任意子序列都是等差子序列。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 1000
// -231 <= nums[i] <= 231 - 1
//
// Related Topics 数组 动态规划
// 👍 157 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class ArithmeticSlicesIiSubsequence {
    public static void main(String[] args) {
        Solution solution = new ArithmeticSlicesIiSubsequence().new Solution();
        int[] nums = new int[]{2, 4, 6, 8, 10};
        System.out.println(solution.numberOfArithmeticSlices(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力
        //先排序，保证递增序列
        //先确定两个指针，然后判断第三个指针
        //前两个指针确定公差dis
        //第三个指针负责将公差为dis的元素加入子序列
        //没有考虑重复计算
        //这种三指针会重复计算
        //wuwuwuwu失败了 没有想到如何去重
        /*思路二：序列dp
        按照三叶大佬的思路来看
        我们可以先简单的使用一维方程f[i]来表示使用nums[i]为等差子序列终点的数两
        然后观察是否满足dp的严谨性，如果不严谨，则增加其他维度
        验证严谨性：
        f[i]按照当前状态来定义
        dp移动的时候可以很容易的得出等差子序列的公差为nums[i]-nums[j]
        可以发现这个时候公差是固定的值，而本题要求的是所有等差子序列，而不是固定公差的等差子序列
        所以增加维度f[i][j]
        表示以j为公差，nums[i]为结尾的等差子序列的数量
        求f[i][j]的移动方程
        对于所有0->i区间内节点j
        d = nums[i] - nums[j]
        我们可以想到f[i][d] = \sum_{j=0}^{i-1}f[j][d] + 1
        可以表示为 j 从0开始到i-1结束，所有与j为等差子数列，且公差为d的数量的和 再加1（加上当前nums[i]）
        举个例子吧，[2, 4, 6, 8]，到6这里，公差为2的序列有俩,[2,4,6]和[4,6]，那么到8这里，就能有[2,4,6,8],[4,6,8]和[6,8]
         6可以很很多组2，4构成序列
        但是多的只有6。8
        从上述例子也可以看出是没有考虑子数组长度小于3的请客（后续处理）
        长度为 22 的等差子序列，由于没有第三个数的差值限制，因此任意的数对 (j, i)(j,i) 都是一个合法的长度为 22 的等差子序列。

        而求长度为 nn 的数组的所有数对，其实就是求 首项为 00，末项为 n - 1n−1，公差为 11，长度为 nn 的等差数列之和，
         直接使用「等差数列求和」公式求解即可。*/
        public int numberOfArithmeticSlices(int[] nums) {
            List<Map<Long, Integer>> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                Map<Long, Integer> map = new HashMap<>();
                for (int j = 0; j < i; j++) {
                    long dis = nums[i]*1L- nums [j];
                    Map<Long, Integer> prev = list.get(j);
                    int cnt = map.getOrDefault(dis,0);
                    cnt += prev.getOrDefault(dis,0);
                    cnt++;
                    map.put(dis,cnt);
                }
                list.add(map);
            }
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                Map<Long, Integer> cur = list.get(i);
                for (Long key : cur.keySet()) {
                    ans += cur.get(key);
                }
            }
            int a1 = 0, an = nums.length - 1;
            int cnt = (a1 + an) * nums.length / 2;
            return ans - cnt;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}