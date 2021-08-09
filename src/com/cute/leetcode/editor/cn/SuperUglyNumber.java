//超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
//
// 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
//
// 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
//
//
//
// 示例 1：
//
//
//输入：n = 12, primes = [2,7,13,19]
//输出：32
//解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,
//28,32] 。
//
// 示例 2：
//
//
//输入：n = 1, primes = [2,3,5]
//输出：1
//解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
//
//
//
//
//
//
// 提示：
//
//
// 1 <= n <= 106
// 1 <= primes.length <= 100
// 2 <= primes[i] <= 1000
// 题目数据 保证 primes[i] 是一个质数
// primes 中的所有值都 互不相同 ，且按 递增顺序 排列
//
//
//
//
// Related Topics 数组 哈希表 数学 动态规划 堆（优先队列）
// 👍 188 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class SuperUglyNumber {
    public static void main(String[] args) {
        Solution solution = new SuperUglyNumber().new Solution();
        int[] nums = new int[]{2, 7, 13, 19};
        System.out.println(solution.nthSuperUglyNumber(12, nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力扫描
        //扫描递增数组
        //TreeSet + 优先队列还是很好考虑到的
        //但是如何维护递增属实是我没想到的，后来一想，原来如此简单
        //通过n计数
        //set去重
        //优先队列

        /*public int nthSuperUglyNumber(int n, int[] primes) {
            Set<Long> set = new HashSet<>();
            PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
            priorityQueue.add(1L);
            set.add(1L);
            while (n-- > 0) {
                long val = priorityQueue.poll();
                if (n == 0){
                    return (int)val;
                }
                for (int num: primes
                     ) {
                    if (!set.contains(num*val)){
                        set.add(num*val);
                        priorityQueue.add(num*val);
                    }
                }

            }
            return -1;
        }*/
        //思路二：多路归并
        //虽然能想到每一个丑数都是由上一个计算而来
        //但是通过三元组来定义，寻找最小值还是很难想到的
        //三叶大佬yyds
        //优先队列做堆倒是很容易想到，我最开始也是卡在去重这里
        //val ：为当前列表指针指向具体值；
        //i ：代表这是由 primes[i] 构造出来的有序序列；
        //idx：代表丑数下标，存在关系 val = ans[idx] * primes[i]。
        //每一次因为单调增的原因所以一定可以得到正确顺序的丑数
        public int nthSuperUglyNumber(int n, int[] primes) {
            int m = primes.length;
            PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            for (int i = 0; i < m; i++) {
                q.add(new int[]{primes[i], i, 0});
            }
            int[] ans = new int[n];
            //初始化第一个一定是1
            ans[0] = 1;
            for (int j = 1; j < n;) {
                int[] temp = q.poll();
                int val = temp[0], i = temp[1], idx = temp[2];
                //维护单调递增
                if (val != ans[j - 1]) ans[j++] = val;
                //这个步骤可以重复写ans数组保证丑数相邻关系
                q.add(new int[]{ans[idx + 1] * primes[i], i, idx + 1});
            }
            return ans[n - 1];
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}