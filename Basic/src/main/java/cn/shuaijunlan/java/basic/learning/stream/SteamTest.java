package cn.shuaijunlan.java.basic.learning.stream;

import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:20 AM 12/27/19.
 */
public class SteamTest {
    @Test
    public void streamReduceTest() {
        String str = "hello everyone, my name is Junlan";
        Stream.of(str.split(" ")).reduce(new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                return null;
            };
        });
    }
}
