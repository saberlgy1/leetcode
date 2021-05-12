//有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。 
//
// 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为
//本次查询的结果。 
//
// 并返回一个包含给定查询 queries 所有结果的数组。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
//输出：[2,7,14,8] 
//解释：
//数组中元素的二进制表示形式是：
//1 = 0001 
//3 = 0011 
//4 = 0100 
//8 = 1000 
//查询的 XOR 值为：
//[0,1] = 1 xor 3 = 2 
//[1,2] = 3 xor 4 = 7 
//[0,3] = 1 xor 3 xor 4 xor 8 = 14 
//[3,3] = 8
// 
//
// 示例 2： 
//
// 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
//输出：[8,0,4,4]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 3 * 10^4 
// 1 <= arr[i] <= 10^9 
// 1 <= queries.length <= 3 * 10^4 
// queries[i].length == 2 
// 0 <= queries[i][0] <= queries[i][1] < arr.length 
// 
// Related Topics 位运算 
// 👍 93 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class XorQueriesOfASubarray {
    public static void main(String[] args) {
        Solution solution = new XorQueriesOfASubarray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        Map<int[][], Integer> map = new HashMap<>();
        public int[] xorQueries(int[] arr, int[][] queries) {
            int[] res = new int[queries.length];
            //暴力法 果不其然超时了
            //优化1 hash算法，防止重复计算
            //优化2 异或运算
            /* Q(left,right)
                    =arr[left]⊕…⊕arr[right]
                    =(arr[0]⊕…⊕arr[left−1])⊕(arr[0]⊕…⊕arr[left−1])⊕(arr[left]⊕…⊕arr[right])
                    =(arr[0]⊕…⊕arr[left−1])⊕(arr[0]⊕…⊕arr[right])
                    =xors[left]⊕xors[right+1]
            ​*/
            int[] temp = new int[arr.length + 1];
            for (int i = 0; i < arr.length; i++) {
                temp[i + 1] = temp[i] ^ arr[i];
            }
            for (int i = 0; i < queries.length; i++) {
                res[i] = temp[queries[i][0]] ^ temp[queries[i][1] + 1];
            }

           /* for (int i = 0 ; i < queries.length; i++){
                res[i] = sumRes(arr, queries[i][0], queries[i][1]);
            }*/
            return res;
        }




        private int sumRes(int[] arr, int l, int r){
            if (map.get(new int[][]{{l,r}})!= null){
                return map.get(new int[][]{{l,r}});
            }
            int res =arr[l];
            for (int i =l + 1; i <= r; i ++){
                res = res ^ arr[i];

            }
            map.put(new int[][]{{l,r}},res);
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}