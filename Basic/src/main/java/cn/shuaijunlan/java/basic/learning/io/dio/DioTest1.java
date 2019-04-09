package cn.shuaijunlan.java.basic.learning.io.dio;

import moe.cnkirito.kdio.DirectIOUtils;
import net.smacke.jaydio.DirectIoLib;
import net.smacke.jaydio.DirectRandomAccessFile;
import org.junit.Test;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:22 PM 4/8/19.
 *
 * using jaydio lib
 */
public class DioTest1 {
    @Test
    public void test1() throws IOException {
        int bufferSize = (1<<23); // Use 8 MiB buffers
        byte[] buf = new byte[bufferSize];

        DirectRandomAccessFile fin =
                new DirectRandomAccessFile(new File("hello.txt"), "r", bufferSize);

        DirectRandomAccessFile fout =
                new DirectRandomAccessFile(new File("world.txt"), "rw", bufferSize);

        while (fin.getFilePointer() < fin.length()) {
            int remaining = (int)Math.min(bufferSize, fin.length()-fin.getFilePointer());
            fin.read(buf,0,remaining);
            fout.write(buf,0,remaining);
        }

        fin.close();
        fout.close();

    }

    @Test
    public void test2() throws IOException {
        int bufferSize = 1 << 12; // Use 8 MiB buffers
        byte[] buf = new byte[bufferSize];

        for (int i = 0; i < bufferSize; i++) {
            buf[i] = (byte)i;
        }


        DirectRandomAccessFile fout =
                new DirectRandomAccessFile(new File("world.txt"), "rw", bufferSize);
        // for (int i = 0; i < bufferSize; i++){
            fout.write(buf, 0, bufferSize);
            // fout.writeInt(i);
        // }

        fout.close();

    }

    @Test
    public void test3() throws IOException {
        File file = new File("world.txt");
        // File file = new File("database.test");
        System.out.println(file.exists());
        System.out.println(file.length());
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = accessFile.getChannel();
        MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getInt());
    }
}
