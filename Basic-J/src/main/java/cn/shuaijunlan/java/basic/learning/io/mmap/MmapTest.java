package cn.shuaijunlan.java.basic.learning.io.mmap;


import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:03 PM 12/8/18.
 */
public class MmapTest {
    private static final long GB = 1024 * 1024 * 1024;
    public static void main(String[] args) throws IOException {
        File file = new File("data.test");
        System.out.println(file.length());
        assert file.exists() || file.createNewFile();
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, GB * 1).load(); //load()
        System.out.println(file.length());
        for (int i = 0; i < 1000; i++){
            mappedByteBuffer.put((byte)i);
        }
        mappedByteBuffer.position(0);
        // for (int i = 0; i < file.length(); i++){
        //     System.out.println(mappedByteBuffer.get());
        // }
        file.delete();
    }

    @Test
    public void test() throws IOException {
        File file = new File("data.test");
        System.out.println(file.length());
        assert file.exists() || file.createNewFile();
        FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, GB * 1);
        System.out.println(file.length());
        for (int i = 0; i < 1000; i++){
            mappedByteBuffer.put((byte)i);
        }
        mappedByteBuffer.position(0);
        System.out.println(mappedByteBuffer.get());
        // file.delete();
    }
}
