//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
//
// 示例 1:
//
//
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是:
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
//
//
// 注意:
//
//
// 数组长度不超过1000。
// 数组里整数的范围为 [0, 1000]。
//
// Related Topics 贪心 数组 双指针 二分查找 排序
// 👍 215 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class ValidTriangleNumber {
    public static void main(String[] args) {
        Solution solution = new ValidTriangleNumber().new Solution();
        int[] nums = new int[]{48, 66, 61, 46, 94, 75};
        System.out.println(solution.triangleNumber(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力扫描双指针+二分查找
        //优先排序数组，然后根据前两个值在右侧做二分查找到满足的最大元素
        //即a[i] + a[j] > a[mid]
        //mid - j 即为满足的的数量
/*        public int triangleNumber(int[] nums) {
            int res = 0;
            Arrays.sort(nums);
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int l = j + 1, r = nums.length - 1, sum = nums[i] + nums[j];
                    int edge = check(nums, l, r, sum);
                    if (edge > 0) {
                        res = res + edge - j;
                    }

                }
            }
            return res;
        }

        private int check(int[] nums, int l, int r, int sum) {
            //不存在满足的边
            if (l == nums.length || nums[l] >= sum) {
                return -1;
            }
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (nums[mid] < sum) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return nums[r] == sum ? r - 1 : r;
        }*/
        //思路二：排序+双指针的枚举遍历
        //对于 nums[i] + nums[j]> nums[k];
        //我们可以发现当固定i扫描的时候
        //j k存在不严谨正相关关系
        //也就是说随着j增大，k不变或增大
        public int triangleNumber(int[] nums) {
            int n = nums.length;
            Arrays.sort(nums);
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i - 1, k = 0; k < j; j--) {
                    while (k < j && nums[k] + nums[j] <= nums[i]) {
                        k++;
                    }
                    res += j - k;
                }
            }
            return res;
        }
    }


//leetcode submit region end(Prohibit modification and deletion)

}