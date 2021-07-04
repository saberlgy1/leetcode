//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有
//一个数字重复 。
//
// 给定一个数组 nums 代表了集合 S 发生错误后的结果。
//
// 请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,2,4]
//输出：[2,3]
//
//
// 示例 2：
//
//
//输入：nums = [1,1]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 104
// 1 <= nums[i] <= 104
//
// Related Topics 位运算 数组 哈希表 排序
// 👍 189 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class SetMismatch {
    public static void main(String[] args) {
        Solution solution = new SetMismatch().new Solution();
        System.out.println(solution.findErrorNums(new int[]{2, 3, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：hash存储
       /* public int[] findErrorNums(int[] nums) {
            int[] temp = new int[nums.length + 1];
            int index = 0, res = 0;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                if (set.contains(nums[i])) {
                    index = nums[i];
                }
                set.add(nums[i]);
                temp[nums[i]] = nums[i];
            }
            for (int i = 1; i < temp.length; i++) {
                if (temp[i] == 0) {
                    res = i;
                }
            }
            return new int[]{index, res};
        }*/
        //思路二：减少set存储
        /*public int[] findErrorNums(int[] nums) {
            int[] temp = new int[nums.length + 1];
            int index = 0, res = 0;
            for (int i = 0; i < nums.length; i++) {
                temp[nums[i]]++;
            }
            for (int i = 1; i < temp.length; i++) {
                if (temp[i] == 0) {
                    res = i;
                }
                if (temp[i] == 2) {
                    index = i;
                }
            }
            return new int[]{index, res};
        }*/
        //思路三：数学
        //正常没有重复元素总和为等差数列求和total
        //通过数组记录重复元素
        //所有总和-非重复元素和为重复元素
        //目标和-非重复元素和为丢失元素
        /*public int[] findErrorNums(int[] nums) {
            int n = nums.length;
            int total = (1 + n) * n / 2;
            int[] temp = new int[n + 1];
            int sum = 0, set = 0;
            for (int x : nums) {
                sum += x;
                if (temp[x] == 0) set += x;
                temp[x] = 1;
            }
            return new int[]{sum-set, total-set};
        }*/
        //思路四：桶排序
        //将对应元素i放入nums[i-1]
        //然后遍历一次，找到非对应元素
        //可以节省存储空间
        public int[] findErrorNums(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                //一直循环直到找到对应元素
                while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, i, nums[i] - 1);
                }
            }
            int a = -1, b = -1;
            for (int i = 0; i < n; i++) {
                if (nums[i] != i + 1) {
                    a = nums[i];
                    b = i == 0 ? 1 : nums[i - 1] + 1;
                }
            }
            return new int[]{a, b};
        }

        private void swap(int[] nums, int l, int r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}