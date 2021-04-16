//使用下面描述的算法可以扰乱字符串 s 得到字符串 t ：
// 
// 如果字符串的长度为 1 ，算法停止 
// 如果字符串的长度 > 1 ，执行下述步骤：
// 
// 在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 s ，则可以将其分成两个子字符串 x 和 y ，且满足 s = x + y 。 
// 随机 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，s 可能是 s = x + y 或者 s = y + x
// 。 
// 在 x 和 y 这两个子字符串上继续从步骤 1 开始递归执行此算法。 
// 
// 
// 
//
// 给你两个 长度相等 的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。如果是，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "great", s2 = "rgeat"
//输出：true
//解释：s1 上可能发生的一种情形是：
//"great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
//"gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
//"gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
//"g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
//"r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
//"r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
//算法终止，结果字符串和 s2 相同，都是 "rgeat"
//这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
// 
//
// 示例 2： 
//
// 
//输入：s1 = "abcde", s2 = "caebd"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：s1 = "a", s2 = "a"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// s1.length == s2.length 
// 1 <= s1.length <= 30 
// s1 和 s2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 236 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class ScrambleString {
    public static void main(String[] args) {
        Solution solution = new ScrambleString().new Solution();
        System.out.println(solution.isScramble("great", "rgeat"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[][][] temp;
        String s1, s2;

        public boolean isScramble(String s1, String s2) {
            //corner case
            if (s1.length() != s2.length()) {
                return false;
            }
            this.s1 = s1;
            this.s2 = s2;
           /* //dp[l1][r1][l2][r2] 表示 s1 从l1 -> r1 可以变化为 s2 l2->r2 又因为r1-l1 == r2 - l2 所以降维处理
            //dp[l][r][length] 表示 s1 从l开始长度为length 和s2 从r开始长度为length 是否为变换字符串
            //长度 从1 - 》 n n+1的长度容量三位长度分别为 n-1 n-1 n
            boolean[][][] matrix = new boolean[s1.length()][s1.length()][s1.length() + 1];
            //dp
            //1、左右不交换 ls1 ls2 满足true rs1 rs2 满足true
            //2、左右交换过 ls1 rs2 满足true ls2 rs1 满足true
            //递归分割 backTrack
            //length 从0 开始涨到length  l和r分别从0开始一直到length
            //
            //不分配 f(s1,s2,i) = f(s1.subString(0,i),s2.subString(0,i)) &&  f(s1.subString(i),s2.subString(i))
            //对换位置 f(s1,s2,i) = f(s1.subString(0,i),s2.subString(i)) &&  f(s1.subString(i),s2.subString(0,i))
            //初始化所有长度为1的分割
            for (int i = 0; i < s1.length(); i++) {
                for (int j = 0; j < s1.length(); j++) {
                    matrix[i][j][1] = s1.charAt(i) == s2.charAt(j);
                }
            }
            //长度2-n的所有情况
            //我好像没理解错 我就是没想到真的四层for循环0 0有点离谱
            for (int length = 2; length <= s1.length(); length++) {
                for (int i = 0; i <= s1.length() - length; i++) {
                    for (int j = 0; j <= s1.length() - length; j++) {
                        for (int k = 1; k <= length - 1; k++) {
                            //第一种不变换
                            if (matrix[i][j][k] && matrix[i + k][j + k][length - k]) {
                                matrix[i][j][length] = true;
                                break;
                            }
                            //第二种变换
                            if (matrix[i][j + length - k][k] && matrix[i + k][j][length - k]) {
                                matrix[i][j][length] = true;
                                break;
                            }
                        }
                    }
                }
            }
            //
            return matrix[0][0][s1.length()];*/
            //第二种方法：跟我最开始想到的递归属于同一种思路：从i开始做左右对比，以及相对对比，递归处理，然后通过枚举长度的方式进行迭代比较
            //按照我的理解其实就是n*n*n*n但是我属实是没想到真的这么做，所以写一半没写下去
            //同样的降维思路
            temp = new int[s1.length()][s2.length()][s1.length() + 1];
            //递归调用dp
            return dp(0, 0, s1.length());
        }

        public boolean dp(int l, int r, int len) {
            //初始化数组
            if (temp[l][r][len] != 0) {
                return temp[l][r][len] == 1;
            }
            //相等情况
            if (s1.substring(l, l + len).equals(s2.substring(r, r + len))) {
                temp[l][r][len] = 1;
                return true;
            }
            //检查数量
            if (!checkCount(l, r, len)) {
                temp[l][r][len] = -1;
                return false;
            }
            for (int i = 1; i < len; i++) {
                if (dp(l, r, i) && dp(l + i, r + i, len - i)) {
                    temp[l][r][len] = 1;
                    return true;
                }
                if (dp(l, r + len - i, i) && dp(l + i, r, len - i)) {
                    temp[l][r][len] = 1;
                    return true;
                }
            }
            temp[l][r][len] = -1;
            return false;
        }

        private boolean checkCount(int l, int r, int len) {
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = l; i < l + len; i++) {
                map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
            }
            for (int i = r; i < r + len; i++) {
                if (!map.containsKey(s2.charAt(i))) {
                    return false;
                }
                map.put(s2.charAt(i), map.getOrDefault(s2.charAt(i), 0) - 1);
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                int value = entry.getValue();
                if (value != 0) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}