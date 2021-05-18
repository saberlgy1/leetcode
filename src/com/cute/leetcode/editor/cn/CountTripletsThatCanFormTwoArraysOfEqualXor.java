//给你一个整数数组 arr 。 
//
// 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。 
//
// a 和 b 定义如下： 
//
// 
// a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] 
// b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 
// 
//
// 注意：^ 表示 按位异或 操作。 
//
// 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。 
//
// 
//
// 示例 1： 
//
// 输入：arr = [2,3,1,6,7]
//输出：4
//解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
// 
//
// 示例 2： 
//
// 输入：arr = [1,1,1,1,1]
//输出：10
// 
//
// 示例 3： 
//
// 输入：arr = [2,3]
//输出：0
// 
//
// 示例 4： 
//
// 输入：arr = [1,3,5,7,9]
//输出：3
// 
//
// 示例 5： 
//
// 输入：arr = [7,11,12,9,5,2,7,17,22]
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 300 
// 1 <= arr[i] <= 10^8 
// 
// Related Topics 位运算 数组 数学 
// 👍 134 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class CountTripletsThatCanFormTwoArraysOfEqualXor {
    public static void main(String[] args) {
        Solution solution = new CountTripletsThatCanFormTwoArraysOfEqualXor().new Solution();
        int[] array = new int[]{1,1,1,1,1};
        System.out.println(solution.countTriplets(array));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countTriplets(int[] arr) {
            //异或的规律很多时候都想不到可以尝试补全所有位数
            //Si = a[0] ^ ``` ^ a[i];
            //Sj = a[0] ^ ``` ^ a[j];
            //Si ^ Sj = a[i+1] ^ ```^ a[j]
            //a = Si-1 ^ Sj-1;
            //b = Sj-1 ^ Sk;
            //a==b
            //Si-1 = Sk
            int[] s = new int[arr.length + 1];
            int res = 0;
            //定义s数组
            for (int i = 0; i < arr.length; i++) {
                s[i + 1] = arr[i] ^ s[i];


            }
            //依次遍历找到所有相同元素
            Map<Integer, Integer> cnt = new HashMap<>();
            Map<Integer, Integer> total = new HashMap<>();
            for (int k = 0; k < arr.length; k++) {
                if (cnt.containsKey(s[k + 1])) {
                    res += cnt.get(s[k + 1]) * k - total.get(s[k + 1]);
                }
                cnt.put(s[k], cnt.getOrDefault(s[k], 0) + 1);
                total.put(s[k], total.getOrDefault(s[k], 0) + k);

            }
            //得到所有数组
            /*for (Integer i : map.keySet()) {
                String[] val = map.get(i).split(",");
                if (val.length <= 1) {
                    continue;
                }
                //应该两层循环
                for (int j = 0; j < val.length; j++) {
                    for (int k = j + 1; k< val.length; k++){
                        res  = res + (Integer.parseInt(val[k]) - Integer.parseInt(val[j]) - 1);
                    }
                }
            }*/
            //优化 k为右侧尾端值，i 从左侧第一个到k之前的位置 k - i1 + k -i2+``` + k-im;
            // 一层循环
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}