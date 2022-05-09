//给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。 
//
// 美式键盘 中： 
//
// 
// 第一行由字符 'qwertyuiop' 组成。 
// 第二行由字符 'asdfghjkl' 组成。 
// 第三行由字符 'zxcvbnm' 组成。 
// 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ['Hello','Alaska','Dad','Peace']
//输出：['Alaska','Dad']
// 
//
// 示例 2： 
//
// 
//输入：words = ['omk']
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：words = ['adsdf','sfd']
//输出：['adsdf','sfd']
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 20 
// 1 <= words[i].length <= 100 
// words[i] 由英文字母（小写和大写字母）组成 
// 
// Related Topics 数组 哈希表 字符串 👍 158 👎 0

package com.cute.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyboardRow {
    public static void main(String[] args) {
        Solution solution = new KeyboardRow().new Solution();
        String[] words = new String[]{"Hello","Alaska","Dad","Peace"};
        System.out.println(solution.findWords(words));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String[] findWords(String[] words) {
            List<String> list = new ArrayList<>();
            Set<Character> set1 = new HashSet<>();
            Set<Character> set2 = new HashSet<>();
            Set<Character> set3 = new HashSet<>();
            set1.add('q');
            set1.add('w');
            set1.add('e');
            set1.add('r');
            set1.add('t');
            set1.add('y');
            set1.add('u');
            set1.add('i');
            set1.add('o');
            set1.add('p');
            set2.add('a');
            set2.add('s');
            set2.add('d');
            set2.add('f');
            set2.add('g');
            set2.add('h');
            set2.add('j');
            set2.add('k');
            set2.add('l');
            set3.add('z');
            set3.add('x');
            set3.add('c');
            set3.add('v');
            set3.add('b');
            set3.add('n');
            set3.add('m');
            for (String word : words
            ) {
                Set<Character> set = new HashSet<>();
                if (set1.contains(Character.toLowerCase(word.charAt(0)))) {
                    set = set1;
                }
                if (set2.contains(Character.toLowerCase(word.charAt(0)))) {
                    set = set2;
                }
                if (set3.contains(Character.toLowerCase(word.charAt(0)))) {
                    set = set3;
                }
                boolean flag = true;
                for (Character c : word.toCharArray()
                ) {
                    if (!set.contains(Character.toLowerCase(c))) {
                        flag = false;
                    }
                }
                if (flag) {
                    list.add(word);
                }
            }
            return list.toArray(new String[0]);

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}