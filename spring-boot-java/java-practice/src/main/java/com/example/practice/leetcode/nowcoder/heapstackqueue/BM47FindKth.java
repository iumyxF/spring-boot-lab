package com.example.practice.leetcode.nowcoder.heapstackqueue;

/**
 * @author fzy
 * @description:
 * @date 2024/11/27 9:16
 */
public class BM47FindKth {

    /*
    有一个整数数组，请你根据快速排序的思路，找出数组中第k大的数。
    给定一个整数数组a,同时给定它的大小n和要找的k，请返回第k大的数（包括重复的元素，不用去重），保证
    答案存在。
    要求：时间复杂度O(nlogn),空间复杂度O(1)
    数据范围：0 <= n <= 1000, 1 <= K <= n,数组中每个元素满足 0 <= val <= 10000000

    输入：[1,3,5,2,2],5,3
    输出：2

    输入：[10,10,9,9,8,7,5,6,4,3,4,2],12,3
    输出：9
    说明：去重后的第3大是8，但本题要求包含重复的元素，不用去重，所以输出9
     */
    public int findKth(int[] a, int n, int k) {
        quickSort(a, 0, n - 1);
        return a[n - k];
    }

    public static void main(String[] args) {
        BM47FindKth s = new BM47FindKth();
        int[] nums = new int[]{10, 10, 9, 9, 8, 7, 5, 6, 4, 3, 4, 2};
        //s.quickSort(nums, 0, nums.length - 1);
        //System.out.println(Arrays.toString(nums));

        System.out.println(s.findKth(nums, 12, 3));

    }

    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        // 哨兵划分
        int pivot = partition(nums, left, right);
        // 递归左子数组、右子数组
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    public int partition(int[] nums, int left, int right) {
        // 选取三个候选元素的中位数并将中位数交换至数组最左端
        int med = medianThree(nums, left, (left + right) / 2, right);
        swap(nums, left, med);
        // 以 nums[left] 为基准数
        int i = left, j = right;
        while (i < j) {
            while (i < j && nums[j] >= nums[left]) {
                // 从右向左找首个小于基准数的元素
                j--;
            }
            while (i < j && nums[i] <= nums[left]) {
                // 从左向右找首个大于基准数的元素
                i++;
            }
            // 交换这两个元素
            swap(nums, i, j);
        }
        // 将基准数交换至两子数组的分界线
        swap(nums, i, left);
        // 返回基准数的索引
        return i;
    }

    public int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left], m = nums[mid], r = nums[right];
        if ((l <= m && m <= r) || (r <= m && m <= l)) {
            // m 在 l 和 r 之间
            return mid;
        }
        if ((m <= l && l <= r) || (r <= l && l <= m)) {
            // l 在 m 和 r 之间
            return left;
        }
        return right;
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
