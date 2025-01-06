package com.example.design.pattern.adapter_pattern.test02;

/**
 * @author iumyxF
 * @description: 二分查找实现
 * @date 2024/1/5 11:22
 */
public class BinarySearch {
    public int binarySearch(int[] array, int key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midVal = array[mid];
            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return 1; //找到元素返回1
            }
        }
        return -1;  //未找到元素返回-1
    }
}
