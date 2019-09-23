package cn.shuaijunlan.java.basic.learning.filepath;

import java.util.Objects;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:01 PM 9/18/19.
 */
public class FilePathTest {
    public static void main(String[] args) {
        System.out.println(FilePathTest.class.getResource("/").getPath());
        // System.out.println(Objects.requireNonNull(FilePathTest.class.getClassLoader().getResource("test")).getPath());
        System.out.println(Objects.requireNonNull(FilePathTest.class.getClassLoader().getResource("cn/shuaijunlan/java/basic/learning/filepath/test")).getPath());
    }
}
