package com.example.practice.leetcode.nowcoder.dichotomy;

/**
 * @author fzy
 * @description:
 * @date 2024/10/23 9:48
 */
public class BM20InversePairs {

    /*
    在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
    输入一个数组，求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。即输出 P mod 1000000007

    数据范围：对于50%的数据，size <= 10的4次方
    对于100%的数据，size <= 10的5次方
    数组中所有数字的值满足0 <= val <= 10的9次方

    要求：空间复杂度O(n),时间复杂度O(nlogn)

    输入: [1,2,3,4,5,6,7,0]
    输出: 7

    输入: [1,2,3]
    输出: 0
     */

    /**
     * 暴力
     * 超时了
     */
    public int inversePairsTimeout(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int res = 0;
        int maxValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 剪枝
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j]) {
                    res++;
                }
            }
        }
        return res;
    }

    int res = 0;

    public int inversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return res;
    }

    public void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        doSort(arr, left, mid, right);
    }

    private void doSort(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int index = 0;
        int l = left;
        int r = mid + 1;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[index++] = arr[l++];
            } else {
                // mid - l + 1 ==> 左区间内，有多少个大于arr[l]的元素,他们都大于arr[r]，所以是逆序对
                res = (res + (mid - l + 1)) % 1000000007;
                temp[index++] = arr[r++];
            }
        }
        while (l <= mid) {
            temp[index++] = arr[l++];
        }
        while (r <= right) {
            temp[index++] = arr[r++];
        }
        for (int k = 0; k < temp.length; k++) {
            arr[left + k] = temp[k];
        }
    }

    public static void main(String[] args) {
        BM20InversePairs s = new BM20InversePairs();
        //System.out.println(s.inversePairs(new int[]{1, 2, 3, 4, 5, 6, 7, 0}));
        //System.out.println(s.inversePairs(new int[]{1, 2, 3}));
        System.out.println(s.inversePairs(new int[]{1, 8, 4, 7, 2}));
    }
}
