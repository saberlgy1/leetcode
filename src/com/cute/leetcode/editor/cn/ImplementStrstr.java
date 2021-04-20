//实现 strStr() 函数。 
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。 
//
// 
//
// 说明： 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。 
//
// 
//
// 示例 1： 
//
// 
//输入：haystack = "hello", needle = "ll"
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
// 
//
// 示例 3： 
//
// 
//输入：haystack = "", needle = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= haystack.length, needle.length <= 5 * 104 
// haystack 和 needle 仅由小写英文字符组成 
// 
// Related Topics 双指针 字符串 
// 👍 796 👎 0

package com.cute.leetcode.editor.cn;

import com.sun.deploy.util.StringUtils;

public class ImplementStrstr {
    public static void main(String[] args) {
        Solution solution = new ImplementStrstr().new Solution();
        System.out.println(solution.strStr("hello","ll"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int strStr(String haystack, String needle) {
            //corner case
            if ("".equals(needle)){
                return 0;
            }
            //本题保证必匹配 所以其他cornercase 可以不做考虑
            int m = haystack.length(), n = needle.length();
            if (m < n) {
                return -1;
            }
            int[] next = new int[n];
            //暴力解法我就不写了
            //KMP算法
            //第一步，定义并求解next数组
            //next数组为匹配模式串的当前位置最长前缀长度
            for (int i = 1, j = 0; i < n; i++) {
                //匹配不成功，则j迭代前移，知道移动到首位字符
                while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                    //回溯到上一次最长前缀长度位置
                    //此时要明确next数组含义，即next[j-1]的意义
                    //next[j - 1]表示 当j指针位置字符和i指针位置字符不相等的时候，应该直接回溯到j-1的最长前缀，
                    //因为这样保证了j-1 长度的最长前缀相等，知道无最长前缀，也就是j回到0的位置，
                    //然后判断当前j指针位置字符和i指针位置字符是否相等，相等j++，next[i] = j
                    j = next[j - 1];
                }
                //相等之后 j+=1
                if (needle.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                //赋值next数组
                next[i] = j;
            }
            for (int i = 0, j = 0; i < m; i++) {
                //不等直接跳过当前前缀字
                while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                    //与上一个for循环类似
                    j = next[j - 1];
                }
                if (haystack.charAt(i) == needle.charAt(j)) {
                    j++;
                }
                if (j == n) {
                    return i - n + 1;
                }
            }

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}