//给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 star
//ti 到 endi 的 闭区间 。
//
// 如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
//
// 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了
//。
//
//
//
// 示例 1：
//
//
//输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
//输出：true
//解释：2 到 5 的每个整数都被覆盖了：
//- 2 被第一个区间覆盖。
//- 3 和 4 被第二个区间覆盖。
//- 5 被第三个区间覆盖。
//
//
// 示例 2：
//
//
//输入：ranges = [[1,10],[10,20]], left = 21, right = 21
//输出：false
//解释：21 没有被任何一个区间覆盖。
//
//
//
//
// 提示：
//
//
// 1 <= ranges.length <= 50
// 1 <= starti <= endi <= 50
// 1 <= left <= right <= 50
//
// Related Topics 数组 哈希表 前缀和
// 👍 32 👎 0

package com.cute.leetcode.editor.cn;

public class CheckIfAllTheIntegersInARangeAreCovered {
    public static void main(String[] args) {
        Solution solution = new CheckIfAllTheIntegersInARangeAreCovered().new Solution();
        int[][] ranges = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        System.out.println(solution.isCovered(ranges, 2, 5));
        System.out.println(3&(-3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力法
        //遍历每个left right之间的元素
        //每找到一个则往下走，知道遍历完区间内元素
        /*public boolean isCovered(int[][] ranges, int left, int right) {
            for (int i = left; i <= right; i++) {
                boolean isValid = false;
                for (int[] range: ranges
                     ) {
                    if (i>=range[0] && i<=range[1]){
                        isValid = true;
                        break;
                    }
                }
                if (!isValid){
                    return false;
                }
            }
            return true;
        }*/
        //差分数组
        //学会了一个新的算法还是很有意思的
        //官方题解写的有点蠢的，或者说写的太天书了根本看不懂
        //【老干妈是万能的】这个大佬的讲解就清晰的多
        //按我的理解里拆分数组的算法计算过程如下
        //定义差分数组，对于每一个区间，区间开始的元素对应+1
        //区间结束元素的下一个元素对应-1
        //然后最后差分数组前缀和>0的即为区间内元素
        //外面的即不在当前区间
        //因为题目限制了元素大小，所以diff区间还是很好做的
        /*public boolean isCovered(int[][] ranges, int left, int right) {
            int[] diff = new int[52];
            for (int[] range : ranges
            ) {
                diff[range[0]]++;
                diff[range[1] + 1]--;
            }
            int[] sum = new int[52];
            for (int i = 1; i < 52; i++) {
               sum[i] = sum[i-1] + diff[i];
            }
            for (int i = left; i <= right; i++) {
                if (sum[i] <= 0) {
                    return false;
                }
            }
            return true;
        }*/
        int n = 55;
        int[] tr = new int[n];
        int lowbit(int x) {
            return x & -x;
        }
        void add(int x, int u) {
            for (int i = x; i <= n; i += lowbit(i)) tr[i] += u;
        }
        int query(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= lowbit(i)) ans += tr[i];
            return ans;
        }
        public boolean isCovered(int[][] rs, int l, int r) {
            for (int[] cur : rs) {
                int a = cur[0], b = cur[1];
                for (int i = a; i <= b; i++) {
                    add(i, 1);
                }
            }
            for (int i = l; i <= r; i++) {
                int cnt = query(i) - query(i - 1);
                if (cnt == 0) return false;
            }
            return true;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}