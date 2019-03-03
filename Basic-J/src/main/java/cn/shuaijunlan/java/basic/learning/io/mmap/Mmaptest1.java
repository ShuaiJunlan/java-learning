package cn.shuaijunlan.java.basic.learning.io.mmap;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:51 PM 12/8/18.
 */
public class Mmaptest1 {
    public static void main(String[] args) throws IOException {
        File file = new File("test");
        System.out.println(file.length());
        assert file.exists() || file.createNewFile();
        FileChannel channel = new FileInputStream(file).getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

        System.out.println(mappedByteBuffer.position());
        System.out.println(file.length());

        for (int i = 0; i < 1000; i++){
            System.out.println(mappedByteBuffer.get());
        }
    }

    @Test
    public void test1() throws IOException {
        File file = new File("test1");
        assert  file.exists() || file.createNewFile();
        FileChannel fileChannel = new FileOutputStream(file).getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());
    }
}
