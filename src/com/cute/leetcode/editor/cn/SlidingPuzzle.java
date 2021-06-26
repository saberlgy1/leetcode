//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示. 
//
// 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 示例： 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//输入：board = [[3,2,4],[1,5,0]]
//输出：14
// 
//
// 提示： 
//
// 
// board 是一个如上所述的 2 x 3 的数组. 
// board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列. 
// 
// Related Topics 广度优先搜索 数组 矩阵 
// 👍 154 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SlidingPuzzle {
    public static void main(String[] args) {
        Solution solution = new SlidingPuzzle().new Solution();
        int[][] boards = new int[][]{{4,1,2},{5,0,3}};
        System.out.println(solution.slidingPuzzle(boards));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：BFS+二维压缩
        //思路分析，这个题有点类似小时候玩的智能拼图？我记得是叫这个名字，只不过这个是固定了起点
        //首先找到起点坐标，然后移动起点坐标
        //二维坐标转换为一维坐标
        //index = x*3+y
        //题解target也就可以转换为「1，2，3，4，5，0」
        //起点一共能移动四个方向（1，0）（0，1）（-1，0）（0，-1）
        //移动需要保证不能越界
        //通过广度优先遍历移动并交换起点元素
        //打印移动后字符串，满足target解答即可
        class Node {
            String s;
            int x,  y;

            public Node(String s, int x, int y) {
                this.s = s;
                this.x = x;
                this.y = y;
            }

            public Node() {
            }
        }
        public int slidingPuzzle(int[][] board) {
            String target = "123450";
            String s = "";
            int sx = 0, sy = 0;
            Node root =new Node();
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    s += board[i][j];
                    //遍历确认起始坐标
                    if (0 == board[i][j]) {
                        sx = i;
                        sy = j;
                    }
                    root = new Node(s,sx,sy);
                }
            }
            //方向向量
            int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            Deque<Node> deque = new LinkedList<>();
            Map<String , Integer> map = new HashMap<>();
            deque.add(root);
            map.put(s, 0);
            while (!deque.isEmpty()) {
                Node tempNode = deque.poll();
                String str = tempNode.s;
                sx = tempNode.x;
                sy = tempNode.y;
                if (str.equals(target)) {
                    return map.get(str);
                }
                for (int[] dir : dirs
                ) {
                    char[] ctr = str.toCharArray();
                    int nx = sx + dir[0], ny = sy + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }
                    //交换数组
                    char tmp = ctr[sx * 3 + sy];
                    ctr[sx * 3 + sy] = ctr[nx * 3 + ny];
                    ctr[nx * 3 + ny] = tmp;
                    String temStr = String.valueOf(ctr, 0, 6);
                    if (temStr.equals(target)) {
                        return map.get(str) + 1;
                    }
                    if (!map.containsKey(temStr)) {
                        deque.add(new Node(temStr,nx,ny));
                        map.put(temStr, map.get(str) + 1);
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}