package cn.shuaijunlan.java.basic.learning.io.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:19 AM 5/15/19.
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 0)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Threads(1)
@Fork(0)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SequentialReadFileTest {
    private static Path path = Paths.get(Config.READ_FILE_NAME);
    private static File file = null;
    private final long KB = 1024;
    private final long MB = 1024 * 1024;

    @Param({"32", "128", "512", KB + "", 4 * KB + "", 8 * KB + "", MB + "", 4 * MB + "", 16 * MB + ""})
    private int size;

    @Setup
    public void prepare(){
        file = path.toFile();
    }
    @Benchmark
    public void test1MMPRead() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = accessFile.getChannel();
        byte[] buffer = new byte[size];
        long length = file.length();
        MappedByteBuffer mappedByteBuffer;
        while (length > 0){
            if (length > Integer.MAX_VALUE){
                mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, file.length() - length, Integer.MAX_VALUE);
                length -= Integer.MAX_VALUE;
            }else {
                mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, file.length() - length, length);
                length -= length;
            }
            while (mappedByteBuffer.hasRemaining() && mappedByteBuffer.remaining() >= size){
                mappedByteBuffer.get(buffer);
            }
        }
    }

    @Benchmark
    public void test2FileChannelRead() throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        // ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(size);
        while (channel.read(byteBuffer) != -1){
            byteBuffer.clear();
        }

    }

    @Benchmark
    public void test3FileInputStreamRead() throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[size];
        while (inputStream.read(buffer) != -1){
        }

    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(SequentialReadFileTest.class.getSimpleName())
                // .output("Benchmark.log")
                .build();
        new Runner(options).run();
    }
}