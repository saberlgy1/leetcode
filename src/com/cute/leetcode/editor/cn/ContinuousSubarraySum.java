//给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
//
//
// 子数组大小 至少为 2 ，且
// 子数组元素总和为 k 的倍数。
//
//
// 如果存在，返回 true ；否则，返回 false 。
//
// 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。
//
//
//
// 示例 1：
//
//
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。
//
// 示例 2：
//
//
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
//
//
// 示例 3：
//
//
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 105
// 0 <= nums[i] <= 109
// 0 <= sum(nums[i]) <= 231 - 1
// 1 <= k <= 231 - 1
//
// Related Topics 数学 动态规划
// 👍 256 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    public static void main(String[] args) {
        Solution solution = new ContinuousSubarraySum().new Solution();
        int[] nums = new int[]{5, 0, 0, 0};
        System.out.println(solution.checkSubarraySum(nums, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //题目限制包含0却没有包含负数
        //思路一：递归
        //递归思路TLE
        //时间复杂度 O(n * n)
        //不过可以根据递归得出思路2 动态规划
        /*public boolean checkSubarraySum(int[] nums, int k) {
            //corner case
            if (nums.length == 1) {
                return false;
            }
            return backTrack(nums, 0, k);
        }

        private boolean backTrack(int[] nums, int i, int k) {
            if (i == nums.length - 1) {
                return false;
            }
            int sum = nums[i];
            i += 1;
            int temp = i;
            while (i <= nums.length - 1) {
                sum += nums[i];
                if (sum % k == 0 || sum == 0) {
                    return true;
                }
                i+=1;
            }
            return backTrack(nums,temp,k);
        }*/
        //思路二：动态规划
        //根据思路一的递归可以得到dp方程
        //递归的可变嵌套元素则是动态规划的维度数量
        //由思路1可知可变嵌套元素为起始坐标i 和 前缀和终止坐标j
        //定义dp数组 dp[i][j] 表示 从数组 从i - j的元素和
        //i > 0 && j > 0 时 dp[i][j]=dp[i-1][j] - nums[i-1];
        //i = 0 时 dp[i][j] = dp[i][j-1] + nums[j];
        //j = 0 时 dp[i][j] = 0;
        //然后每一次计算都可以判断一次是否dp[i][j]==nk && j >i 如果相等
        //还是会tle
        //时间复杂度 O(n * n)
        /*public boolean checkSubarraySum(int[] nums, int k) {
            //corner case
            if (nums.length < 2) {
                return false;
            }
            int[][] dp = new int[nums.length][nums.length];
            dp[0][0] = nums[0];
            //初始化一维坐标
            for (int i = 1; i < nums.length; i++) {
                dp[0][i] = dp[0][i - 1] + nums[i];
                if (dp[0][i] % k == 0 || dp[0][i] == 0) {
                    return true;
                }
            }
            for (int i = 1; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    dp[i][j] = dp[i - 1][j] - nums[i - 1];
                    if (dp[i][j] % k == 0 || dp[i][j] == 0) {
                        return true;
                    }
                }
            }
            return false;

        }*/
        //思路三：前缀和+hash+数学
//        前缀和
//        数学这个标签是有用的看来
//        通过前缀和差值来判断子数组的和
//        prefix[i] = nums[0]+…+nums[i]
//        prefix[j] = nums[0]+…+nums[j]
//        当j - i >= 2时 子数组长度>=2
//        a = prefix[i], b = prefix[j]
//        如果满足(b - a)%k ==0 || b - a ==0 则 b - a = nk
//        m1  m2 为除k的余数
//        b = n1 * k + m1, a= n2 * k + m2
//        (n1-n2) * k + (m1 - m2) = nk
//        因为m1 和 m2均为余数 所以 m1 == m2
//        所以当存在两个前缀和prefix[i]，prefix[j]对k取余相等，则代表由满足条件的子数组和
//        因此，通过hashmap即可完成对此的判定
//        map key为余数，value为前缀和索引i，只要满足i , j 差值>=2 即可
//        时间复杂度O(2n)
        public boolean checkSubarraySum(int[] nums, int k) {
            //corner case
            if (nums.length == 1) {
                return false;
            }
            Map<Integer, Integer> map = new HashMap<>();
            map.put(nums[0] % k, 0);
            int[] prefix = new int[nums.length];
            prefix[0] = nums[0];
            //初始化前缀和数组
            for (int i = 1; i < nums.length; i++) {
                prefix[i] = nums[i] + prefix[i - 1];
                //在此即可判断一次
                if (prefix[i] % k == 0 || prefix[i] == 0) {
                    return true;
                }
                int x = prefix[i] % k;
                if (map.containsKey(x) && Math.abs(map.get(x) - i) >= 2) {
                    return true;
                } else {
                    if (map.containsKey(x)){
                        map.put(x, Math.min(i, map.get(x)));
                    }else{
                        map.put(x, i);
                    }
                }
            }
            return false;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}