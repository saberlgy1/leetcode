//给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= 
//t ，同时又满足 abs(i - j) <= k 。 
//
// 如果存在则返回 true，不存在返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1], k = 3, t = 0
//输出：true 
//
// 示例 2： 
//
// 
//输入：nums = [1,0,1,1], k = 1, t = 2
//输出：true 
//
// 示例 3： 
//
// 
//输入：nums = [1,5,9,1,5,9], k = 2, t = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 104 
// 0 <= t <= 231 - 1 
// 
// Related Topics 排序 Ordered Map 
// 👍 349 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Set;
import java.util.TreeSet;

public class ContainsDuplicateIii {
    public static void main(String[] args) {
        Solution solution = new ContainsDuplicateIii().new Solution();
        int[] nums = new int[]{2147483640,2147483641};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums,1,100));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            //两层for循环
            //corner case
            //数字越界问题，依靠顺序查找，先判断nums[index] -t 以及 nums[index] + t 是否越界，然后查找子区间是否有
            //O(nk)超时
            /*for (int i = 0; i < nums.length; i++) {
                if (check(nums, k, t, i)) {
                    return true;
                }
            }
            return false;*/
            //滑动窗口开始了，我最大的噩梦
            // 滑动窗口类似于一种存储数据结构，需要快速存取插入，本题中 可以采用 treeSet
            //本题绝对值，所以可以放弃右侧扫描，只扫描左侧，扫描到最后一个元素和最左侧也是一样的效果
            //时间复杂度 O(nlog(min(n,k)))
            //ceiling 返回大于或等于指定元素的最小元素
            /*TreeSet<Long> set = new TreeSet<>();
            for (int i = 0 ; i < nums.length; i++){
                Long ceiling = set.ceiling((long) nums[i] - (long) t);
                if (ceiling!= null &&ceiling <=(long) nums[i] + (long) t ){
                    return true;
                }
                set.add((long)nums[i]);
                if (i >= k){
                    set.remove((long)nums[i - k]);
                }
            }
            return false;*/
            //TODO 桶排序方法


            return false;

        }

        private boolean check(int[] nums, int k, int t, int index) {
            for (int i = Math.max(index - k, 0); i < Math.min(index + k, nums.length); i++) {
                if (i != index){
                    long min = (long) nums[index] - (long)t;
                    long max = (long)nums[index] + (long)t;
                    if (nums[i]>= min && nums[i] <= max){
                        return true;
                    }
                }
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}