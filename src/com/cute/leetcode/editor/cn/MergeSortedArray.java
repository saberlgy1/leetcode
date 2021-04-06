//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。 
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nu
//ms2 的元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m + n 
// nums2.length == n 
// 0 <= m, n <= 200 
// 1 <= m + n <= 200 
// -109 <= nums1[i], nums2[i] <= 109 
// 
// Related Topics 数组 双指针 
// 👍 897 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Arrays;

public class MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new MergeSortedArray().new Solution();
        int[] a = new int[]{1, 2, 3, 0, 0, 0};
        int[] b = new int[]{2, 5, 6};
        solution.merge(a, 3, b, 3);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            if (m == 0 && n == 0) {
                return;
            }
/*            //解法1 新开辟数组后copy O(m + n) O(m + n)
            int[] res = new int[m + n];
            int l = 0, r = 0;
            int i = 0;
            for (; (i < m + n) && (l < m) && (r < n); i++) {

                if (nums1[l] <= nums2[r]) {
                    res[i] = nums1[l];
                    l++;
                } else {
                    res[i] = nums2[r];
                    r++;
                }
            }
            if (l == m) {
                for (; r < n; r++,i++) {
                    res[i] = nums2[r];
                }
            }
            if (r == n) {
                for (; l < m; l++,i++) {
                    res[i] = nums1[l];
                }
            }
            for (int j = 0; j < m + n; j++) {
                nums1[j] = res[j];
            }*/
            //优化额外空间，因为是排序过的数组所以可以从后往前遍历而不会被覆盖
            int l = m - 1, r = n - 1, i = m + n - 1;
            for (; (i >= 0) && (l >= 0) && (r >= 0); i--) {
                if (nums1[l]>= nums2[r] ){
                    nums1[i] = nums1[l];
                    l--;
                }else {
                    nums1[i] = nums2[r];
                    r--;
                }

            }
            if (l < 0){
                for (; r >=0 ; r--,i--) {
                    nums1[i]= nums2[r];
                }
            }
//            if (r < 0){
//                for (; l >=0 ; l--,i--) {
//                    nums1[i]= nums1[l];
//                }
//            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}