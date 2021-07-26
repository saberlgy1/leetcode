package com.cute.leetcode.editor.cn;

/**
 * @program: leetcode
 * @description: 251周赛
 * @author: lgy
 * @create: 2021-07-25 12:27
 **/

public class TestContest251 {
    public static void main(String[] args) {
        System.out.println(getLucky("leetcode", 1));
        System.out.println(maximumNumber("5",new int[]{1,4,7,5,3,2,5,6,9,4}));
    }

    /**
     * 给你一个由小写字母组成的字符串 s ，以及一个整数 k 。
     * <p>
     * 首先，用字母在字母表中的位置替换该字母，将 s 转化 为一个整数（也就是，'a' 用 1 替换，'b' 用 2 替换，... 'z' 用 26 替换）。接着，将整数 转换 为其 各位数字之和 。共重复 转换 操作 k 次 。
     * <p>
     * 例如，如果 s = "zbax" 且 k = 2 ，那么执行下述步骤后得到的结果是整数 8 ：
     * <p>
     * 转化："zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
     * 转换 #1：262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
     * 转换 #2：17 ➝ 1 + 7 ➝ 8
     * 返回执行上述操作后得到的结果整数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "iiii", k = 1
     * 输出：36
     * 解释：操作如下：
     * - 转化："iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
     * - 转换 #1：9999 ➝ 9 + 9 + 9 + 9 ➝ 36
     * 因此，结果整数为 36 。
     * 示例 2：
     * <p>
     * 输入：s = "leetcode", k = 2
     * 输出：6
     * 解释：操作如下：
     * - 转化："leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
     * - 转换 #1：12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
     * - 转换 #2：33 ➝ 3 + 3 ➝ 6
     * 因此，结果整数为 6 。
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-digits-of-string-after-convert
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param k
     *
     * @return
     */
    public static int getLucky(String s, int k) {

        char[] chars = s.toCharArray();
        String value = "";
        for (int i = 0; i < chars.length; i++) {
            value += (chars[i] - 96);
        }
        for (int i = 0; i < k; i++) {
            int sum = 0;
            for (int j = 0; j < value.length(); j++) {
                sum += Integer.valueOf(value.substring(j, j + 1));
            }
            value = String.valueOf(sum);
        }
        return Integer.valueOf(value);
    }

    /**
     * 给你一个字符串 num ，该字符串表示一个大整数。另给你一个长度为 10 且 下标从 0  开始 的整数数组 change ，该数组将 0-9 中的每个数字映射到另一个数字。更规范的说法是，数字 d 映射为数字 change[d] 。
     * <p>
     * 你可以选择 突变  num 的任一子字符串。突变 子字符串意味着将每位数字 num[i] 替换为该数字在 change 中的映射（也就是说，将 num[i] 替换为 change[num[i]]）。
     * <p>
     * 请你找出在对 num 的任一子字符串执行突变操作（也可以不执行）后，可能得到的 最大整数 ，并用字符串表示返回。
     * <p>
     * 子字符串 是字符串中的一个连续序列。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：num = "132", change = [9,8,5,0,3,6,4,2,6,8]
     * 输出："832"
     * 解释：替换子字符串 "1"：
     * - 1 映射为 change[1] = 8 。
     * 因此 "132" 变为 "832" 。
     * "832" 是可以构造的最大整数，所以返回它的字符串表示。
     * 示例 2：
     * <p>
     * 输入：num = "021", change = [9,4,3,5,7,2,1,9,0,6]
     * 输出："934"
     * 解释：替换子字符串 "021"：
     * - 0 映射为 change[0] = 9 。
     * - 2 映射为 change[2] = 3 。
     * - 1 映射为 change[1] = 4 。
     * 因此，"021" 变为 "934" 。
     * "934" 是可以构造的最大整数，所以返回它的字符串表示。
     * 示例 3：
     * <p>
     * 输入：num = "5", change = [1,4,7,5,3,2,5,6,9,4]
     * 输出："5"
     * 解释："5" 已经是可以构造的最大整数，所以返回它的字符串表示。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= num.length <= 105
     * num 仅由数字 0-9 组成
     * change.length == 10
     * 0 <= change[d] <= 9
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/largest-number-after-mutating-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param num
     * @param change
     *
     * @return
     */
    public static String maximumNumber(String num, int[] change) {
        StringBuilder res = new StringBuilder();
        int begin = 0;
        while (begin< num.length()) {
            int val = Integer.parseInt(num.substring(begin, begin + 1));
            if (val < change[val]) {
                break;
            }
            begin++;
            res.append(val);
        }
        while (begin < num.length()) {
            int val = Integer.parseInt(num.substring(begin, begin + 1));
            if (val <= change[val]) {
                res.append(change[val]);
                begin++;
            } else {
                break;
            }
        }
        while (begin < num.length()) {
            res.append(num.substring(begin, begin + 1));
            begin++;
        }
        return res.toString();
    }
}
