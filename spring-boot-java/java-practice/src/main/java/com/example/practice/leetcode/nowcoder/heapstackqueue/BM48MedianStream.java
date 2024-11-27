package com.example.practice.leetcode.nowcoder.heapstackqueue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2024/11/27 14:27
 */
public class BM48MedianStream {
    /*
    如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排
    序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中
    间两个数的平均值。我们使用insert()方法读取数据流，使用GetMedian()方法获取当前读取数据
    的中位数。

    数据范围：数据流中数个数满足1 <= n <= 1000，大小满足 1 <= val <= 1000
    进阶：空间复杂度O(n),时间复杂度O(nlogn)

    input: [5,2,3,4,1,6,7,0,8]
    output: "5.00 3.50 3.00 3.50 3.00 3.50 4.00 3.50 4.00 "
    explain: 数据流里面不断吐出的是5,2,3...,则得到的平均数分别为 5, (5+2)/2, 3 ,(3+4)/2 , 3,
     */

    List<Integer> list = new ArrayList<>();

    /**
     * 保证插入有序
     */
    public void insert(Integer num) {
        if (list.isEmpty()) {
            list.add(num);
        } else {
            int index = 0;
            for (; index < list.size(); index++) {
                if (list.get(index) >= num) {
                    break;
                }
            }
            list.add(index, num);
        }
    }

    public Double getMedia() {
        if (list.isEmpty()){
            return (double) 0;
        }
        int size = list.size();
        if (size % 2 == 0) {
            // 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
            return (double) (list.get(size / 2) + list.get(size / 2 - 1)) / 2;
        } else {
            return (double) (list.get(size / 2));
        }
    }


    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(30);
        //l.add(20);
        System.out.println((double) (l.get(l.size() / 2) + l.get((l.size() - 1) / 2)) / 2);
    }
}
