//给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
//
// 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
//
//
//
// 示例 1：
//
//
//输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//输出："apple"
//
//
// 示例 2：
//
//
//输入：s = "abpcplea", dictionary = ["a","b","c"]
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// 1 <= dictionary.length <= 1000
// 1 <= dictionary[i].length <= 1000
// s 和 dictionary[i] 仅由小写英文字母组成
//
// Related Topics 数组 双指针 字符串 排序 👍 204 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LongestWordInDictionaryThroughDeleting {
    public static void main(String[] args) {
        Solution solution = new LongestWordInDictionaryThroughDeleting().new Solution();
        String[] str = new String[]{"word", "good", "best", "good"};
        System.out.println(solution.findLongestWord("wordgoodgoodgoodbestword", Arrays.asList(str)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*public String findLongestWord(String s, List<String> dictionary) {
            String res = "";
            int n = s.length();
            for (String tar : dictionary) {
                int l = 0, r = 0;
                while (l < n && r < tar.length()) {
                    if (s.charAt(l) == tar.charAt(r)) {
                        l++;
                        r++;
                    } else {
                        l++;
                    }
                }
                if (r == tar.length()) {
                    if (res.length() < tar.length()) {
                        res = tar;
                    } else if (res.length() == tar.length()) {
                        res = compare(tar, res) ? tar : res;
                    }
                }
            }
            return res;
        }

        boolean compare(String s1, String s2) {
            for (int i = 0; i < s1.length() && i < s2.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return s1.charAt(i) < s2.charAt(i);
                }
            }
            return s1.length() < s2.length();
        }*/
        public String findLongestWord(String s, List<String> dictionary) {
            int n = s.length();
            Collections.sort(dictionary, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (o1.length() != o2.length()) {
                        return o2.length() - o1.length();
                    }
                    for (int i = 0; i < o1.length(); i++) {
                        if (o1.charAt(i) != o2.charAt(i)) {
                            return o1.charAt(i) - o2.charAt(i);
                        }
                    }
                    return 0;
                }
            });
            for (String tar : dictionary) {
                int l = 0, r = 0;
                while (l < n && r < tar.length()) {
                    if (s.charAt(l) == tar.charAt(r)) {
                        l++;
                        r++;
                    } else {
                        l++;
                    }
                }
                if (r == tar.length()) {
                    return tar;
                }
            }
            return "";
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}