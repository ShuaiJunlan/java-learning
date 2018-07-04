package cn.shuaijunlan.nowcoder.$2017.$04.$18;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 19:27 2017/4/18.
 */
public class App2 {
    public static void main(String[] args) {
        HashSet<Long> hashSet = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        Long temp;
        while (scanner.hasNext()) {
            temp = scanner.nextLong();
            if (temp == 0)
                break;
            else
                hashSet.add(temp);
        }
        System.out.println(hashSet.size());
    }
}
