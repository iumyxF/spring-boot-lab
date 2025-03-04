package com.example.practice.leetcode.nowcoder.dichotomy;

/**
 * @author fzy
 * @description:
 * @date 2024/10/24 10:40
 */
public class BM22Compare {

    /*
    牛客项目发布项日版本时会有版本号，比如1.02.11,2.14.4等等
    现在给你2个版本号version1和version2,请你比较他们的大小
    版本号是由修订号组成，修订号与修订号之间由一个"."连接。1个修订号可能有多位数字组成，修订号可能包含前导0,
    且是合法的。例如，1.02.11, 0.1, 0.2 都是合法的版本号
    每个版本号至少包含1个修订号。
    修订号从左到右编号，下标从0开始，最左边的修订号下标为0，下一个修订号下标为1，以此类推。
    比较规则：
    一.比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较忽略任何前导零后的整数
    值。比如"0.1"和"0.01"的版本号是相等的
    二.如果版本号没有指定某个下标处的修订号，则该修订号视为0。例如，"1.1"的版本号小于"1.1.1"。因为"1.1"的版本号
    相当于"1.1.0"，第3位修订号的下标为0，小于1
    三.version1>version2返回1，如果version1<version2返回-1，不然返回0.

    数据范围：
    1 <=version1.length,version2.length <=1000
    version1和version2的修订号不会超过int的表达范围，即不超过32位整数的范围
    进阶：空间复杂度O(1),时间复杂度O(n)
     */

    public int compare(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");

        int len = Math.max(split1.length, split2.length);
        for (int i = 0; i < len; i++) {
            String s1 = i > split1.length - 1 ? "0" : split1[i];
            String s2 = i > split2.length - 1 ? "0" : split2[i];
            int i1 = Integer.parseInt(s1);
            int i2 = Integer.parseInt(s2);
            if (i1 > i2) {
                return 1;
            } else if (i1 < i2) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String v1 = "0.226";
        String v2 = "0.36";
        BM22Compare s = new BM22Compare();
        System.out.println(s.compare(v1, v2));
    }
}
