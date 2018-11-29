package cn.shuaijunlan.java.basic.learning.concurrent.forkjoin;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

// import java.util.concurrent.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:01 PM 11/29/18.
 */
public class ForkJoinTest extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public ForkJoinTest(int start, int end){
        this.start = start;
        this.end = end;
    }
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //computing 1 + 2 + 3 + 4 + ... + 1000
        //if the value too large, got:
        //TODO: java.lang.NoClassDefFoundError: Could not initialize class java.util.concurrent.locks.AbstractQueuedSynchronizer$Node
        // The problem is that your sort algorithm is broken.
        // This is causing a StackOverFlowError and because the stack is exhausted this is often being mis-reported as a ClassDefNotFoundError.
        ForkJoinTest forkJoinTest = new ForkJoinTest(1, 5);
        Future<Integer> re = forkJoinPool.submit(forkJoinTest);

        try {
            System.out.println(re.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }finally {
            if (forkJoinTest.isCompletedAbnormally()){
                forkJoinTest.getException().printStackTrace();
            }
            forkJoinPool.shutdown();
        }
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute){
            for (int i = start; i <= end; i++){
                sum += i;
            }
        }else {
            int middle = start + (end - start) >> 1;
            ForkJoinTest left = new ForkJoinTest(start, middle);
            ForkJoinTest right = new ForkJoinTest(middle + 1, end);

            //executing sub-task
            left.fork();
            right.fork();

            //waiting for sun-task finishing, and getting result
            int leftResult = left.join();
            int rightResult = right.join();

            //add
            sum = leftResult + rightResult;
        }
        return sum;
    }

}
