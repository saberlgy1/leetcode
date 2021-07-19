//元素的 频数 是该元素在一个数组中出现的次数。
//
// 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
//
// 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,4], k = 5
//输出：3
//解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
//4 是数组中最高频元素，频数是 3 。
//
// 示例 2：
//
//
//输入：nums = [1,4,8,13], k = 5
//输出：2
//解释：存在多种最优解决方案：
//- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
//- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
//- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
//
//
// 示例 3：
//
//
//输入：nums = [3,9,6], k = 2
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 105
// 1 <= nums[i] <= 105
// 1 <= k <= 105
//
// Related Topics 数组 二分查找 前缀和 滑动窗口
// 👍 64 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class FrequencyOfTheMostFrequentElement {
    public static void main(String[] args) {
        Solution solution = new FrequencyOfTheMostFrequentElement().new Solution();
        int[] nums = new int[]{9930, 9923, 9983, 9997, 9934, 9952, 9945, 9914, 9985, 9982, 9970, 9932, 9985, 9902, 9975, 9990, 9922, 9990, 9994, 9937, 9996, 9964, 9943, 9963, 9911, 9925, 9935, 9945, 9933, 9916, 9930, 9938, 10000, 9916, 9911, 9959, 9957, 9907, 9913, 9916, 9993, 9930, 9975, 9924, 9988, 9923, 9910, 9925, 9977, 9981, 9927, 9930, 9927, 9925, 9923, 9904, 9928, 9928, 9986, 9903, 9985, 9954, 9938, 9911, 9952, 9974, 9926, 9920, 9972, 9983, 9973, 9917, 9995, 9973, 9977, 9947, 9936, 9975, 9954, 9932, 9964, 9972, 9935, 9946, 9966};
        //int[] nums = new int[]{3, 9, 6};
        //int[] nums = new int[]{1, 2, 4};
        //int[] nums = new int[]{9926, 9960, 10000, 9992, 9917, 9986, 9934, 9985, 9977, 9950, 9922, 9913, 9971, 9978, 9984, 9959, 9934, 9948, 9918, 9916, 9967, 9965, 9985, 9977, 9988, 9983, 9900, 9945, 9913, 9966, 9968, 9986, 9939, 9914, 9980, 9957, 9921, 9927, 9917, 9972, 9974, 9953, 9984, 9912, 9975, 9920, 9966, 9932, 9921, 9904, 9928, 9959, 9993, 9937, 9934, 9974, 9937, 9964, 9922, 9963, 9991, 9930, 9944, 9930, 9982, 9980, 9967, 9904, 9955, 9947, 9924, 9973, 9997, 9950, 9905, 9924, 9990, 9947, 9953, 9924, 9977, 9938, 9951, 9982, 9932, 9926, 9928, 9912, 9917, 9929, 9924, 9921, 9987, 9910, 9927, 9921, 9929, 9937, 9919, 9995, 9949, 9953};
        System.out.println(solution.maxFrequency(nums, 3056));
        //System.out.println(solution.maxFrequency(nums, 5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：排序+暴力扫描

        /**
         * 可以思考一个问题就是我们操作变化的元素会不会超过数组的最大元素
         * 如果我们变大了最大元素，且最大元素是最高频次元素，那其余操作都要因此增加，因此我们可以假定我们不需要变大最大元素
         * 这样就确立了一个二分范围，第一个元素的元素和 和所有元素的元素和
         * 先用暴力法看一下，不走二分，直接全部遍历一次，通过前缀和+k与元素值* 元素数量做比较, res表示当前元素
         * sum[i]+k >=sum[i-res-1] (res+1)*nums[i-1]
         * 满足则取元素数量
         * 然后递增res知道res=i即可
         * 最后返回res
         */
       /* public int maxFrequency(int[] nums, int k) {
            Arrays.sort(nums);
            int n = nums.length;
            int res = 1;
            int[] sums = new int[n + 1];
            sums[1] = nums[0];
            for (int i = 2; i <= n; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
            //i表示i个元素
            for (int i = 1; i <= n; i++) {
                //至少能保证res+1个元素满足才更新
                while (res < i && sums[i] + k >= sums[i - res - 1] + (res + 1) * nums[i - 1]) {
                    res++;
                }
            }
            return res;
        }*/

        /**
         * 思路二：思路一的优化
         * while循环可以用二分做判断上下边界是 当前res 和i
         */
        int[] nums, sum;
        int n, k;
        public int maxFrequency(int[] _nums, int _k) {
            nums = _nums;
            k = _k;
            n = nums.length;
            Arrays.sort(nums);
            sum = new int[n + 1];
            for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + nums[i - 1];
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (check(mid)) l = mid;
                else r = mid - 1;
            }
            return r;
        }
        boolean check(int len) {
            for (int l = 0; l + len - 1 < n; l++) {
                int r = l + len - 1;
                int cur = sum[r + 1] - sum[l];
                int t = nums[r] * len;
                if (t - cur <= k) return true;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}