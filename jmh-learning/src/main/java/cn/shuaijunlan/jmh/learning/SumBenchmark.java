package cn.shuaijunlan.jmh.learning;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:22 PM 1/7/19.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class SumBenchmark {
    @Param({"10000", "100000", "1000000"})
    private int length;

    private int[] numbers;
    private Calculator singleThreadCalc, multiThreadCalc;
    @Setup
    public void prepare(){
        numbers = IntStream.rangeClosed(1, length).toArray();
        singleThreadCalc = new SinglethreadCalculator();
        multiThreadCalc = new MultithreadCalculator(Runtime.getRuntime().availableProcessors());
    }

    @TearDown
    public void shutDown(){
        singleThreadCalc.shutDown();
        multiThreadCalc.shutDown();
    }
    @Benchmark
    public long singleThreadBench(){
        return singleThreadCalc.sum(numbers);
    }
    @Benchmark
    public long multiThreadBench(){
        return multiThreadCalc.sum(numbers);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(SumBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(2)
                .build();
        Collection<RunResult> results = new Runner(options).run();
        // ResultExporter.exportResult("单线程与多线程求和性能", results, "length", "微秒");
    }
}
class SinglethreadCalculator implements Calculator{

    @Override
    public long sum(int[] numbers) {
        if (numbers == null || numbers.length == 0){
            return 0;
        }
        long re = 0;
        for (int i : numbers){
            re += i;
        }
        return re;
    }

    @Override
    public void shutDown() {

    }
}
class MultithreadCalculator implements Calculator{
    private int threadNum;
    private ExecutorService executorService;
    private static CountDownLatch countDownLatch;
    private static long[] res;

    MultithreadCalculator(int threadNum){
        this.threadNum = threadNum;
        executorService = Executors.newFixedThreadPool(threadNum);
        countDownLatch = new CountDownLatch(threadNum);
        res = new long[threadNum];
    }

    @Override
    public long sum(int[] numbers) {
        for (int i = 0; i < threadNum; i++){
            executorService.submit(new Task(
                    i * (numbers.length%threadNum),
                    (i == threadNum-1) ? numbers.length : (i+1) * (numbers.length%threadNum),
                    numbers, i));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (threadNum > 1){
            for (int i = 1; i < threadNum; i++){
                res[0] += res[i];
            }
        }
        return res[0];
    }

    @Override
    public void shutDown() {
        executorService.shutdown();
    }
    static class Task extends Thread{
        private int start, end, k;
        private int[] nums;
        long re = 0;
        Task(int start, int end, int[] nums, int k){
            this.start = start;
            this.end = end;
            this.nums = nums;
            this.k = k;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++){
                re += nums[i];
            }
            res[k] = re;
            countDownLatch.countDown();
        }
    }
}

