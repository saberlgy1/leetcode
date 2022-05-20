//一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。 
//
// 它们所处的环境设定是一个 rows x cols 的方格 grid ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。 
//
// 
// 玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。 
// 地板由字符 '.' 表示，玩家可以通过这个格子。 
// 墙用字符 '#' 表示，玩家不能通过这个格子。 
// 食物用字符 'F' 表示，玩家可以通过这个格子。 
// 字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。 
// 
//
// 猫和老鼠按照如下规则移动： 
//
// 
// 老鼠 先移动 ，然后两名玩家轮流移动。 
// 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 grid 。 
// catJump 和 mouseJump 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。 
// 它们可以停留在原地。 
// 老鼠可以跳跃过猫的位置。 
// 
//
// 游戏有 4 种方式会结束： 
//
// 
// 如果猫跟老鼠处在相同的位置，那么猫获胜。 
// 如果猫先到达食物，那么猫获胜。 
// 如果老鼠先到达食物，那么老鼠获胜。 
// 如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。 
// 
//
// 给你 rows x cols 的矩阵 grid 和两个整数 catJump 和 mouseJump ，双方都采取最优策略，如果老鼠获胜，那么请你返回 
//true ，否则返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
//输出：true
//解释：猫无法抓到老鼠，也没法比老鼠先到达食物。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：grid = ["M.C...F"], catJump = 1, mouseJump = 4
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：grid = ["M.C...F"], catJump = 1, mouseJump = 3
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 
//1
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// rows == grid.length 
// cols = grid[i].length 
// 1 <= rows, cols <= 8 
// grid[i][j] 只包含字符 'C' ，'M' ，'F' ，'.' 和 '#' 。 
// grid 中只包含一个 'C' ，'M' 和 'F' 。 
// 1 <= catJump, mouseJump <= 8 
// 
// Related Topics 广度优先搜索 图 记忆化搜索 数学 动态规划 博弈 👍 69 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class CatAndMouseIi {
    public static void main(String[] args) {
        Solution solution = new CatAndMouseIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int n, m ,tx,ty,a,b;
        int[][] f = new int[8*8*8*8][1000];
        int[][] dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        String[] g ;
        int dfs(int x, int y, int p, int q, int k){
            //快速计算位置
            int state = (x << 9) | (y << 6) | (p << 3) | q;
            if (k == 1000 - 1) return f[state][k] = 1;
            if (x == p && y == q) return f[state][k] = 1;
            if (x == tx && y == ty) return f[state][k] = 0;
            if (p == tx && q == ty) return f[state][k] = 1;
            if (f[state][k] != -1) return f[state][k];
            if (k % 2 == 0) { // mouse
                for (int[] di : dir) {
                    for (int i = 0; i <= b; i++) {
                        int nx = x + di[0] * i, ny = y + di[1] * i;
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                        if (g[nx].charAt(ny) == '#') break;
                        if (dfs(nx, ny, p, q, k + 1) == 0) return f[state][k] = 0;
                    }
                }
                return f[state][k] = 1;
            } else { // cat
                for (int[] di : dir) {
                    for (int i = 0; i <= a; i++) {
                        int np = p + di[0] * i, nq = q + di[1] * i;
                        if (np < 0 || np >= n || nq < 0 || nq >= m) break;
                        if (g[np].charAt(nq) == '#') break;
                        if (dfs(x, y, np, nq, k + 1) == 1) return f[state][k] = 1;
                    }
                }
                return f[state][k] = 0;
            }
        }

        public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
            g = grid;
            n = g.length; m = g[0].length(); a = catJump; b = mouseJump;
            for (int i = 0; i < 8*8*8*8; i++) Arrays.fill(f[i], -1);
            int x = 0, y = 0, p = 0, q = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (g[i].charAt(j) == 'M') {
                        x = i; y = j;
                    } else if (g[i].charAt(j) == 'C') {
                        p = i; q = j;
                    } else if (g[i].charAt(j) == 'F') {
                        tx = i; ty = j;
                    }
                }
            }
            return dfs(x, y, p, q, 0) == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}