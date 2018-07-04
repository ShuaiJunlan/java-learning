package cn.shuaijunlan.lanqiao.$2017;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:45 2017/3/18.
 */
public class $05 {
    public static float getV(float a) {
        int temp = (int) (a * 1000);
        temp += 5;
        temp = temp / 10;
        return temp / 100.0f;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        float rate = scanner.nextFloat();
        int stages = scanner.nextInt();
        float sum = 10000.0f;       //  本金
        float re = 0.0f;            //  存储最后的结果
        float min = Float.MAX_VALUE;    //  表示最后差额
        float begin = getV(sum / stages);        //  起始还款金额
        while (true) {
            sum = 10000.0f;
            begin = getV(begin + 0.01f);
            for (int i = 0; i < stages; i++) {
                sum = getV(sum * rate / 12 / 100) + sum;
                sum -= begin;
            }
            if (sum > 0 && sum < min) {
                re = begin;
                min = sum;
            } else if (sum == 0) {
                re = begin;
                break;
            } else if (sum < 0) {
                if ((-sum) < min) {
                    re = begin;
                }
                break;
            }

        }
        System.out.println((int) (re * 100));
        System.out.println(getV(10000.0f / 24) + 0.01);
    }

}
