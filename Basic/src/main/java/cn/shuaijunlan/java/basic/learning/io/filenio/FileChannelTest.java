package cn.shuaijunlan.java.basic.learning.io.filenio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:55 PM 5/14/19.
 */
public class FileChannelTest {
    @Test
    public void testFileChannel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(""));
        fileInputStream.read();

        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        fileChannel.read(byteBuffer);
    }
}
