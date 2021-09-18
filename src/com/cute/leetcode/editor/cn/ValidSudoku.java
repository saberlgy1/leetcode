//请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图） 
// 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 注意： 
//
// 
// 一个有效的数独（部分已被填充）不一定是可解的。 
// 只需要根据以上规则，验证已经填入的数字是否有效即可。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = 
//[["5","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = 
//[["8","3",".",".","7",".",".",".","."]
//,["6",".",".","1","9","5",".",".","."]
//,[".","9","8",".",".",".",".","6","."]
//,["8",".",".",".","6",".",".",".","3"]
//,["4",".",".","8",".","3",".",".","1"]
//,["7",".",".",".","2",".",".",".","6"]
//,[".","6",".",".",".",".","2","8","."]
//,[".",".",".","4","1","9",".",".","5"]
//,[".",".",".",".","8",".",".","7","9"]]
//输出：false
//解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无
//效的。 
//
// 
//
// 提示： 
//
// 
// board.length == 9 
// board[i].length == 9 
// board[i][j] 是一位数字或者 '.' 
// 
// Related Topics 数组 哈希表 矩阵 👍 607 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    public static void main(String[] args) {
        Solution solution = new ValidSudoku().new Solution();
        char[][] board = new char[][]{{'.', '2', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '5', '.', '1'}, {'.', '.', '.', '.', '.', '.', '8', '1', '3'}, {'4', '.', '9', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '2', '.', '.', '.', '.', '.', '.'}, {'7', '.', '6', '.', '.', '.', '.', '.', '.'}, {'9', '.', '.', '.', '.', '4', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.', '.'}};
        System.out.println(solution.isValidSudoku(board));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力搜就完事了
        public boolean isValidSudoku(char[][] board) {
            //先列搜
            for (int i = 0; i < board.length; i++) {
                Set<Character> set = new HashSet<>();
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == '.') continue;
                    if (set.contains(board[i][j])) {
                        return false;
                    }
                    set.add(board[i][j]);
                }
            }
            //再行搜
            for (int i = 0; i < board.length; i++) {
                Set<Character> set = new HashSet<>();
                for (int j = 0; j < board.length; j++) {
                    if (board[j][i] == '.') continue;
                    if (set.contains(board[j][i])) {
                        return false;
                    }
                    set.add(board[j][i]);
                }
            }
            //九宫格搜
            int[][] dirs = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            for (int i = 1; i <= 7; i += 3) {
                for (int j = 1; j <= 7; j += 3) {
                    Set<Character> set = new HashSet<>();
                    if (board[i][j] != '.') {
                        set.add(board[i][j]);
                    }
                    for (int[] dir : dirs) {
                        if (board[i + dir[0]][j + dir[1]] == '.') continue;
                        if (set.contains(board[i + dir[0]][j + dir[1]])) {
                            return false;
                        }
                        set.add(board[i + dir[0]][j + dir[1]]);
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}