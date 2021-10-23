//请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。 
//
// 实现词典类 WordDictionary ： 
//
// 
// WordDictionary() 初始化词典对象 
// void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配 
// bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 
//'.' ，每个 . 都可以表示任何一个字母。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["WordDictionary","addWord","addWord","addWord","search","search","search",
//"search"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//输出：
//[null,null,null,null,false,true,true,true]
//
//解释：
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
// 
//
// 
//
// 提示： 
//
// 
// 1 <= word.length <= 500 
// addWord 中的 word 由小写英文字母组成 
// search 中的 word 由 '.' 或小写英文字母组成 
// 最多调用 50000 次 addWord 和 search 
// 
// Related Topics 深度优先搜索 设计 字典树 字符串 👍 364 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class DesignAddAndSearchWordsDataStructure {
    public static void main(String[] args) {
    }
    //leetcode submit region begin(Prohibit modification and deletion)
static class WordDictionary {

        static int[][] word = new int[250000][26];
        static boolean[] isWord = new boolean[250000];
        static int idx;
        public WordDictionary() {
            for(int[] w: word){
                Arrays.fill(w, 0);
            }
            Arrays.fill(isWord, false);
            idx = 0;
        }

        public void addWord(String s) {
            int p = 0;
            for (int i = 0; i < s.length();i++){
                int val = s.charAt(i) - 'a';
                if(word[p][val] == 0) word[p][val] = ++idx;
                p = word[p][val];
            }
            isWord[p] = true;
        }

        public boolean search(String word) {
            return dfs(word,0,0);
        }
        public boolean dfs(String s,int trIdx, int sIdx){
            int n = s.length();
            if (n == sIdx) return isWord[trIdx];
            char c = s.charAt(sIdx);
            if (c == '.') {
                for (int j = 0; j < 26; j++) {
                    if (word[trIdx][j] != 0 && dfs(s, word[trIdx][j], sIdx + 1)) return true;
                }
                return false;
            } else {
                int u = c - 'a';
                if (word[trIdx][u] == 0) return false;
                return dfs(s, word[trIdx][u], sIdx + 1);
            }
        }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

}