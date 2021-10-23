//给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：[3] 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：[1,1,1,3,3,2,2,2]
//输出：[1,2] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// 
//
// 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。 
// Related Topics 数组 哈希表 计数 排序 👍 508 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementIi {
    public static void main(String[] args) {
        Solution solution = new MajorityElementIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> majorityElement(int[] nums) {
            List<Integer> res = new ArrayList<>();
            int k = nums.length / 3;
            int a = 0, b = 0;
            int c1 = 0, c2 = 0;
            for (int i : nums) {
                if (c1 != 0 && a == i) {
                    c1++;
                } else if (c2 != 0 && b == i) {
                    c2++;
                } else if (c1 == 0 && ++c1 >= 0) a = i;
                else if (c2 == 0 && ++c2 >= 0) b = i;
                else {
                    c1--;
                    c2--;
                }
            }
            c1 = 0;
            c2 = 0;
            for (int i : nums) {
                if (a == i) c1++;
                else if (b == i) c2++;
            }
            if (c1 > k) res.add(a);
            if (c2 > k) res.add(b);

            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}