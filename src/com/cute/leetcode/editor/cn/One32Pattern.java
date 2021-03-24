//给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < a
//j。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。 
//
// 注意：n 的值小于15000。 
//
// 示例1: 
//
// 
//输入: [1, 2, 3, 4]
//
//输出: False
//
//解释: 序列中不存在132模式的子序列。
// 
//
// 示例 2: 
//
// 
//输入: [3, 1, 4, 2]
//
//输出: True
//
//解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
// 
//
// 示例 3: 
//
// 
//输入: [-1, 3, 2, 0]
//
//输出: True
//
//解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
// 
// Related Topics 栈 
// 👍 311 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class One32Pattern {
    public static void main(String[] args) {
        Solution solution = new One32Pattern().new Solution();
        int[] nums = new int[]{3, 5, 0, 3, 4};
        System.out.println(solution.find132pattern(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean find132pattern(int[] nums) {
            int n = nums.length;
            //corner case
            if (n < 3) {
                return false;
            }
            //预设最小值
            int leftmin = nums[0];
            //132
            //方案1  通过确定中间最大元素，找到左右12元素判断 time limit exceeded
           /* for (int i = 1; i < n - 1; i++) {
                TreeSet<Integer> right = new TreeSet<>();
                for (int j = i + 1; j < n; j++) {
                    right.add(nums[j]);
                }
                for (int j = 0; j < i; j++) {
                    leftmin = Math.min(leftmin, nums[j]);
                }
                if (nums[i] > leftmin && right.ceiling(leftmin + 1) != null && right.ceiling(leftmin + 1) < nums[i]) {
                    return true;
                }
            }*/
            //优化1 treeset 优化使用treemap记录坐标 一次赋值，找到该值的最有坐标，比middle 坐标大即可
            //T O(n + n * n) = O(n*n) S O(n)
/*            TreeMap<Integer,Integer> right = new TreeMap<>();
            for (int i = 2; i < n; i++) {
                right.put(nums[i], i);
            }
            for (int i = 1; i < n - 1; i++) {
                for (int j = 0; j < i; j++) {
                    leftmin = Math.min(leftmin, nums[j]);
                }
                Integer next = right.ceilingKey(leftmin + 1);
                if (nums[i] > leftmin && next != null && next < nums[i]&& right.get(next) > i) {
                    return true;
                }
            }*/
            //优化2 leetcode解法 没看明白为啥
            //tips 看懂了 每右移一次中间最大元素，则右边相邻位置的2号元素出现次数为0，则不需要进行在比较了，也就是说如果当前2好元素出现次数为0，则ceiling元素的时候完全可以不考虑2号，
            // 减少了存入treemap的空间与时间，所以相当于做了一部分的优化
/*            TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

            for (int k = 2; k < n; ++k) {
                rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
            }

            for (int j = 1; j < n - 1; ++j) {
                if (leftmin < nums[j]) {
                    Integer next = rightAll.ceilingKey(leftmin + 1);
                    if (next != null && next < nums[j]) {
                        return true;
                    }
                }
                leftmin = Math.min(leftmin, nums[j]);
                rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
                if (rightAll.get(nums[j + 1]) == 0) {
                    rightAll.remove(nums[j + 1]);
                }
            }*/
            //优化3 单调栈 枚举2从右至左遍历
            //栈内由底至上依次递减
/*            Stack<Integer> stack = new Stack<>();
            //入栈最后元素
            stack.push(nums[n - 1]);
            int maxK = Integer.MIN_VALUE;
            for (int i = n - 2; i >= 0; i--) {
                if (nums[i] < maxK) {
                    return true;
                }
                while (!stack.isEmpty() && nums[i] > stack.peek()) {
                    maxK = stack.pop();
                }
                //优化4 如果当前元素比max小，则没有必要入栈，入栈也不会影响max的值
                if (nums[i]> maxK){
                    stack.push(nums[i]);
                }
            }*/
            //优化4 单调栈，枚举2
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}