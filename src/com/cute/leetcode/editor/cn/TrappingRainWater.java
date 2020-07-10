//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Mar
//cos 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针 
// 👍 1428 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Stack;

public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
        int[] arr = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution.trap(arr));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            //corner case
            if (height.length == 0) {
                return 0;
            }
            //按行求和 time limit exceeded
//            int len = height.length;
//            int temp = 0;
//            boolean flag = false;
//            int max = 0;
//            int sum = 0;
            //按行求和 time exceeded

            //求出最高层级
//            for (int i = 0; i < len; i++) {
//                if (height[i] > max) {
//                    max = height[i];
//                }
//            }
//            for (int level = 1; level <= max; level++) {
//                temp = 0;
//                flag = false;
//                for (int i = 0; i < len; i++) {
//                    if (flag && height[i] < level) {
//                        temp = temp + 1;
//                    }
//                    if (height[i] >= level) {
//                        sum += temp;
//                        temp = 0;
//                        flag = true;
//                    }
//                }
//
//            }


            //按列求和 暴力法
            int sum = 0;
            int len = height.length;
//            for (int i = 1; i <= len - 2; i++) {
//                for (int l = 0; l < i; l++) {
//                    if (height[l] > left) {
//                        left = height[l];
//                    }
//                }
//                for (int r = i + 1; r < len; r++){
//                    if (height[r] > right){
//                        right = height[r];
//                    }
//                }
//                sum += Math.max(Math.min(left, right) - height[i],0);
//                left = 0;
//                right = 0;
//            }

            //优化1 取数组最大值、左右指针少获取一次
            //获取最大值所在索引
//            int index = getMaxIndex(height);
//
//
//            for (int i = 1; i <= len - 2; i++) {
//                if (i < index) {
//                    right = height[index];
//                    for (int l = 0; l < i; l++) {
//                        if (height[l] > left) {
//                            left = height[l];
//                        }
//                    }
//                }
//
//                if (i > index) {
//                    left = height[index];
//                    for (int r = i + 1; r < len; r++) {
//                        if (height[r] > right) {
//                            right = height[r];
//                        }
//                    }
//                }
//
//                if (i != index) {
//
//                    sum += Math.max(Math.min(left, right) - height[i], 0);
//                    left = 0;
//                    right = 0;
//                }
//            }
//            //优化2 动态规划 dp
//            int[] maxleft = new int[len];
//
//            for (int i = 1; i < len - 1; i++) {
//                if (maxleft[i - 1] < height[i - 1]) {
//                    maxleft[i] = height[i - 1];
//                } else {
//                    maxleft[i] = maxleft[i - 1];
//                }
//
//                if (height[len -  i  ] <maxRight[len - i]){
//                    maxRight[len - 1 - i]  = maxRight[len - i];
//                }else{
//                    maxRight[len - 1 - i]  =height[len - i ];
//                }
//
//            }
//            for (int i = 1; i < len - 1; i++){
//                sum += Math.max((Math.min(maxleft[i], maxRight[i]) - height[i]),0);
//            }
//            return sum;
//        }
            //优化3 dp + 单指针
//            int[] maxRight = new int[len];
//            int maxLeft = height[0];
//            for (int i = len - 2; i >= 0; i--) {
//                maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
//            }
//
//            for (int i = 1; i < len - 1; i++){
//                if (maxLeft < height[i - 1]) {
//                    maxLeft = height[i - 1];
//                }
//                sum += Math.max((Math.min(maxLeft, maxRight[i]) - height[i]), 0);
//            }
//
//            return sum;
//        }

            //优化4 dp + 双指针
//            int ml = height[0],  left = 1, right = len - 2,mr = height[right + 1];
//            while (left <= right) {
//                if (ml < mr) {
//                    if (ml > height[left]) {
//                        sum += (ml - height[left]);
//                    } else {
//                        ml = height[left];
//                    }
//                    left ++;
//                }else{
//                    if (mr > height[right]) {
//                        sum += (mr - height[right]);
//                    } else {
//                        mr = height[right];
//                    }
//                    right --;
//                }
//            }
            //方法五 单调栈
            Stack<Integer> stack = new Stack<>();
            int cur = 0;
            while (cur < len) {
                while (!stack.empty() && height[cur] > height[stack.peek()]){
                    int h = height[stack.peek()];
                    stack.pop();
                    if (stack.isEmpty()){
                        break;
                    }
                    int dis = cur - stack.peek() - 1;
                    int min = Math.min(height[stack.peek()],height[cur]);
                    sum   = sum + (dis* (min - h));
                }
                stack.push(cur);
                cur ++;
            }
            return sum;

        }

        private int getMaxIndex(int[] arr) {
            int len = arr.length;
            int res = 0;
            int max = 0;
            for (int i = 0; i < len; i++) {
                if (arr[i] > max) {
                    res = i;
                    max = arr[i];
                }
            }
            return res;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}