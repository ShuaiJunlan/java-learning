package cn.shuaijunlan.java.basic.learning.gc;

import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:47 PM 3/20/19.
 * -Xmx30M -Xms30M -XX:NewSize=12M -XX:SurvivorRatio=10 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:log/gc.log
 *
 * http://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html
 */
public class Solution {
    private static final int _KB = 1024;
    public static void main(String[] args) throws IOException, InterruptedException {
        System.in.read();
        while (true){
            Thread.sleep(5);
            byte[] bytes = new byte[_KB];
        }
    }
}
