//给你一个整数数组 nums 和一个整数 k 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 rig
//ht（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR n
//ums[right] 。 
//
// 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0,3,0], k = 1
//输出：3
//解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
//输出：3
//解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
//输出：3
//解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3] 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 2000 
// 0 <= nums[i] < 210 
// 
// Related Topics 动态规划 
// 👍 55 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MakeTheXorOfAllSegmentsEqualToZero {
    public static void main(String[] args) {
        Solution solution = new MakeTheXorOfAllSegmentsEqualToZero().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minChanges(int[] nums, int k) {
            //首先题解找规律找到破题点
            //第一步发现变换后的规律是一个规律数组
            //每k个元素都是完全相同的子数组
            //所以最后只需要找到每一列都相同，首行异或和为0的最小变动次数
            //需要考虑的特殊情况就是最后一行可能没有k个元素，但是仍旧不需要考虑和不为0的情况
            //第一步 建立map 存储每一列存在的数字的次数，以及存在多少个不同的数字cnt
            //根据限制 max = 2*10 = 1024
            int max = 1024;
            int cnt = 0;
            int[][] f = new int[k][max];
            //记录前i-1次改变的最小次数
            int[] g = new int[nums.length];
            for (int i = 0; i < k; i++) {
                Arrays.fill(f[i], 0x3f3f3f3f);
                g[i] = 0x3f3f3f3f;
            }

            for (int i = 0; i < k; i++, cnt = 0) {
                Map<Integer, Integer> map = new HashMap<>();
                //存入map 统计cnt数量
                for (int j = i; j < nums.length; j += k) {
                    map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                    cnt++;
                }
                if (i == 0) { // 第 0 列：只需要考虑如何将该列变为 xor 即可
                    for (int xor = 0; xor < max; xor++) {
                        f[0][xor] = Math.min(f[0][xor], cnt - map.getOrDefault(xor, 0));
                        g[0] = Math.min(g[0], f[0][xor]);
                    }
                } else { // 其他列：考虑与前面列的关系
                    for (int xor = 0; xor < max; xor++) {
                        //整列替换是最多的情况g[i-1] 表示 之前i-1列变成xor的最少方法次数+cnt
                        f[i][xor] = g[i - 1] + cnt; // 整列替换
                        for (int cur : map.keySet()) { // 部分替换
                            //之前i-1列变成xor ^ cur 然后到 当前列再做 ^ cur 异或变成xor ，最后再减掉 cur的存在次数，即可表示为最少次数

                            f[i][xor] = Math.min(f[i][xor], f[i - 1][xor ^ cur] + cnt - map.get(cur));
                        }
                        g[i] = Math.min(g[i], f[i][xor]);
                    }
                }
            }
            return f[k - 1][0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}