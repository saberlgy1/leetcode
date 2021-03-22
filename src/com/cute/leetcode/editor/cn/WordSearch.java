//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法 
// 👍 794 👎 0

package com.cute.leetcode.editor.cn;

public class WordSearch {
    public static void main(String[] args) {
        Solution solution = new WordSearch().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean exist(char[][] board, String word) {

            int m = board.length, n = board[0].length;
            boolean[][] visited = new boolean[m][n];
            //记录被搜索数组标志位
            int flag = 0;
            //遍历整个二维数组每个位置做回溯
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    boolean res = check(i, j, word, board, visited, 0);
                    if (res) {
                        return res;
                    }
                }
            }
            return false;

        }

        private boolean check(int i, int j, String word, char[][] board, boolean[][] visited, int location) {
            if (board[i][j] != word.charAt(location)) {
                return false;
            }
            //排除不等情况，相等且到达被搜索字符串最尾端返回true；
            if (location == word.length() - 1) {
                return true;
            }
            visited[i][j] = true;
            boolean result = false;
            //四个方向遍历，并且填写visited
            int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int movation = 0; movation < direction.length; movation++) {
                int newi = i + direction[movation][0], newj = j + direction[movation][1];
                if (newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length) {
                    if (!visited[newi][newj]) {
                        boolean flag = check(newi, newj, word, board, visited, location + 1);
                        if (flag) {
                            result = true;
                            break;
                        }
                    }
                }
            }
            // 重置visit数组
            visited[i][j] = false;
            return result;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}