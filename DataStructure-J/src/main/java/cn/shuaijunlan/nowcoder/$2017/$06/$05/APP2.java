package cn.shuaijunlan.nowcoder.$2017.$06.$05;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:36 2017/6/5.
 */
public class APP2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        int length = str.length();
        boolean isLock = false;
        int count = 0;
        for (int i = 0; i < length; i++){
            int temp = str.charAt(i);
            if (97 <= temp && temp <= 122 && !isLock) count++;
            else if (97 <= temp && temp <= 122 && isLock) {
                count++;
                i--;
                isLock = false;
            }
            else if (!isLock) {
                count++;
                i--;
                isLock = true;
            }
            else {
                count++;
            }
        }
        System.out.println(count);
    }
}
