//给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。 
//
// 子数组 是数组的一段连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,1,0,1], goal = 2
//输出：4
//解释：
//如下面黑体所示，有 4 个满足题目要求的子数组：
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0,0,0], goal = 0
//输出：15
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// nums[i] 不是 0 就是 1 
// 0 <= goal <= nums.length 
// 
// Related Topics 数组 哈希表 前缀和 滑动窗口 
// 👍 140 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        Solution solution = new BinarySubarraysWithSum().new Solution();
        int[] nums = new int[]{1, 0, 1, 0, 1};
        System.out.println(solution.numSubarraysWithSum(nums, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法+前缀和
        //求出每个前n项的和，sum0 -> sum(n-1)
        //每个子数组的和（temp(i,j) = sum(j) - sum(i-1)）
        //判断target
        //嘿嘿 毫无疑问TLE了
        /*public int numSubarraysWithSum(int[] nums, int goal) {
            int n = nums.length, res = 0;
            //从1开始防止0号元素判定
            int[] sum = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
            //两次for循环，求出所有子数组的和
            for (int i = 1; i < n + 1; i++) {
                for (int j = i; j < n + 1; j++) {
                    if (sum[j] - sum[i - 1] == goal) {
                        res += 1;
                    }
                }
            }
            return res;
        }*/
        //思路二：优化思路一
        //当前值如果小于goal，r指针可以往右移，
        //当前值如果大于goal，l指针要先右移到temp<goal，才能继续r指针右移
        //我把题还想复杂了，元素不是1就是0

        /*public int numSubarraysWithSum(int[] nums, int goal) {
            int n = nums.length, res = 0;
            //从1开始防止0号元素判定
            int[] sum = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
            //两个指针
            int l = 1, r = 1;
            while (l < n && r < n) {
                int temp = sum[r] - sum[l - 1];
                while (temp < goal && r < n + 1) {
                    r++;
                    if (r < n+1){
                        temp = sum[r] - sum[l - 1];
                    }
                }
                if (r == n+1){
                    r--;
                }
                //r右移
                while (temp == goal && r < n + 1) {
                    res++;
                    r++;
                    if (r < n + 1) {
                        temp = sum[r] - sum[l - 1];
                    }
                }
                //新的l和r指针
                l++;
                r = l;
            }

            return res + (nums[n - 1] == goal ? 1 : 0);
        }*/
        //思路三：思路一的又一次优化-容斥原理
        //通过map记录r指针的前缀和
        //容斥原理，我们需要找到sum[r]-sum[l-1]=goal
        //扫描nums 对于当前元素和sum[r]我们应该找到所有满足起条件内的sum[r] - goal的前缀和
        /*public int numSubarraysWithSum(int[] nums, int goal) {
            int n = nums.length, res = 0;
            //从1开始防止0号元素判定
            int[] sum = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
            Map<Integer, Integer> map = new HashMap<>();
            //第0号元素为0
            map.put(0, 1);
            for (int i = 0; i < n; i++) {
                int r = sum[i + 1], l = r - goal;
                res += map.getOrDefault(l, 0);
                map.put(r, map.getOrDefault(r, 0) + 1);
            }
            return res;
        }*/
        //思路四：思路二的优化
        //思路二求前缀和其实是没意义的
        //因为本题严格意义上是单调递增的
        //从1开始防止0号元素判定
        //三个个指针,左边两个指针定义一个区间，
        //表示这个区间内所有元素到r位置之间的所有元素的和为goal
        //通过s1 记录 l1-r区间的元素和
        //s2记录 l2-r区间的元素和
        //遍历nums数组移动r指针，每次向右移动l1 l2 保证区间有解
        //两个一个负责将l1 固定在第一个和为goal的点
        //另一个负责将l2固定在最后一个和为goal的点
        /*public int numSubarraysWithSum(int[] nums, int goal) {
            int n = nums.length, res = 0;
            int l1 = 0, r = 0, l2 = 0, s1 = 0, s2 = 0;
            for (; r < n; r++) {
                s1 += nums[r];
                s2 += nums[r];
                //左边第一个指针移动到第一个元素和等于goal的元素
                while (l1 <= r && s1 > goal) {
                    s1 -= nums[l1];
                    l1++;
                }
                //从l1开始遍历，减少遍历次数
                while (l2 <= r && s2 >= goal) {
                    s2 -= nums[l2];
                    l2++;
                }
                res =res +  l2 - l1;
            }
            return res;
        }*/
        //思路五：思路四的改造,这个应该是思路一双指针的改写又回到了n^2
        //由此可见思路四，是通过单调性来记录两个状态达到了时间复杂的优化
        public int numSubarraysWithSum(int[] nums, int goal) {
            int n = nums.length, res = 0;
            int r = 0, sum = 0;
            for (; r < n; r++) {
                sum += nums[r];
                int l1 = 0, l2 = 0;
                int s1 = sum;
                int s2 = sum;
                //左边第一个指针移动到第一个元素和等于goal的元素
                while (l1 <= r && s1 > goal) {
                    s1 -= nums[l1];
                    l1++;
                }
                //从l1开始遍历，减少遍历次数
                while (l2 <= r && s2 >= goal) {
                    s2 -= nums[l2];
                    l2++;
                }
                res = res + l2 - l1;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}