//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。 
//
// 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 2913 👎 0

package com.cute.leetcode.editor.cn;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        int[] a1 = new int[]{ 3};
        int[] a2 = new int[]{-2,-1};
        System.out.println(solution.findMedianSortedArrays(a1, a2));

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //莫名其妙的双百 m+n m+n
        /*public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;
            int length = len2 + len1;
            int l = 0, r = 0, res = 0;
            int[] arr = new int[length/2 + 1];
            for (int i = 0; i < arr.length; i++){
                if (l < len1 && r < len2){
                    if (nums1[l] <= nums2[r]){
                        arr[i] = nums1[l];
                        l++;
                    }else {
                        arr[i] = nums2[r];
                        r++;
                    }
                }else if (l < len1){
                    arr[i] = nums1[l];
                    l++;
                }else if (r < len2){
                    arr[i] = nums2[r];
                    r++;
                }
            }
            if (length % 2 ==0){
                return ((double) (arr[arr.length -2]) + (double) arr[arr.length - 1])/2;
            }
            return arr[arr.length - 1];
        }*/
        //二分查找 log（m + n）1
   /*     public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int length1 = nums1.length, length2 = nums2.length;
            int totalLength = length1 + length2;
            if (totalLength % 2 == 1) {
                int midIndex = totalLength / 2;
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;
            } else {
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
                double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
                return median;
            }
        }

        public int getKthElement(int[] nums1, int[] nums2, int k) {


            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;

            while (true) {
                if (index1 == length1) {
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }
                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);
                    index2 = newIndex2 + 1;
                }
            }
        }*/
        //持续优化 分割函数
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }
            int m = nums1.length;
            int n = nums2.length;
            int left = 0, right = m;
            while (left <= right) {
                int i = (left + right) / 2;
                int j = (m + n + 1) / 2 - i;
                if (j != 0 && i != m && nums2[j - 1] > nums1[i]) {
                    left += 1;
                } else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                    right -= 1;
                } else { // 达到要求，并且将边界条件列出来单独考虑
                    int maxLeft = 0;
                    if (i == 0) {
                        maxLeft = nums2[j - 1];
                    } else if (j == 0) {
                        maxLeft = nums1[i - 1];
                    } else {
                        maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                    }
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    } // 奇数的话不需要考虑右半部分

                    int minRight = 0;
                    if (i == m) {
                        minRight = nums2[j];
                    } else if (j == n) {
                        minRight = nums1[i];
                    } else {
                        minRight = Math.min(nums2[j], nums1[i]);
                    }

                    return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
                }
            }
            return 0.0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}