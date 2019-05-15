package cn.shuaijunlan.java.basic.learning.io.test;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:29 AM 5/15/19.
 */
public class GenerateDataFileTest {
    private final long GB = 1024 * 1024 * 1024;
    @Test
    public void generate1GBDataFile() throws IOException {
        Path path = Paths.get(Config.READ_FILE_NAME);

        if (Files.exists(path)){
            Files.delete(path);
        }

        FileChannel channel = new FileOutputStream(Files.createFile(path).toFile()).getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // StringBuilder str = new StringBuilder();

        String str = "aitjdnfikuaweyrioqweuyriuqweyriquweyram8juydsndfbi475y93485y93485uy9q328457y9234875ytakejfhakejrthiuweruyhgt5982345y923485y98345y3489ty98734-dsnlehrtq09u45jfnfsmdnfkasjhrkwejrhlahfkasnfksdfhbkasjhraw;erkj;awelrjlqwekhrlashdfkasjdhfkiajshriqwuyriqwurhaisdhf";
        str += str;
        str += str;
        System.out.println(str.getBytes().length);

        byteBuffer.put(str.getBytes());

        for (int i = 0; i < 5 * GB / 1024; i++){
            byteBuffer.flip();
            channel.write(byteBuffer);
        }

    }
}
