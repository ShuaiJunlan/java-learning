package cn.shuaijunlan.java.basic.learning.io.fileoio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:04 AM 4/16/19.
 */
public class FileInputStreamTest {
    @Test
    public void test1(){
        File file = new File("FileInputStreamTest.txt");
        if (!file.exists()){
            try {
                assert file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            byte[] re = new byte[4];
            int a = inputStream.read(re);
            // inputStream.getChannel();
            System.out.println(a);
            System.out.println(new String(re));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
