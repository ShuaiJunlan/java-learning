package cn.shuaijunlan.java.basic.learning.keyboard;

import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:24 PM 8/20/19.
 */
public class SystemReadDemo {
    public static void main(String[] args) {
        System.out.println("Enter a char.");
        while (true){
            char val = 0;
            try {
                val = (char)System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(val);
        }
    }
}
