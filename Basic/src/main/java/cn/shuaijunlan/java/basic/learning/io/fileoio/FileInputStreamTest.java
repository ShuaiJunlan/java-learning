package cn.shuaijunlan.java.basic.learning.io.fileoio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:04 AM 4/16/19.
 */
public class FileInputStreamTest {
    @Test
    public void test1(){
        File file = new File("/tmp/FileInputStreamTest.txt");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        File file = new File("/tmp/FileInputStreamTest.txt");
        if (!file.exists()){
            try {
                assert file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(file);
            byte[] re = new byte[4];
            outputStream.write(re);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        String aa = "fasdf";
        System.out.println(aa.getBytes().length);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 256; i++){
            stringBuilder.append((char)i);
            System.out.println("i: " + (i+1) + " length: " + stringBuilder.toString().length());
        }
        System.out.println(stringBuilder.toString());
        System.out.println(stringBuilder.toString().getBytes().length);
        System.out.println(stringBuilder.toString().getBytes().length);
        System.out.println(stringBuilder.toString().length());
    }
}
