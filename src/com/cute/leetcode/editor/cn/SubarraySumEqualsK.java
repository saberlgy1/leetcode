//给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。 
//
// 示例 1 : 
//
// 
//输入:nums = [1,1,1], k = 2
//输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
// 
//
// 说明 : 
//
// 
// 数组的长度为 [1, 20,000]。 
// 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。 
// 
// Related Topics 数组 哈希表 
// 👍 914 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        Solution solution = new SubarraySumEqualsK().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路1 暴力法 估计会TLE
        //遍历所有求和值
        //O(n* n)
        /*public int subarraySum(int[] nums, int k) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                int sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if (sum == k) {
                        res += 1;
                    }
                }
            }
            return res;
        }*/
        //思路2 map容斥原则
        //通过map存储前缀和，找到右边界到前缀右侧为target的值
        public int subarraySum(int[] nums, int k){
            Map<Integer, Integer> map = new HashMap<>();
            //初始化时，mp.put(0, 1)的功能是，确保nums[0]==k时，会作为一个符合条件的子数组进行计数。如果k=0，这个条件会不会影响结果？答案是不会。举例：[1,-1]，k=0。初始化的(0,1)最后会变成(0,2)，但是这个过程中count只更新了一次，没有影响。这个分析可以继续看下面第2条。
            //先更新符合条件的解的计数，再更新哈希表，否则会重复计数——此时符合条件的值对应的key-value这时重复计算了一次
            int res = 0, n = nums.length, sum = 0;
            map.put(0,1);
            for (int num: nums
                 ) {
                sum += num;
                if (map.containsKey(sum - k)){
                    res += map.get(sum - k);
                }
                map.put(sum , map.getOrDefault(sum,0) + 1);

            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}