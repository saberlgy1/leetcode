//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
// 例如，
//
// [2,3,4] 的中位数是 3
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5
//
// 设计一个支持以下两种操作的数据结构：
//
//
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。
// double findMedian() - 返回目前所有元素的中位数。
//
//
// 示例：
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3)
//findMedian() -> 2
//
// 进阶:
//
//
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 494 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    public static void main(String[] args) {
        MedianFinder solution = new FindMedianFromDataStream().new MedianFinder();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {
        //思路一：大小顶堆
        //首先明确中位数的概念与整体元素的大小无关
        //只是排序后的中间元素的平均值或中间元素的值，也就是说只与奇偶数有关
        //大顶堆存较小的半部分元素，小顶堆存较大的半部分元素
        PriorityQueue<Integer> minQ;
        PriorityQueue<Integer> maxQ;
        double mid;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            minQ = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            maxQ = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
        }

        public void addNum(int num) {
            if (maxQ.isEmpty()) {
                maxQ.add(num);
                mid = num;
                return;
            }
            if (minQ.isEmpty()) {
                int temp = maxQ.poll();
                minQ.add(Math.min(temp, num));
                maxQ.add(Math.max(temp, num));
                mid = ((double)maxQ.peek() +minQ.peek()) / 2;
                return;
            }
            //两边元素相同
            if (minQ.size() == maxQ.size()) {
                if (num < minQ.peek()) {
                    int temp = minQ.poll();
                    minQ.add(num);
                    maxQ.add(temp);
                } else {
                    maxQ.add(num);
                }
                mid = maxQ.peek();
            }
            //两边元素不同
            else {
                if (num < maxQ.peek()) {
                    minQ.add(num);
                } else {
                    int temp = maxQ.poll();
                    maxQ.add(num);
                    minQ.add(temp);
                }
                mid =  ((double)maxQ.peek() + minQ.peek()) / 2;
            }
        }

        public double findMedian() {
            return mid;
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)

}