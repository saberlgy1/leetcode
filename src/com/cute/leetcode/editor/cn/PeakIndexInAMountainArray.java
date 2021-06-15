//符合下列属性的数组 arr 称为 山脉数组 ：
//
// arr.length >= 3
// 存在 i（0 < i < arr.length - 1）使得：
//
// arr[0] < arr[1] < ... arr[i-1] < arr[i]
// arr[i] > arr[i+1] > ... > arr[arr.length - 1]
//
//
//
//
// 给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i +
//1] > ... > arr[arr.length - 1] 的下标 i 。
//
//
//
// 示例 1：
//
//
//输入：arr = [0,1,0]
//输出：1
//
//
// 示例 2：
//
//
//输入：arr = [0,2,1,0]
//输出：1
//
//
// 示例 3：
//
//
//输入：arr = [0,10,5,2]
//输出：1
//
//
// 示例 4：
//
//
//输入：arr = [3,4,5,1]
//输出：2
//
//
// 示例 5：
//
//
//输入：arr = [24,69,100,99,79,78,67,36,26,19]
//输出：2
//
//
//
//
// 提示：
//
//
// 3 <= arr.length <= 104
// 0 <= arr[i] <= 106
// 题目数据保证 arr 是一个山脉数组
//
//
//
//
// 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
// Related Topics 二分查找
// 👍 173 👎 0

package com.cute.leetcode.editor.cn;

public class PeakIndexInAMountainArray {
    public static void main(String[] args) {
        int[] nums = new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19};
        Solution solution = new PeakIndexInAMountainArray().new Solution();
        System.out.println(solution.peakIndexInMountainArray(nums));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //思路一：暴力法
        //思路二：二分查找
        //跟前两题的二分查找的区别在于这次是根据单调性来判断而不是根据大小判断
        //山峰元素需要满足 向右为单调递减，向左为单调递增
        //题目数据保证 arr 是一个山脉数组 所以不需要考虑特殊情况以及不满足的情况
        /*public int peakIndexInMountainArray(int[] arr) {
            int l = 0, r = arr.length - 1;
            while (l < r) {
                int mid = (r - l)/2 + l;
                if (mid > 0 && mid <= arr.length - 2) {
                    //山峰元素在mid左侧
                    if (arr[mid] > arr[mid + 1]) {
                        //满足山峰元素条件
                        if (arr[mid] > arr[mid - 1]) {
                            return mid;
                        }
                        //包含山峰元素（山峰元素右侧元素）
                        else {
                            r = mid;
                        }
                    }
                    //山峰元素在mid右侧
                    else {
                        l = mid + 1;
                    }

                }
            }
            return l;
        }*/
        //思路三：宫水大大三分查找
//        具体的，由于峰顶元素为全局最大值，
//         因此我们可以每次将当前区间分为 [l, m1]、[m1, m2] 和 [m2, r] 三段
//         如果满足 arr[m1] > arr[m2]，说明峰顶元素不可能存在与 [m2, r] 中
//         让 r = m2 - 1 即可。
//         另外一个区间分析同理

        public int peakIndexInMountainArray(int[] arr) {
            int n = arr.length;
            int l = 0, r = n - 1;
            while (l < r) {
                int m1 = l + (r - l) / 3;
                int m2 = r - (r - l) / 3;
                if (arr[m1] > arr[m2]) {
                    r = m2 - 1;
                } else {
                    l = m1 + 1;
                }
            }
            return r;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}