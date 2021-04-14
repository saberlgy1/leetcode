//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。
//
// 请你实现 Trie 类：
//
//
// Trie() 初始化前缀树对象。
// void insert(String word) 向前缀树中插入字符串 word 。
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
// 。
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。
//
//
//
//
// 示例：
//
//
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
//
//
//
//
// 提示：
//
//
// 1 <= word.length, prefix.length <= 2000
// word 和 prefix 仅由小写英文字母组成
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
//
// Related Topics 设计 字典树
// 👍 620 👎 0

package com.cute.leetcode.editor.cn;

import com.sun.deploy.util.StringUtils;

public class ImplementTriePrefixTree {
    public static void main(String[] args) {
        Trie trie = new ImplementTriePrefixTree().new Trie();
        trie.insert("apple");
        System.out.println(trie.startsWith("app"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Trie {

        //26个字母
        private Trie[] children  ;
        //有没有到此结束的单词节点
        private boolean isEnd ;

        private String s;

        public  Trie() {
            children = new Trie[26];
            isEnd = false;
            s= "";
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();
            this.children[chars[0] - 'a'] = insertBackTrack(this.children[chars[0] - 'a'], word);
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();
            //word.length已经排除corner case了
            return searchBackTrack(this.children[chars[0] - 'a'], word);

        }
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            return startsWithBackTrack(this.children[chars[0] - 'a'], prefix);
        }

        private Trie insertBackTrack(Trie trie, String word) {
            if (trie == null){
                trie = new Trie();
            }
            if ("".equals(word)) {
                trie.isEnd = true;
                return trie;
            }
            trie.s = word.substring(0,1);
            if (word.length() == 1){
                trie.isEnd = true;
                return trie;
            }
            Trie temp =insertBackTrack(trie.children[(int) word.charAt(1) - 97], word.substring(1));
            trie.children[(int) word.charAt(1) - 97] = temp;
            return trie;
        }

        private boolean searchBackTrack(Trie trie, String word) {
            if (trie == null) {
                return false;
            }
            if (word.length() == 1) {
                return trie.isEnd && trie.s.equals(word);
            }
            return searchBackTrack(trie.children[(int) word.charAt(1) - 97], word.substring(1));
        }

        private boolean startsWithBackTrack(Trie trie, String prefix) {
            if (trie == null) {
                return false;
            }
            if (prefix.length() == 1) {
                return trie.s.equals(prefix);
            }
            return startsWithBackTrack(trie.children[(int) prefix.charAt(1) - 97], prefix.substring(1));
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}