package cn.shuaijunlan.java.basic.learning.keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:26 PM 8/20/19.
 */
public class BufferedReaderDemo {
    public static void main(String[] args) {

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Enter a string:");
                String string = bufferedReader.readLine();
                System.out.println(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
