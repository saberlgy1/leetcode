//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 排序 数组 双指针 
// 👍 793 👎 0

package com.cute.leetcode.editor.cn;

/**
 * @author 2440917872qq.com
 */
public class SortColors {
    public static void main(String[] args) {
        Solution solution = new SortColors().new Solution();
        int[] a = new int[]{2,0,2,1,1,0};
        solution.sortColors(a);
        for (int j = 0; j < a.length; j++){
            System.out.println(a[j]);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void sortColors(int[] nums) {
            //常数空间
            //单指针原地置换 2次扫描 O(2n) O(1)
/*            int ptr = 0;
            for (int i = 0; i < nums.length; i++){
                if (nums[i] == 0){
                    int temp = nums[ptr];
                    nums[ptr] = nums[i];
                    nums[i] = temp;
                    ptr++;
                }
            }
            for (int i = 0; i < nums.length ; i++) {
                if (nums[i] == 1){
                    int temp = nums[ptr];
                    nums[ptr] = nums[i];
                    nums[i] = temp;
                    ptr++;
                }
            }*/

            //双指针 一个指针代表0 一个指针代表1 其实还有一个循环指针i 负责记录当前扫描到的位置
            //每当i 扫描到0 或者 1 则暂停扫描，交换交换01 指针与当前指针值，移动对应指针，然后知道i处值为2 ，继续扫描
            int m = 0, n=0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    int temp = nums[i];
                    nums[i] = nums[n];
                    nums[n] = temp;
                    n++;
                }
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[m];
                    nums[m] = temp;
                    if (m < n){
                        temp = nums[i];
                        nums[i] = nums[n];
                        nums[n] = temp;
                    }
                    m++;
                    n++;
                }

            }


        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}