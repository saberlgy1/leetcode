//可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
//
// 'A'：Absent，缺勤
// 'L'：Late，迟到
// 'P'：Present，到场
//
//
// 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
//
//
// 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
// 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
//
//
// 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7
//取余 的结果。

// 
//
// 示例 1：
//
//
//输入：n = 2
//输出：8
//解释：
//有 8 种长度为 2 的记录将被视为可奖励：
//"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：3
//
//
// 示例 3：
//
//
//输入：n = 10101
//输出：183236316
//
//
//
//
// 提示：
//
//
// 1 <= n <= 105
//
// Related Topics 动态规划
// 👍 144 👎 0

package com.cute.leetcode.editor.cn;

public class StudentAttendanceRecordIi {
    public static void main(String[] args) {
        Solution solution = new StudentAttendanceRecordIi().new Solution();
        System.out.println(solution.checkRecord(10101));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
//        思路一：动态规划
//        这预感是明天的每日一题
//        easy直接到hard也是狠
//        正常递归思路是每一个元素三选一dfs(i)
//        知道i达到n||当前排列不满足题目要求的时候时退出递归
//        时间复杂度可以很容易的发现必然tle
//        但是由此可以推出dp方程dp[i]
//        表示由 i个元素排列组合满足题目要求的方案数量
//        初始化dp[0] = 0;
//        dp[1] = 3;
//        dp[n]第递推思路可以从如下角度分析
//        选取第n个元素的原则是需要使选取之后的排列满足要求
//        也就是分如下几种情况
//        当前序方案dp[n-1]有A的时候，只可以添加P、L（需要下一个条件判定）
//        当前序方案dp[n-1]末尾是"LL"的时候，只可以添加P
//        其余情况可添加所有元素A、L、P
//        由此可以发现我们需要记录每种方案数的两个指标
//        1、A的数量 2、末尾两位的字符串
//        因此一维的dp[n]无法满足定义
//        需要定义三维dp[i][j][k] 表示使用i个元素长末尾，包含j个A，末尾为k个l的方案数量
//        初始化dp[0][0][0] = 0;
//        dp[1][0][1] = 1
//        dp[1][1][0] = 1
//        dp[1][0][0] = 1
//        递推方程
//        dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][0][1] + dp[i-1][0][2])%mod;
//        dp[i][1][0] = (dp[i-1][1][0] + dp[i-1][1][1] + dp[i-1][1][2])%mod;
//        dp[i][0][1] = (dp[i-1][0][0])%mod;
//        dp[i][0][2] = (dp[i-1][0][1])%mod;
//        dp[i][1][1] = (dp[i-1][1][0])%mod;
//        dp[i][1][2] = (dp[i-1][1][1])%mod;
//        dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2])%mod
        public int checkRecord(int n) {
            long[][][] dp = new long[n + 1][2][3];
            dp[1][0][1] = 1;
            dp[1][1][0] = 1;
            dp[1][0][0] = 1;
            int mod = (int) 1e9 + 7;
            for (int i = 2; i <= n; i++) {
                dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % mod;
                dp[i][1][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % mod;
                dp[i][0][1] = dp[i - 1][0][0];
                dp[i][0][2] = dp[i - 1][0][1];
                dp[i][1][1] = dp[i - 1][1][0];
                dp[i][1][2] = dp[i - 1][1][1];
                dp[i][1][0] += (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % mod;
            }
            return (int)((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % mod);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}