//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1702 👎 0

package com.cute.leetcode.editor.cn;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        String[] strs = new String[]{"flower","flow","flight"};
        System.out.println(solution.longestCommonPrefix(strs));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：
        //这道题居然还真的没做过离谱的
        public String longestCommonPrefix(String[] strs) {
            for (int i = 0; i < strs[0].length(); i++) {
                String prefix = strs[0].substring(0, i + 1);
                for (String str : strs
                ) {
                    if (i < str.length() && str.startsWith(prefix)) {
                        continue;
                    } else {
                        return prefix.substring(0, prefix.length() - 1);
                    }
                }
            }
            return strs[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}