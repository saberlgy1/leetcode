//最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作： 
//
// 
// Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。 
// Paste（粘贴）：粘贴 上一次 复制的字符。 
// 
//
// 给你一个数字 n ，你需要使用最少的操作次数，在记事本上输出 恰好 n 个 'A' 。返回能够打印出 n 个 'A' 的最少操作次数。 
//
// 
//
// 示例 1： 
//
// 
//输入：3
//输出：3
//解释：
//最初, 只有一个字符 'A'。
//第 1 步, 使用 Copy All 操作。
//第 2 步, 使用 Paste 操作来获得 'AA'。
//第 3 步, 使用 Paste 操作来获得 'AAA'。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1000 
// 
// Related Topics 数学 动态规划 👍 351 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class TwoKeysKeyboard {
    public static void main(String[] args) {
        Solution solution = new TwoKeysKeyboard().new Solution();
        System.out.println(solution.minSteps(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：dfs
        //每一次都是复制当前剪贴板上的字符才会增加字符串
        //然后可以改变当前剪贴板
        //dfs搜索当前剪贴板以及当前字符数量
        //每次改变剪贴板或者复制字符串都算是一次操作
        //初始状态剪贴板无字符串，当前字符串数量为1
        //数据集不超过1000，所以默认最大值1010
        /*int target;
        int INF = 1010;

        public int minSteps(int n) {
            target = n;
            return dfs(1, 0);
        }

        public int dfs(int cur, int paste) {
            //超过目标数量，不满足条件
            if (cur > target) {
                return INF;
            }
            //刚好满足，不需要再调用函数
            if (cur == target) {
                return 0;
            }
            int res = INF;
            if (paste > 0) {
                res = Math.min(res, dfs(cur + paste, paste));
            }
            //还有一种情况是再次复制当前的
            //这种情况需要做剪枝处理，当前一次已经是复制的情况，不需要再做复制操作
            //这样就可以防止死循环
            if (cur != paste){
                res = Math.min(res, dfs(cur, cur));

            }
            return res + 1;
        }*/
        //思路二：dfs+记忆化
        //观测上述dfs可以发现使用dfs优化
        //可变参数有两个
        //一个是当前字符串数量，一个是当前剪贴板
        /*int target;
        int INF = 1010;
        int[][] cnt = new int[1010][1010];
        public int minSteps(int n) {
            target = n;
            return dfs(1, 0);
        }

        public int dfs(int cur, int paste) {
            //超过目标数量，不满足条件
            if (cur > target) {
                return INF;
            }
            //刚好满足，不需要再调用函数
            if (cur == target) {
                return 0;
            }
            if (cnt[cur][paste]!=0){
                return cnt[cur][paste];
            }
            int res = INF;
            if (paste > 0) {
                res = Math.min(res, dfs(cur + paste, paste));
            }
            //还有一种情况是再次复制当前的
            //这种情况需要做剪枝处理，当前一次已经是复制的情况，不需要再做复制操作
            //这样就可以防止死循环
            if (cur != paste){
                res = Math.min(res, dfs(cur, cur));
            }
            cnt[cur][cur] = Math.min(res+1,cnt[cur][cur]);
            return res + 1;
        }*/
        //思路三：dp
        //都已经记忆化+dfs了必然可以使用dp
        //定义dp[i][j]分别记录上述记忆化递归的两个变量
        //表述当前i个字符串，剪贴板上为j个字符串的最少操作数量
        public int minSteps(int n) {
            int[][] dp = new int[n + 1][n + 1];
            int INF = 1010;

            for (int[] d:dp
                 ) {
                Arrays.fill(d,INF);
            }
            //初始化状态
            dp[1][0] = 0;
            dp[1][1] = 1;
            for (int i = 2; i <= n; i++) {
                int min = INF;
                //如果是粘贴操作，不会超过一半元素所以可以遍历到i/2
                for (int j = 0; j <= i / 2; j++) {
                    dp[i][j] = dp[i - j][j] + 1;
                    min = Math.min(min,dp[i][j]);
                }
                //如果是复制操作
                dp[i][i] = min+1;
            }
            int res =INF;
            for (int d:dp[n]
                 ) {
                res = Math.min(d,res);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}