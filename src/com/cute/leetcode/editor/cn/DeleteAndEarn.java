//给你一个整数数组 nums ，你可以对它进行一些操作。 
//
// 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] +
// 1 的元素。 
//
// 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,2]
//输出：6
//解释：
//删除 4 获得 4 个点数，因此 3 也被删除。
//之后，删除 2 获得 2 个点数。总共获得 6 个点数。
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,3,3,3,4]
//输出：9
//解释：
//删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
//之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
//总共获得 9 个点数。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// 1 <= nums[i] <= 104 
// 
// Related Topics 动态规划 
// 👍 294 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class DeleteAndEarn {
    public static void main(String[] args) {
        Solution solution = new DeleteAndEarn().new Solution();
        int[] nums = new int[]{2, 2, 3, 3, 3, 4};
        System.out.println(solution.deleteAndEarn(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //题意分析：
        //最开始看到题想到两种思路，一种是贪心法，一种是动态规划
        //这种题贪心法如果可以解，那dp也一定可以所以可以先考虑dp
        //相关题目链接 leetcode-198 打家劫舍
        //思路一：动态规划
        //因为包含相同元素，所以当取得x元素后，相当于取得了所有相同x元素，并删除了所有x-1 以及 x+1元素
        //维护一个数组，存入所有元素的数量*元素值存入
        //dp[i]=max(dp[i−2]+nums[i],dp[i−1])
        //因为是一维，所以可以不用定义数组，用两个元素带你dp方程求之
        //题目保证正整数
       /* public int deleteAndEarn(int[] nums) {
            //找出最大元素,确定维护数组边界大小
            int max = 0;
            for (int n : nums
            ) {
                max = Math.max(max, n);
            }
            int[] sum = new int[max + 1];
            for (int n : nums
            ) {
                sum[n] += n;
            }
            //动态规划取相隔元素
            int first = sum[0], second = Math.max(sum[0], sum[1]);
            for (int i = 2; i < max + 1; i++) {
                int temp = second;
                second = Math.max(first + sum[i], second);
                first = temp;
            }

            return second;


        }*/
        //时间复杂度O(n+n+max)
        //思路二： 排序 + 动态规划
        //优先排序，然后动态规划
        //排序后得到一个有大小关系的数组
        //取连续相距不超过1的子数组，然后对每个子数组参照思路1dp后聚合
        //时间复杂度O(nlgn)
        public int deleteAndEarn(int[] nums) {
            int n = nums.length;
            int ans = 0;
            Arrays.sort(nums);
            List<Integer> sum = new ArrayList<Integer>();
            sum.add(nums[0]);
            int size = 1;
            for (int i = 1; i < n; ++i) {
                int val = nums[i];
                //想等元素，存入链表获得最后值
                if (val == nums[i - 1]) {
                    sum.set(size - 1, sum.get(size - 1) + val);
                }
                //距离上一个元素差值为1，则加入链表进行聚合
                else if (val == nums[i - 1] + 1) {
                    sum.add(val);
                    ++size;
                }
                //取得所有相邻区间，如果不在连续区间内，新建一个子链表循环操作
                else {
                    ans += rob(sum);
                    sum.clear();
                    sum.add(val);
                    size = 1;
                }
            }
            ans += rob(sum);
            return ans;
        }


    }

    public int rob(List<Integer> nums) {
        int size = nums.size();
        if (size == 1) {
            return nums.get(0);
        }
        int first = nums.get(0), second = Math.max(nums.get(0), nums.get(1));
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(first + nums.get(i), second);
            first = temp;
        }
        return second;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}