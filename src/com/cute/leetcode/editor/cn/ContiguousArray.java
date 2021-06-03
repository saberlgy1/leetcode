//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
//
//
//
// 示例 1:
//
//
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
//
// 示例 2:
//
//
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 105
// nums[i] 不是 0 就是 1
//
// Related Topics 哈希表
// 👍 293 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public static void main(String[] args) {
        Solution solution = new ContiguousArray().new Solution();
        int[] nums =new int[]{0,1};
        System.out.println(solution.findMaxLength(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法（这种方法没什么意义，遍历所有子数组）
        //思路二：hash表
        //由于数组中只有0 1 两个元素，当子元素长度为n（n为正偶数）时，子数组和为（n/2）
        //所以可以把0换成-1 这样 子数组和即为0；
        //题意转化成了求连续子数组和为0的最大长度子数组
        //最近做的两道题都有这种思想，通过前缀和求连续区间
        //也就是说连续子数组长度则可以通过前缀和的差值来判断
        //首先定义一个prefix[]数组 prefix[i] = nums[0] +……+ nums[i]
        //nums[i] +……+ nums[j] = prefix[j] - prefix[i-1]
        //如果只是这样计算每一个子数组的和其实和暴力法没有区别甚至是多了O(n)的时间复杂度
        //可以根据题目含义得出，目标是求出prefix[j] - prefix[i]== 0的时候 j 与 i的最大差值
        //也就是说需要求得使prefix[j]与prefix[i]相等情况j 与 i差值最大
        //因此可以考虑引入hashmap进行计算，将prefix[i]的值作为key存入hashmap并记录当前坐标为value
        //每次求出前缀和的时候判断求Math.max(j-i)
        public int findMaxLength(int[] nums) {
            int n = nums.length,max = 0;
            Map<Integer,Integer> map = new HashMap<>();
            int[] prefix = new int[n];
            prefix[0] = (nums[0] == 0 ? -1 : 1);
            map.put(prefix[0],0);
            //求前缀和数组
            for (int i = 1; i < n; i++) {
                prefix[i] = prefix[i - 1] + (nums[i] == 0 ? -1 : 1);
                //当前前缀和已经满足，则进入计算范围
                if (prefix[i] == 0){
                    max = Math.max(i+1,max);
                }
                if (map.containsKey(prefix[i])){
                    max = Math.max(i-map.get(prefix[i]),max);
                }else{
                    map.put(prefix[i], i);
                }
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}