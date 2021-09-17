//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f",
//"l","v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 10⁴ 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 数组 字符串 回溯 矩阵 👍 455 👎 0

package com.cute.leetcode.editor.cn;

import java.util.*;

public class WordSearchIi {
    public static void main(String[] args) {
        Solution solution = new WordSearchIi().new Solution();
//        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
//        String[] words = new String[]{"oath", "pea", "eat", "rain"};
//        char[][] board = new char[][]{{'a', 'a'}};
//        String[] words = new String[]{"a"};
        char[][] board = new char[][]{{'a', 'b'},{'c','d'}};
        String[] words = new String[]{"dcab"};

        System.out.println(solution.findWords(board, words));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /*class Trie {
        int[][] trie;
        int[] cnt;
        int idx;

        public Trie() {
            //26个字母
            trie = new int[1000009][26];
            cnt = new int[1000009];
            idx = 0;
        }

        //插入字符串
        public void insert(String s) {
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - 'a';
                //当前为初始字符则初始化
                if (trie[p][num] == 0) {
                    trie[p][num] = ++idx;
                }
                //移动当前指针指向到下一个字符
                p = trie[p][num];
            }
            cnt[p]++;
        }

        //搜索字符串
        public boolean search(String s) {
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - 'a';
                if (trie[p][num] == 0) return false;
                p = trie[p][num];
            }
            return cnt[p]!=0;
        }

        public boolean startsWith(String s) {
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - 'a';
                if (trie[p][num] == 0) return false;
                p = trie[p][num];
            }
            return true;
        }
    }*/

    class Solution {
        //思路一：暴力dfs+回溯
//        最开始肯定可以暴力回溯拿到所有解
//        将所有目标单词存入set
//        然后每一次扫到目标单词，从set中删除一个元素
//        通过vis记录每个字符是否被扫描，放置重复扫描
        /*Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();
        char[][] boards;
        int m, n;
        boolean[][] vis;

        public List<String> findWords(char[][] board, String[] words) {
            m = board.length;
            n = board[0].length;
            boards = board;
            set.addAll(Arrays.asList(words));
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    vis = new boolean[m][n];
                    char c = boards[i][j];
                    vis[i][j] = true;
                    StringBuilder temp = new StringBuilder();
                    temp.append(c);
                    dfs(i, j, temp);
                    vis[i][j] = false;
                    temp.deleteCharAt(temp.length() - 1);
                }
            }
            return res;
        }

        void dfs(int i, int j, StringBuilder sb) {

            if (sb.toString().length() > 10) {
                return;
            }
            if (set.contains(sb.toString())) {
                res.add(sb.toString());
                set.remove(sb.toString());
            }

            if (i > 0 && !vis[i - 1][j]) {
                vis[i - 1][j] = true;
                sb.append(boards[i - 1][j]);
                dfs(i - 1, j, sb);
                vis[i - 1][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (j > 0 && !vis[i][j - 1]) {
                sb.append(boards[i][j - 1]);
                vis[i][j - 1] = true;
                dfs(i, j - 1, sb);
                vis[i][j - 1] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (i < m - 1 && !vis[i + 1][j]) {
                sb.append(boards[i + 1][j]);
                vis[i + 1][j] = true;
                dfs(i + 1, j, sb);
                vis[i + 1][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (j < n - 1 && !vis[i][j + 1]) {
                sb.append(boards[i][j + 1]);
                vis[i][j + 1] = true;
                dfs(i, j + 1, sb);
                vis[i][j + 1] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }*/
        //思路二：字典树+二维数组实现
        //这种寻找单词的题其实很容易想到Trie(前缀树)
        //trie有两种实现思路-二维数组以及自定义结构体（类似链表）
        //trie有三个比较关键的函数
        //search 查找
        //insert 插入
        //startsWith 判断是否有此前缀
        //我不太理解为啥会TLE，我本机没啥问题啊离谱
        /*Set<String> set = new HashSet<>();
        char[][] boards;
        int m, n;
        boolean[][] vis = new boolean[15][15];
        Trie trie = new Trie();

        public List<String> findWords(char[][] board, String[] words) {
            boards = board;
            m = board.length;
            n = board[0].length;
            for (String word : words
            ) {
                trie.insert(word);
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int num = boards[i][j] - 'a';
                    if (trie.trie[0][num] != 0) {
                        vis[i][j] = true;
                        StringBuilder sb = new StringBuilder();
                        sb.append(boards[i][j]);
                        dfs(i, j, sb);
                        vis[i][j] = false;
                    }
                }
            }
            List<String> res = new ArrayList<>(set);
            return res;
        }

        void dfs(int i, int j, StringBuilder sb) {
            if (sb.toString().length() > 10) {
                return;
            }
            if (trie.search(sb.toString())) {
                set.add(sb.toString());
            }
            if (i > 0 && !vis[i - 1][j]) {
                vis[i - 1][j] = true;
                sb.append(boards[i - 1][j]);
                dfs(i - 1, j, sb);
                vis[i - 1][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (j > 0 && !vis[i][j - 1]) {
                sb.append(boards[i][j - 1]);
                vis[i][j - 1] = true;
                dfs(i, j - 1, sb);
                vis[i][j - 1] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (i < m - 1 && !vis[i + 1][j]) {
                sb.append(boards[i + 1][j]);
                vis[i + 1][j] = true;
                dfs(i + 1, j, sb);
                vis[i + 1][j] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            if (j < n - 1 && !vis[i][j + 1]) {
                sb.append(boards[i][j + 1]);
                vis[i][j + 1] = true;
                dfs(i, j + 1, sb);
                vis[i][j + 1] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }*/
        //思路三：字典树+自定义结构体实现
        char[][] boards;
        int m, n;
        class Trie{
            Trie[] trs;
            String s;
            public Trie(){
                 trs = new Trie[26];
            }
        }
        Set<String> set = new HashSet<>();
        Trie trie = new Trie();
        public void insert(String s){
            Trie p = trie;
            for (int i = 0; i < s.length(); i++) {
                int u = s.charAt(i)-'a';
                if (p.trs[u]==null){
                    p.trs[u] = new Trie();
                }
                p = p.trs[u];
            }
            p.s = s;

        }
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][] vis = new boolean[15][15];
        public List<String> findWords(char[][] board, String[] words) {

            boards = board;
            m = board.length;
            n = board[0].length;
            for (String word : words
            ) {
                insert(word);
            }
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int num = boards[i][j] - 'a';
                    if (this.trie.trs[num] != null) {
                        vis[i][j] = true;
                        dfs(i, j, this.trie.trs[num]);
                        vis[i][j] = false;
                    }
                }
            }
            List<String> res = new ArrayList<>(set);
            return res;
        }

        void dfs(int i, int j, Trie trie) {

            if (trie.s!=null){
                set.add(trie.s);
            }
            for (int[] d : dirs) {
                int dx = i + d[0], dy = j + d[1];
                if (dx < 0 || dx >= m || dy < 0 || dy >= n) continue;
                if (vis[dx][dy]) continue;
                int u = boards[dx][dy] - 'a';
                if (trie.trs[u] != null) {
                    vis[dx][dy] = true;
                    dfs(dx, dy, trie.trs[u]);
                    vis[dx][dy] = false;
                }
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}