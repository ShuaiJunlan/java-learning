package cn.shuaijunlan.java.basic.learning.io.dio;

import moe.cnkirito.kdio.DirectIOLib;
import moe.cnkirito.kdio.DirectIOUtils;
import moe.cnkirito.kdio.DirectRandomAccessFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:50 PM 3/3/19.
 */
public class DioTest {
    // file path should be specific since the different file path determine whether your system support direct io
    public static DirectIOLib directIOLib = DirectIOLib.getLibForPath("/");
    // you should always write into your disk the Integer-Multiple of block size through direct io.
    // in most system, the block size is 4kb
    private static final int BLOCK_SIZE = 4 * 1024;

    @Test
    public void test() throws IOException {
        if (DirectIOLib.binit) {
            ByteBuffer byteBuffer = DirectIOUtils.allocateForDirectIO(directIOLib, 4 * BLOCK_SIZE);
            for (int i = 0; i < BLOCK_SIZE; i++) {
                byteBuffer.putInt(i);
            }
            byteBuffer.flip();
            DirectRandomAccessFile directRandomAccessFile = new DirectRandomAccessFile(new File("./database.test"), "rw");
            directRandomAccessFile.write(byteBuffer, 0);
        } else {
            throw new RuntimeException("your system do not support direct io");
        }
    }
    @Test
    public void test1() throws IOException {
        if (DirectIOLib.binit) {
            ByteBuffer byteBuffer = DirectIOUtils.allocateForDirectIO(directIOLib, 4 * BLOCK_SIZE);
            DirectRandomAccessFile directRandomAccessFile = new DirectRandomAccessFile(new File("./database.test"), "rw");
            directRandomAccessFile.read(byteBuffer, 0);
            byteBuffer.flip();
            for (int i = 0; i < BLOCK_SIZE; i++) {
                System.out.print(byteBuffer.getInt() + " ");
            }
        } else {
            throw new RuntimeException("your system do not support direct io");
        }
    }
}
