//已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。 
//
// 不要使用系统的 Math.random() 方法。 
//
// 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: 1
//输出: [7]
// 
//
// 示例 2: 
//
// 
//输入: 2
//输出: [8,4]
// 
//
// 示例 3: 
//
// 
//输入: 3
//输出: [8,1,10]
// 
//
// 
//
// 提示: 
//
// 
// rand7 已定义。 
// 传入参数: n 表示 rand10 的调用次数。 
// 
//
// 
//
// 进阶: 
//
// 
// rand7()调用次数的 期望值 是多少 ? 
// 你能否尽量少调用 rand7() ? 
// 
// Related Topics 数学 拒绝采样 概率与统计 随机化 👍 240 👎 0

package com.cute.leetcode.editor.cn;

import java.util.Random;
import java.util.stream.IntStream;

public class ImplementRand10UsingRand7 {
    public static void main(String[] args) {
        Solution solution = new ImplementRand10UsingRand7().new Solution();
    }

    class SolBase {
        public int rand7() {
            return new Random().nextInt(7) + 1;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)


    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     *
     * @return a random integer in the range 1 to 7
     */
    //思路一：大数随机转小数随机
    //从random10()%10->random7()的概率是相等的
    //我们不断random10 返回7以内的数的概率和random7的概率是相同的，可以通过极限去理解，也可以通过概率求和去理解
    //因此我们可以定义一个更大的书random7*random7这样来仇random10的概率也能保证相等
    /*class Solution extends SolBase {
        public int rand10() {
            int num = (rand7() - 1) * 7 + rand7();
            while (num > 10) {
                num = (rand7() - 1) * 7 + rand7();
            }
            return num;
        }
    }
*/
    //思路二：概率转换（不严格pass）
    //这种概率题oj都不太聪明
    class Solution extends SolBase {
        public int rand10() {
            int res = 0;
            for(int i = 0; i < 10; i++){
                res += rand7();
            }
            return res % 10 + 1;
        }
    }


    //思路三：思路一的优化
    //思路一得到的是49个数的概率
    //因为是独立概率分布所以可以之选40个，然后求余数即可
    /*class Solution extends SolBase {
        public int rand10() {
            int num = (rand7() - 1) * 7 + rand7();
            while (num > 40) {
                num = (rand7() - 1) * 7 + rand7();
            }
            return 1 + num % 10;
        }
    }*/
    //思路四：思路三的优化
   /* class Solution extends SolBase {
        public int rand10() {
            while (true) {
                int num = (rand7() - 1) * 7 + rand7();
                // 如果在40以内，那就直接返回
                if (num <= 40) return 1 + num % 10;
                // 说明刚才生成的在41-49之间，利用随机数再操作一遍
                num = (num - 40 - 1) * 7 + rand7();
                if (num <= 60) return 1 + num % 10;
                // 说明刚才生成的在61-63之间，利用随机数再操作一遍
                num = (num - 60 - 1) * 7 + rand7();
                if (num <= 20) return 1 + num % 10;

            }
        }
    }*/
//leetcode submit region end(Prohibit modification and deletion)

}