package cn.shuaijunlan.jmh.learning;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 5:14 PM 1/7/19.
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Threads(2)
@Fork(2)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StringBuilderBenchmark {
    @Benchmark
    public void testStringAdd() {
        // System.out.println(1);
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        print(a);
    }

    @Benchmark
    public void testStringBuilderAdd() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        print(sb.toString());
    }

    private void print(String a) {
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StringBuilderBenchmark.class.getSimpleName())
                // .output("Benchmark.log")
                .build();
        new Runner(options).run();
    }
}
