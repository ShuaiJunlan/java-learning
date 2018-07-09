package cn.shuaijunlan.lanqiao.$2016.JAVA_A;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 18:29 2017/3/3.
 */
public class $10 {
    private static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    public static void compressNum(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        for (int i = 0; i < arr.length; i++) {
            System.out.println(isContainKey(arr[i]));
            if (isContainKey(arr[i])) {
                int temp = arr[i];              //  使用一个临时变量
                arr[i] = getDiffs(arr[i], i);
                setValue(temp, i);
            } else {
                hashMap.put(arr[i], i);
                arr[i] = -arr[i];
            }

        }
    }

    public static boolean isContainKey(int key) {
        for (int a : hashMap.keySet()) {
            if (a == key)
                return true;
        }
        return false;
    }

    public static void setValue(int key, int value) {
        for (Map.Entry<Integer, Integer> a : hashMap.entrySet()) {
            if (a.getKey().equals(key))
                a.setValue(value);
        }
    }

    public static int getDiffs(int key, int value) {
        int count = 0;
        int start = 0;
        for (Map.Entry<Integer, Integer> a : hashMap.entrySet()) {
            if (a.getKey().equals(key)) {
                start = a.getValue();
                System.out.println(start);
                break;
            }
        }
        for (Map.Entry<Integer, Integer> a : hashMap.entrySet()) {
            if (start < a.getValue() && a.getValue() < value) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] arr ={1,2,2,1,2};
        int[] arr = {1, 1, 2, 3, 2, 3, 1, 2, 2, 2, 3, 1};
        compressNum(arr);
        for (int i : arr)
            System.out.println(i);
    }
}
