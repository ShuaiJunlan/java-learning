package cn.shuaijunlan.disruptor.learning.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:50 AM 5/22/19.
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.MILLISECONDS)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class AddTestWithJMH {
    private static long MAX_VALUE = 500_000_000L;
    private long i = 0;
    private volatile long j = 0;
    private LongAdder longAdder = new LongAdder();

    private AtomicLong atomicLong = new AtomicLong(0);
    @Benchmark
    public void test1() {
        i = 0;
        // long start = System.currentTimeMillis();
        while (i < MAX_VALUE){
            i++;
        }
        // System.out.println(System.currentTimeMillis()-start + "hhhhh");
    }
    @Benchmark
    public void test2() {
        j = 0;
        while (j < MAX_VALUE){
            j++;
        }
    }
    @Benchmark
    public void test3() {
        i = 0 ;
        while (i < MAX_VALUE){
            synchronized (this){
                i++;
            }
        }
    }
    @Benchmark
    public void test4() {
        longAdder.reset();
        while (longAdder.longValue() < MAX_VALUE){
            longAdder.increment();
        }
    }
    @Benchmark
    public void test5() {
        atomicLong.set(0);
        while (atomicLong.getAndIncrement() < MAX_VALUE){
            // atomicLong.incrementAndGet();
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(AddTestWithJMH.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

    // Benchmark             Mode  Cnt     Score    Error  Units
    // AddTestWithJMH.test1  avgt   10   263.063 ± 55.879  ms/op
    // AddTestWithJMH.test2  avgt   10  3391.759 ± 24.336  ms/op
    // AddTestWithJMH.test3  avgt   10  9233.300 ± 61.204  ms/op
    // AddTestWithJMH.test4  avgt   10  4447.101 ± 65.937  ms/op
    // AddTestWithJMH.test5  avgt   10  3380.500 ±  6.368  ms/op
}
