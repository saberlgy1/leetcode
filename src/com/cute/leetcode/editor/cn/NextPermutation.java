//实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须原地修改，只允许使用额外常数空间。 
//
// 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。 
//1,2,3 → 1,3,2 
//3,2,1 → 1,2,3 
//1,1,5 → 1,5,1
//16321 21136
// Related Topics 数组
// 👍 596 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        Solution solution = new NextPermutation().new Solution();
        int[] nums = new int[]{1,5, 1};
        solution.nextPermutation(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void nextPermutation(int[] nums) {
            //corner case
            if (nums.length <= 1) {
                return;
            }
            int len = nums.length;
            int i = len - 1;
            //step 1 从右至左找到第一个元素对，前一个元素小于第二个元素的
            //保证元素降序
            while (i >= 1 && nums[i - 1] >= nums[i]) {
                i--;
            }
            if (i == 0) {
                Arrays.sort(nums);
                return;
            }
            int j = i - 1;
            //从右至左找到第一个大于num[j]的元素
            int temp = len - 1;
            while (temp > j && nums[temp] <= nums[j]) {
                temp--;
            }
            swap(nums, j, temp);
            Arrays.sort(nums, j + 1, len);
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}