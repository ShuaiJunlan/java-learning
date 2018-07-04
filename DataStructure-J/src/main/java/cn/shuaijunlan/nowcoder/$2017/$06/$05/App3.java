package cn.shuaijunlan.nowcoder.$2017.$06.$05;

import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:56 2017/6/5.
 */
public class App3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        long c = scanner.nextLong();
        if (getNum(n , m , c))
            System.out.println("No");
        else
            System.out.println("Yes");
    }
    public static boolean getNum(long n,long m, long c){
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (i * j > i * i || i * j < j * j)
                    count++;
        count = 1;
        if (count * c % 2 == 0)
            return true;
        return false;

    }
}
