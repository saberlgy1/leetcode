//猜数字游戏的规则如下： 
//
// 
// 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。 
// 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。 
// 
//
// 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）： 
//
// 
// -1：我选出的数字比你猜的数字小 pick < num 
// 1：我选出的数字比你猜的数字大 pick > num 
// 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num 
// 
//
// 返回我选出的数字。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10, pick = 6
//输出：6
// 
//
// 示例 2： 
//
// 
//输入：n = 1, pick = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：n = 2, pick = 1
//输出：1
// 
//
// 示例 4： 
//
// 
//输入：n = 2, pick = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 231 - 1 
// 1 <= pick <= n 
// 
// Related Topics 二分查找 
// 👍 132 👎 0

package com.cute.leetcode.editor.cn;

public class GuessNumberHigherOrLower {

    private static int num = 6;

    public static void main(String[] args) {
        Solution solution = new GuessNumberHigherOrLower().new Solution();
        System.out.println(solution.guessNumber(10));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Forward declaration of guess API.
     *
     *
     * @return -1 if num is lower than the guess number
     * 1 if num is higher than the guess number
     * otherwise return 0
     * int guess(int num);
     */

    public class Solution extends GuessGame {
        //思路一：暴力
        //思路二：二分查找
        public int guessNumber(int n) {
            int l = 1, r = n;
            while (l < r) {
                int mid = (r - l) / 2 + l;
                if (guess(mid) < 0) {
                    r = mid;
                } else if (guess(mid) > 0) {
                    l = mid + 1;
                } else {
                    return mid;
                }
            }
            return l;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    static class GuessGame {
        public static int guess(int n) {
            if (n < num) {
                return 1;
            }
            if (n > num) {
                return -1;
            }
            return 0;

        }
    }

}