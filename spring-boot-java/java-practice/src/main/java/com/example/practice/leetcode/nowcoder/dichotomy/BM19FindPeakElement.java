package com.example.practice.leetcode.nowcoder.dichotomy;

/**
 * @author fzy
 * @description:
 * @date 2024/10/22 14:58
 */
public class BM19FindPeakElement {

    /*
    描述
    给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
    1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
    2.假设nums[-1] = nums[n] = 负无穷
    3.对于所有有效的i都有nums[i] != nums[i+1]
    4.你可以使用O(logN)的时间复杂度实现此问题吗?
    数据范围：
    如输入[2,4,1,2,7,8,4]时，会形成两个山峰，
    一个是索引为1，峰值为4的山峰，
    另一个是索引为5，峰值为8的山峰
     */
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // 往值大的方向移动指针
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
