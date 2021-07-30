//字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列： 
//
// 
// 序列中第一个单词是 beginWord 。 
// 序列中最后一个单词是 endWord 。 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典 wordList 中的单词。 
// 
//
// 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 的 最短转换序列 中
//的 单词数目 。如果不存在这样的转换序列，返回 0。 
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
// Related Topics 广度优先搜索 哈希表 字符串 
// 👍 781 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        Solution solution = new WordLadder().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //        //思路一：双向BFS
//        //定义两个个队列广度优先扫描，每一次都遍历所有队列元素，当遍历到相同元素时，返回计数次数
//        //通过map存入改变次数
        Set<String> set = new HashSet<>();

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            for (String word : wordList
            ) {
                set.add(word);
            }
            if (!set.contains(endWord)) {
                return 0;
            }
            int ans = bfs(beginWord, endWord);
            return ans == -1 ? 0 : ans + 1;
        }

        private int bfs(String begin, String end) {
            Map<String, Integer> m1 = new HashMap<>();
            Map<String, Integer> m2 = new HashMap<>();
            Deque<String> d1 = new ArrayDeque<>();
            Deque<String> d2 = new ArrayDeque<>();
            m1.put(begin, 0);
            d1.add(begin);
            d2.add(end);
            m2.put(end, 0);
            while (!d1.isEmpty() && !d2.isEmpty()) {
                //优先拓展较短队列
                int ans = -1;
                if (d1.size() <= d2.size()) {
                    ans = update(d1, m1, m2);
                } else {
                    ans = update(d2, m2, m1);
                }
                if (ans != -1) return ans;
            }
            return -1;
        }

        private int update(Deque<String> q1, Map<String, Integer> m1, Map<String, Integer> m2) {
            String temp = q1.poll();
            int n = temp.length();
            // 枚举替换原字符串的哪个字符 i
            for (int i = 0; i < n; i++) {
                // 枚举将 i 替换成哪个小写字母
                for (int j = 0; j < 26; j++) {
                    // 替换后的字符串
                    String sub = temp.substring(0, i) + String.valueOf((char) ('a' + j)) + temp.substring(i + 1);
                    if (set.contains(sub)) {
                        // 如果该字符串在「当前方向」被记录过（拓展过），跳过即可
                        if (m1.containsKey(sub)) continue;

                        // 如果该字符串在「另一方向」出现过，说明找到了联通两个方向的最短路
                        if (m2.containsKey(sub)) {
                            return m1.get(temp) + 1 + m2.get(sub);
                        } else {
                            // 否则加入 deque 队列
                            q1.addLast(sub);
                            m1.put(sub, m1.get(temp) + 1);
                        }
                    }
                }
            }
            return -1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}