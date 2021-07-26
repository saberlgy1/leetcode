//给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
//
// 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间添加 3 得到 [1,4,3,1,
//2] 。你可以在数组最开始或最后面添加整数。
//
// 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
//
// 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比方说，[2,7,4] 是 [4,2,3,
//7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
//
//
//
// 示例 1：
//
// 输入：target = [5,1,3], arr = [9,4,2,3,4]
//输出：2
//解释：你可以添加 5 和 1 ，使得 arr 变为 [5,9,4,1,2,3,4] ，target 为 arr 的子序列。
//
//
// 示例 2：
//
// 输入：target = [6,4,8,1,3,2], arr = [4,7,6,2,3,8,6,1]
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= target.length, arr.length <= 105
// 1 <= target[i], arr[i] <= 109
// target 不包含任何重复元素。
//
// Related Topics 贪心 数组 哈希表 二分查找
// 👍 43 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class MinimumOperationsToMakeASubsequence {
    public static void main(String[] args) {
        Solution solution = new MinimumOperationsToMakeASubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //思路一：贪心法
        //target-最长公共子序列长度
        //证明过程我属实是不太行
        //不过整体来看本题的实现难度，哪怕知道了这个也不是一道简单题，所以这题还是很有价值的
        //因为都是非重复字符串，所以可以保证子序列相对顺序
        //首先定义map 找到每个元素的索引位置
        //f 动规数组：与朴素 LIS 解法的动规数组含义一致。f[i] 代表以 、nums[i] 为结尾的上升子序列的最大长度；
        //g 贪心数组：g[len] = x 代表上升子序列长度为 len、的上升子序列的「最小结尾元素」为 x
        //二分查找对应最长公共子串长度
        //根据包含目标子串（target）元素的长度进行遍历
        //二分求解，结果为贪心数组取得小于当前子序列结尾的最大索引
        //动态规划数组f[i]即为上述结果的下一个位置
        //我们使用max记录即可
        public int minOperations(int[] target, int[] arr) {
            Map<Integer, Integer> map = new HashMap<>();
            int n = target.length, m = arr.length;
            for (int i = 0; i < n; i++) {
                map.put(target[i],i);
            }
            List<Integer> childList = new ArrayList<>();
            for (int i = 0 ; i < m; i++){
                if (map.containsKey(arr[i])) childList.add(map.get(arr[i]));
            }
            int len = childList.size();
            int[] f = new int[len], g = new int[len + 1];
            Arrays.fill(g,Integer.MAX_VALUE);
            int max = 0;
            for (int i = 0; i < len; i++) {
                int l = 0, r = len;
                while (l < r) {
                    int mid = l + r + 1 >> 1;
                    if (g[mid] < childList.get(i)) l = mid;
                    else r = mid - 1;
                }
                int clen = r + 1;
                f[i] = clen;
                g[clen] = Math.min(g[clen], childList.get(i));
                max = Math.max(max, clen);
            }
            return n-max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}