//给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
//
// 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始
//）。
//
// 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
//
// 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
//
// |x| 定义为：
//
//
// 如果 x >= 0 ，值为 x ，或者
// 如果 x <= 0 ，值为 -x
//
//
//
//
// 示例 1：
//
//
//输入：nums1 = [1,7,5], nums2 = [2,3,5]
//输出：3
//解释：有两种可能的最优方案：
//- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
//- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
//两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
//
//
// 示例 2：
//
//
//输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
//输出：0
//解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
//
//
// 示例 3：
//
//
//输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
//输出：20
//解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
//绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
//
//
//
//
// 提示：
//
//
// n == nums1.length
// n == nums2.length
// 1 <= n <= 105
// 1 <= nums1[i], nums2[i] <= 105
//
// Related Topics 贪心 数组 二分查找 有序集合
// 👍 52 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MinimumAbsoluteSumDifference {
    public static void main(String[] args) {
        Solution solution = new MinimumAbsoluteSumDifference().new Solution();
        int[] nums1 = new int[]{1, 10, 4, 4, 2, 7};
        int[] nums2 = new int[]{9, 3, 5, 1, 7, 4};
        System.out.println(solution.minAbsoluteSumDiff(nums1, nums2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //求出每个元素差值绝对值，不断更新差值最大值
        //第一次扫描统计差值最大值，并存储每个差值最大值产生的索引位置index，存入set中，如果max值更新，set重新存储
        //第二次扫描统计nums[1]数组元素与nums2[index]的差值最小值，使用cnt临时变量统计最小差值数量，更新res
        //...我还以为要统计交换数量，写完了才发现写的不对哦，原来只是统计一下差值和
        //思路重来：
        //改变后差值绝对值最小 |nums1[i] - nums2[i]| - |nums1[j] - nums2[i]|最大
        //前半部分大小根据i变化，为不变的值，后半部分越小，差值越大，所以可以查找nums1中与nums2[i]最接近的元素
        //TLE
       /* public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
            int sum = 0, index = 0, mod = (int) 1e9 + 7;
            int[] cal = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                int temp = Math.abs(nums1[i] - nums2[i]);
                cal[i] = temp;
                sum = Integer.MAX_VALUE - sum < temp ? sum % mod + temp : sum + temp;
            }
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < nums1.length; i++) {
                for (int num1 : nums1) {
                    max = Math.max(cal[i] - Math.abs(num1 - nums2[i]), max);
                     if (num1 == nums2[i]){
                        break;
                    }
                }
            }
            return (sum + mod - max) % mod;
        }*/
        //思路二：思路一的优化
        //可以通过对nums1 排序 ，二分搜索简化max求值过程
        public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
            int sum = 0, mod = (int) 1e9 + 7;
            int[] cal = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                int temp = Math.abs(nums1[i] - nums2[i]);
                cal[i] = temp;
                sum = Integer.MAX_VALUE - sum < temp ? sum % mod + temp : sum + temp;
            }
            Arrays.sort(nums1);
            int max = 0;
            for (int i = 0; i < nums1.length; i++) {
                if (cal[i] == 0){
                    continue;
                }
                int index = binarySearch(nums1, nums2[i]);
                int dis = Math.abs(nums1[index] - nums2[i]);
                //确认是当前index最接近的值，还是i下一元素为最接近的值
                if (index + 1 < nums1.length) {
                    dis = Math.min(dis, Math.abs(nums1[index + 1] - nums2[i]));
                }
                //确认|nums1[i] - nums2[i]| - |nums1[j] - nums2[i]|最大
                max = Math.max(cal[i] - dis,max );
            }
            //保证 -max不会有负数
            return (sum + mod - max) % mod;
        }

        /**
         * 找到一个元素，当前位置<= target,下一元素为空或者大于target
         *
         * @param nums
         * @param target
         *
         * @return
         */
        public int binarySearch(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            //corner
            if (nums[r] < target) {
                return r;
            }
            while (l < r) {
                int mid = (l - r) / 2 + r;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
            return r;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}