package cn.shuaijunlan.basicalgorithm.sort.mergesort;

import java.util.Arrays;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:59 2017/3/5.
 */
public class MergeSort {
    public static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, mid, start, end);
        }
    }

    public static void merge(int[] arr, int mid, int start, int end) {
        int[] temp = new int[end - start + 1];
        int t = mid + 1;
        int m = start;
        int k = 0;
        while (m <= mid && t <= end) {
            if (arr[m] < arr[t]) {
                temp[k++] = arr[m++];
            } else
                temp[k++] = arr[t++];
        }
//        while (m <= mid)
//            temp[k++] = arr[m++];
        if (m <= mid)
            System.arraycopy(arr, m, temp, k, mid - m + 1);
//        while (t <= end)
//            temp[k++] = arr[t++];
        if (t <= end)
            System.arraycopy(arr, t, temp, k, end - t + 1);
        System.arraycopy(temp, 0, arr, start, temp.length);
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 8, 3, 1, 6, 9, 0, 5, 4};
        int[] nums1 = {2, 2, 2, 1, 1, 1, 1, 0, 0, 4};

        MergeSort.mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

        MergeSort.mergeSort(nums1, 0, nums1.length - 1);
        System.out.println(Arrays.toString(nums1));
    }
}
