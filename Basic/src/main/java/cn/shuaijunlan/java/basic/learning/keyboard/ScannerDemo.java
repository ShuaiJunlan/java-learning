package cn.shuaijunlan.java.basic.learning.keyboard;

import java.util.Scanner;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:32 PM 8/20/19.
 */
public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            System.out.println(scanner.next());
            System.out.println(scanner.nextLine());
        }
    }
}
