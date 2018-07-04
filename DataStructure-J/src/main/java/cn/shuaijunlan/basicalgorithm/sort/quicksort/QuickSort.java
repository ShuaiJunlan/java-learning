package cn.shuaijunlan.basicalgorithm.sort.quicksort;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 16:00 2017/3/1.
 */

/**
 * 实现快排
 */
public class QuickSort {
//    public void swap(int[] num, int a, int b)
//    {
//        int temp = num[a];
//        num[a] = num[b];
//        num[b] = temp;
//    }
//    public int partition(int[] num, int start ,int end)
//    {
//        int key = num[start];
//        int keyIndex = start;
//        while (start < end)
//        {
//            /**
//             * 先从后到前遍历，找出一个比key小的值，下标为end
//             */
//            while (end > start && num[end] >= key)
//            {
//                end --;
//            }
//            /**
//             * 再从前到后遍历，找出一个比key大的值，小标为start
//             */
//            while (end > start && num[start] <= key)
//            {
//                start ++;
//            }
//            /**
//             * 交换这两个值
//             */
//            swap(num, start, end);
//        }
//        /**
//         * 交换keyIndex和start对应的值
//         */
//        swap(num, start, keyIndex);
//        return end;
//    }
//    public void quickSort(int[] num, int start, int end)
//    {
//        if (num == null || num.length == 0)
//            return;
//        if (start >= end)
//            return;
//        int index = partition(num, start, end);
//        quickSort(num, start, index-1);
//        quickSort(num, index+1, end);
//    }

    /**
     * 实现数组中两个数的交换
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(int[] arr, int start, int end) {
        int index = start;
        while (start < end) {
            while (start < end && arr[index] <= arr[end]) {
                end--;
            }
            while (start < end && arr[index] >= arr[start]) {
                start++;
            }
            swap(arr, start, end);
        }
        swap(arr, index, end);
        return end;
    }

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end)
            return;
        if (arr == null || arr.length == 0)
            return;
        int index = partition(arr, start, end);
        quickSort(arr, start, index - 1);
        quickSort(arr, index + 1, end);

    }


    public static void main(String[] args) {
//        int[] a = {2,4,2,7,8,9,24,5,7,8};
        int[] a = {11, 11, 11, 22, 11, 222, 11};
        QuickSort q = new QuickSort();
        q.quickSort(a, 0, a.length - 1);
        for (int b : a)
            System.out.println(b);
    }
}
