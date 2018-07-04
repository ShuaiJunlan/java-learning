package cn.shuaijunlan.nowcoder.$2017.$03.$23;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:37 2017/3/23.
 */
public class App1 {
    public static boolean isBalanceNum(char[] arr) {
        int length = arr.length;
        int zero = 0;
        int multi = 1;
        for (int i = 0; i < length; i++) {
            if (arr[i] == '0')
                zero++;
            multi *= (arr[i] - '0');
        }
        if (zero == 1)
            return false;
        if (zero >= 2)
            return true;
        int temp = arr[0] - '0';
        for (int i = 1; i < length; i++) {
            if ((multi / (float) temp) == (float) temp) {
                return true;
            }
            temp *= (arr[i] - '0');
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            char[] arr = String.valueOf(a).toCharArray();
            if (isBalanceNum(arr))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
