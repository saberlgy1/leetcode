package com.cute.leetcode.editor.contest.noDouble58;

/**
 * @program: leetcode
 * @description: 检查操作是否合法
 * @author: lgy
 * @create: 2021-08-07 22:48
 * 给你一个下标从 0 开始的 8 x 8 网格 board ，其中 board[r][c] 表示游戏棋盘上的格子 (r, c) 。棋盘上空格用 '.' 表示，白色格子用 'W' 表示，黑色格子用 'B' 表示。
 * <p>
 * 游戏中每次操作步骤为：选择一个空格子，将它变成你正在执行的颜色（要么白色，要么黑色）。但是，合法 操作必须满足：涂色后这个格子是 好线段的一个端点 （好线段可以是水平的，竖直的或者是对角线）。
 * <p>
 * 好线段 指的是一个包含 三个或者更多格子（包含端点格子）的线段，线段两个端点格子为 同一种颜色 ，且中间剩余格子的颜色都为 另一种颜色 （线段上不能有任何空格子）。你可以在下图找到好线段的例子：
 * <p>
 * <p>
 * 给你两个整数 rMove 和 cMove 以及一个字符 color ，表示你正在执行操作的颜色（白或者黑），如果将格子 (rMove, cMove) 变成颜色 color 后，是一个 合法 操作，那么返回 true ，如果不是合法操作返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：board = [[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],["W","B","B",".","W","W","W","B"],[".",".",".","B",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."]], rMove = 4, cMove = 3, color = "B"
 * 输出：true
 * 解释：'.'，'W' 和 'B' 分别用颜色蓝色，白色和黑色表示。格子 (rMove, cMove) 用 'X' 标记。
 * 以选中格子为端点的两个好线段在上图中用红色矩形标注出来了。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：board = [[".",".",".",".",".",".",".","."],[".","B",".",".","W",".",".","."],[".",".","W",".",".",".",".","."],[".",".",".","W","B",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".","B","W",".","."],[".",".",".",".",".",".","W","."],[".",".",".",".",".",".",".","B"]], rMove = 4, cMove = 4, color = "W"
 * 输出：false
 * 解释：虽然选中格子涂色后，棋盘上产生了好线段，但选中格子是作为中间格子，没有产生以选中格子为端点的好线段。
 * <p>
 * <p>
 * 提示：
 * <p>
 * board.length == board[r].length == 8
 * 0 <= rMove, cMove < 8
 * board[rMove][cMove] == '.'
 * color 要么是 'B' 要么是 'W' 。
 **/

public class CheckOpr {

    public static void main(String[] args) {
        //char[][] board = new char[][]{{'.', '.', '.', 'B', '.', '.', '.', '.'}, {'.', '.', '.', 'W', '.', '.', '.', '.'}, {'.', '.', '.', 'W', '.', '.', '.', '.'}, {'.', '.', '.', 'W', '.', '.', '.', '.'}, {'W', 'B', 'B', '.', 'W', 'W', 'W', 'B'}, {'.', '.', '.', 'B', '.', '.', '.', '.'}, {'.', '.', '.', 'B', '.', '.', '.', '.'}, {'.', '.', '.', 'W', '.', '.', '.', '.'}};
        //char[][] board = new char[][]{{'W','W','.','B','.','B','B','.'},{'W','B','.','.','W','B','.','.'},{'B','B','B','B','W','W','B','.'},{'W','B','.','.','B','B','B','.'},{'W','W','B','.','W','.','B','B'},{'B','.','B','W','.','B','.','.'},{'.','B','B','W','B','B','.','.'},{'B','B','W','.','.','B','.','.'}};
        char[][] board = new char[][]{
                {'W','B','.','B','W','.','B','.'},
                {'.','W','.','.','W','B','.','B'},
                {'.','.','B','B','B','B','W','W'},
                {'W','W','B','.','.','W','W','W'},
                {'.','W','B','B','B','B','.','.'},
                {'B','.','.','.','B','B','B','B'},
                {'.','W','.','B','B','B','B','W'},
                {'.','.','B','.','B','W','B','B'}};

        System.out.println(new CheckOpr().checkMove(board, 7, 2, 'B'));
    }

    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        board[rMove][cMove] = color;
        //横线段
        char[] num = new char[8];
        for (int i = 0; i < 8; i++) {
            num[i] = board[rMove][i];
        }
        if (check(num, cMove)) {
            return true;
        }
        //纵线段
        for (int i = 0; i < 8; i++) {
            num[i] = board[i][cMove];
        }
        if (check(num, rMove)) {
            return true;
        }
        //坐上到右下对角线
        int l = rMove, r = cMove;
        num = new char[8];
        num[r] = board[l][r];
        while (l >= 0 && r >= 0) {
            num[r] = board[l--][r--];
        }
        l = rMove;
        r = cMove;
        while (l < 8 && r < 8) {
            num[r] = board[l++][r++];

        }
        if (check(num, cMove)) {
            return true;
        }
        //左下到右上对角线
        l = rMove;
        r = cMove;
        num = new char[8];
        num[r] = board[l][r];
        while (l < 8 && r >= 0) {
            num[r] = board[l++][r--];
        }

        l = rMove;
        r = cMove;
        while (l >= 0 && r < 8) {
            num[r] = board[l--][r++];
        }
        if (check(num, cMove)) {
            return true;
        }
        return false;
    }

    boolean check(char[] num, int temp) {

        int l = temp - 1, r = temp + 1;
        if (temp >= 2 && num[l] != '.' && num[l] != num[temp]) {
            while (l >= 0 && num[l] != '.') {
                if (num[l] != num[temp]) {
                    l--;
                } else if (num[l] == num[temp] && temp - l >= 2) {
                    return true;
                } else {
                    break;
                }
            }
        }
        if (r < 8 && num[r] != '.' && num[r] != num[temp]) {
            while (r < 8 && num[r] != '.') {
                if (num[r] != num[temp]) {
                    r++;
                } else if (num[r] == num[temp] && r - temp >= 2) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

}
