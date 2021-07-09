//数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。请设计时间复杂度为 O(N) 、空间复杂度为 O(1
//) 的解决方案。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,5,9,5,9,5,5,5]
//输出：5 
//
// 示例 2： 
//
// 
//输入：[3,2]
//输出：-1 
//
// 示例 3： 
//
// 
//输入：[2,2,1,1,1,2,2]
//输出：2 
// Related Topics 数组 计数 
// 👍 100 👎 0

package com.cute.leetcode.editor.cn;

public class FindMajorityElementLcci {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3};
        Solution solution = new FindMajorityElementLcci().new Solution();
        System.out.println(solution.majorityElement(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //通过map存储每个元素的信息，然后通过value 判断数组中占比超过一半的元素元素即可
        //时间复杂度O(n) 空间复杂度O(1)
        //这种方法就不写了，太简单了
        //思路二：Boyer-Moore投票算法
        //这也是我第一次听说这个算法
        //整体思路类似于随机确立一个候选元素
        //每当遍历一个元素与当前元素相同则计数器count+1，不同则count-1
        //当count=0的时候遍历下一个元素，将候选元素变为下一个元素，重复上述过程
        //证明原理，因为只要元素的定义是超过数组一般元素，所以这种做法一定会抵消其余元素，剩余的元素可能是主要元素
        //需要再次扫描数组，确认主要元素的数量是否超过数组的一半
        public int majorityElement(int[] nums) {
            int count = 1, master = nums[0], n = nums.length;
            if (n == 1) {
                return master;
            }
            for (int i = 1; i < n; i++) {
                if (count == 0) {
                    master = nums[i];
                }
                if (nums[i] == master) {
                    count++;
                } else {
                    count--;
                }
            }
            count = 0;
            for (int num : nums
            ) {
                if (num == master) {
                    count++;
                }
            }
            return count >= (n + 1) / 2 ? master : -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}