package cn.shuaijunlan.nowcoder.$2018;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 6:51 PM 2018/07/19.
 */
public class SortOfMoveToTail {
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println(leastOfMoveTime(arr));
    }

    public static int leastOfMoveTime(int arr[])
    {
        int res=0;
        int n=arr.length;
        int oldArr[]=new int[n];
        for(int i=0;i<n;i++){
            //保存原数组
            oldArr[i]=arr[i];
        }
        //原数组排序
        Arrays.sort(arr);
        for(int i=0;i<n;i++) {
            if(oldArr[i]==arr[res]){
                res++;
            }
        }
        return n-res;
    }

}
