package cn.shuaijunlan.lanqiao.Test;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 17:39 2017/3/22.
 */
public class App8 {
    public static void main(String[] args) {
        for (int i = 1156; true; i++) {
            char[] num = String.valueOf(i).toCharArray();
            get(num);
            System.out.println(i);
        }
    }

    public static void get(char[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += Math.pow((nums[i] - '0'), 2);
//            System.out.println(count);
        }
        int j = 5;
        while (count != 1) {
            nums = String.valueOf(count).toCharArray();
            count = 0;

            for (int i = 0; i < nums.length; i++) {
                count += Math.pow((nums[i] - '0'), 2);
            }
            System.out.println(count);
//            j--;
        }

    }

}
