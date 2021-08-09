//泰波那契序列 Tn 定义如下：
//
// T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
//
// 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
//
//
//
// 示例 1：
//
// 输入：n = 4
//输出：4
//解释：
//0,1,1,2,3,5
//T_3 = 0 + 1 + 1 = 2
//T_4 = 1 + 1 + 2 = 4
//T_5 =
//
//
// 示例 2：
//
// 输入：n = 25
//输出：1389537
//
//
//
//
// 提示：
//
//
// 0 <= n <= 37
// 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
//
// Related Topics 记忆化搜索 数学 动态规划
// 👍 85 👎 0

package com.cute.leetcode.editor.cn;

public class NThTribonacciNumber {
    public static void main(String[] args) {
        Solution solution = new NThTribonacciNumber().new Solution();
        System.out.println(solution.tribonacci(5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：常规递归
        //37 TLE纯属恶心人哦
        //泰波那契数
        /*public int tribonacci(int n) {
            if (n < 1) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            if (n == 2){
                return 1;
            }
            if (n == 3) {
                return 2;
            }
            return tribonacci(n-1) + tribonacci(n-2) +tribonacci(n-3);
        }*/
        //思路二：记忆化数组
        //计算结果只与前三个数有关
        /*public int tribonacci(int n) {
            if (n < 1) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 1;
            }
            int a = 0, b = 1, c = 1;
            int res = 2;
            for (int i = 3; i <= n; i++) {
                res = a + b + c;
                a = b;
                b = c;
                c = res;
            }
            return res;
        }*/
        //思路三：线性代数
/*        class Matrix {
            int[][] a = new int[3][3];

            Matrix(boolean x) {
                {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            a[i][j] = x ? i == j ? 1 : 0 : 0;
                        }
                    }
                }
            }

            Matrix multi(Matrix b) {
                Matrix C = new Matrix(false);
                for (int k = 0; k < 3; k++) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            C.a[i][j] = (C.a[i][j] + a[i][k] * b.a[k][j]);
                        }
                    }
                }
                return C;
            }

            Matrix babel(int n) {
                Matrix C = new Matrix(true);
                Matrix A = new Matrix(false);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        A.a[i][j] = a[i][j];
                    }
                }
                for (; n > 0; n >>= 1, A = A.multi(A)) {
                    if ((n & 1) == 1) {
                        C = C.multi(A);
                    }
                }
                return C;
            }
        }

        public int tribonacci(int n) {
            Matrix A = new Matrix(false);
            A.a[0][1]=1;
            A.a[0][2]=1;
            Matrix B = new Matrix(false);
            B.a[0][2]=1;
            B.a[1][0]=B.a[1][2]=1;
            B.a[2][1]=B.a[2][2]=1;
            B=(B.babel(n));
            A=A.multi(B);
            return A.a[0][0];
        }*/
        //思路四：数学公式
        public int tribonacci(int n) {
            return (int) Math.floor((Math.pow(33.0 * (99.0 - 17.0 * Math.sqrt(33.0)), 1.0 / 3.0) + Math.pow(33.0 * (99.0 + 17.0 * Math.sqrt(33.0)), 1.0 / 3.0)) / 66.0 * Math.pow((1.0 + Math.pow(19.0 - 3.0 * Math.sqrt(33.0), 1.0 / 3.0) + Math.pow(19.0 + 3.0 * Math.sqrt(33.0), 1.0 / 3.0)) / 3.0, 1.0 * n) + 0.5);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}