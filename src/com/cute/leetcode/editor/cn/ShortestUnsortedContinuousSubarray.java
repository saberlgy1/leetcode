//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,4]
//输出：0
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 104
// -105 <= nums[i] <= 105
//
//
//
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
//
//
// Related Topics 栈 贪心 数组 双指针 排序 单调栈
// 👍 597 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        Solution solution = new ShortestUnsortedContinuousSubarray().new Solution();
        int[] nums = new int[]{1, 3, 2, 2, 2};
        //int[] nums = new int[]{2,3,3,2,4};
        //int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        //int[] nums = new int[]{1, 2, 4, 5, 3};
        System.out.println(solution.findUnsortedSubarray(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：排序加双指针
        //最简单的肯定是排序后双针针前后分别扫描到不同元素
        /*public int findUnsortedSubarray(int[] nums) {
            int[] copy = new int[nums.length];
            for (int i = 0; i < nums.length;i++){
                copy[i] = nums[i];
            }
            int i = 0, j = nums.length - 1;
            Arrays.sort(nums);
            for (; i < nums.length; i++) {
                if (copy[i] != nums[i]) {
                    break;
                }
            }
            for (; j >= i; j--) {
                if (copy[j] != nums[j]) {
                    break;
                }
            }
            return j-i+1;
        }*/

//        思路二：双指针
//        跑了几个用例，重复元素很影响计算
//        对于下面求解出来的两个区间分别进行两个边界的重新划分
//        定义两个边界外数字，作为最小元素和最大元素
//        防止ij区间内有部分元素为最小或最大元素移动到边界
//        遍历区间内所有元素，当找到比nums[i]小的时候需要一直回溯i左侧的元素
//        如果比开始元素都小，则将i移动到头部指针，并更新min为边界外最小值
//        对j执行相反操作
//        当i==j的时候得到无需交换
//        i!=j  i的定义为最后不被移动的左边元素
//        j的定义为最后不被移动的右边元素
//        两者直接需要移动的元素数量为j-1 - (i+1) +1;
        public int findUnsortedSubarray(int[] nums) {
            int MIN = -10005, MAX = 100005;
            //corner case
            if (nums.length == 1) {
                return 0;
            }
            int n = nums.length, i = 0, j = n - 1;
            //找到两个以开始元素为起始和以结束元素为结尾的递增区间
            while (i < j && nums[i] <= nums[i + 1]) {
                i++;
            }

            while (j > i && nums[j] >= nums[j - 1]) {
                j--;
            }
            int l = i, r = j, min = nums[i], max = nums[j];
            for (int k = l; k <= r; k++) {
                if (nums[k] < min) {
                    while (i >= 0 && nums[i] > nums[k]) {
                        i--;
                    }
                    min = i >= 0 ? nums[i] : MIN;
                }
                if (nums[k] > max) {
                    while (j < nums.length && nums[j] < nums[k]) {
                        j++;
                    }
                    max = j < n ? nums[j] : MAX;
                }
            }
            return j == i ? 0 : (j - 1) - (i + 1) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}