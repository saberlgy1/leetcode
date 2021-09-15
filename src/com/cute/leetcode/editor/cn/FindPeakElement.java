//峰值元素是指其值严格大于左右相邻值的元素。 
//
// 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。 
//
// 你可以假设 nums[-1] = nums[n] = -∞ 。 
//
// 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1]
//输出：2
//解释：3 是峰值元素，你的函数应该返回其索引 2。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,1,3,5,6,4]
//输出：1 或 5 
//解释：你的函数可以返回索引 1，其峰值元素为 2；
//     或者返回索引 5， 其峰值元素为 6。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 对于所有有效的 i 都有 nums[i] != nums[i + 1] 
// 
// Related Topics 数组 二分查找 👍 538 👎 0

package com.cute.leetcode.editor.cn;

public class FindPeakElement {
    public static void main(String[] args) {
        Solution solution = new FindPeakElement().new Solution();
        int[] nums = new int[]{1,2,3,1};
        System.out.println(solution.findPeakElement(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        思路一：二分查找
//        看到lgn时间复杂度第一反应就是二分查找
//        本题需要考虑如何二分查找到峰值元素
//        贪心法的证明可以分为两步
//        第一步：我们是否可以证明数组一定有峰顶元素
//        因为有两个负无穷的边界
//        所以当数组只有一个元素的时候该元素就是封顶元素
//        当数组长度大于1
//        不是一般性的考虑x[i]为左边界元素，如果x[i]>x[i+1]则i是峰顶元素索引
//        如果否则x[i]<x[i+1]那么x[i+1]就是新的左边界元素
//        按照这种逻辑右移i直到遇到一个x[i]>x[i+1]就会找到峰顶元素
//        如果直到i+1到达右边界都没有这种情况，那么x[i+1]一定会大于负无穷的边界，因此最后一个元素是峰顶元素
//        第二步证明二分不会错过峰顶元素
//        当x[i]>x[i-1]那么峰顶元素一定出现在x[i]本身或者右侧
//        按照证明一就很容易理解这个点，因为这样就相当于x[i]<x[i+1]这个情况，右侧是一定会出现峰顶元素的
//        与此相反的如果x[i]>x[i+1]那么一定会现在x[i]本身或者左侧
//        证明思路同理上面
//        接下来就是常规的二分模版了
//        本题也可以发现，我们的二分不是为了有序，而是为了二段性
//        只有每段不同即可使用二分来做
        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;
            if (r == 0) {
                return 0;
            }
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (mid == 0) {
                    if (nums[mid] > nums[mid + 1]) {
                        return 0;
                    } else {
                        l = mid + 1;
                    }
                } else if (mid > 0 && mid < nums.length - 1) {
                    if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                        return mid;
                    }
                    if (nums[mid] > nums[mid - 1]) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                } else {
                    return nums[mid] > nums[mid - 1] ? mid : mid - 1;
                }
            }
            return l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}