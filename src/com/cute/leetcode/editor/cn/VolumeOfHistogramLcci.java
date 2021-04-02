//给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。 
//
// 
//
// 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marco
//s 贡献此图。 
//
// 示例: 
//
// 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
//输出: 6 
// Related Topics 栈 数组 双指针 
// 👍 148 👎 0

package com.cute.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class VolumeOfHistogramLcci {
    public static void main(String[] args) {
        Solution solution = new VolumeOfHistogramLcci().new Solution();
        int[] a = new int[] {0,1,0,2,1,0,1,3,2,1,2};
        int[] b = new int[] {2,0,2};
        int[] c = new int[]{5,2,1,2,1,5};
        int[] d = new int[] {4,2,0,3,2,5};
        System.out.println(solution.trap(a));
        System.out.println(solution.trap(b));
        System.out.println(solution.trap(c));
        System.out.println(solution.trap(d));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int trap(int[] height) {
            int n = height.length;
            Stack<Integer> stack = new Stack<>();
            //维护一个最大值，进行边界计算
            int i = 0, max = 0, res = 0;
            for (; i < n; i++) {
                if (height[i] != 0) {
                    max = height[i];
                    stack.push(height[i]);
                    i++;
                    break;
                }
            }
            //corner case
            if (stack.isEmpty()) {
                return 0;
            }
            while (i < n) {
                if (height[i] <= stack.peek()) {
                    stack.push(height[i]);
                }
                if (height[i] > stack.peek()) {
                    int temp = 0, index = 0;
                    //按层运算
                    if (height[i] < max) {
                        while (!stack.isEmpty() && stack.peek() < height[i]) {
                            temp = height[i];
                            index++;
                            res = res + height[i] - stack.pop();
                        }
                        for (int j = 0; j <= index; j++) {
                            temp = height[i];
                            stack.push(temp);
                        }
                    }
                    if (height[i] >= max) {
                        if (stack.peek() > max) {
                            max = height[i];
                        } else {
                            while (!stack.isEmpty()) {
                                temp = height[i];
                                index++;
                                res = res + max - stack.pop();
                            }
                            for (int j = 0; j <= index; j++) {
                                stack.push(temp);
                            }
                            max = temp;
                        }
                    }
                }
                i++;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}