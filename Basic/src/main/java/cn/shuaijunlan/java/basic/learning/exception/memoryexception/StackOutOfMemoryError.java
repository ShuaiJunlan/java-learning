package cn.shuaijunlan.java.basic.learning.exception.memoryexception;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:49 PM 1/16/19.
 */
public class StackOutOfMemoryError {
    public static void main(String[] args) {
        while (true){
            new Thread(() -> {
                while (true){}
            }).start();
        }
    }
}
