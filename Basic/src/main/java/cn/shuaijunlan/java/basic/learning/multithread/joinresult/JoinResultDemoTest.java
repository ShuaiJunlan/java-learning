package cn.shuaijunlan.java.basic.learning.multithread.joinresult;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 9:23 AM 2018/09/08.
 */
public class JoinResultDemoTest {
    /**
     * Getting joined result by future
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test1() throws InterruptedException, ExecutionException {
        //recording main thread starting time
        Long start = System.currentTimeMillis();
        //creating a fixed pool
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //result list
        List<Integer> list = new ArrayList<>(10);
        //future list
        List<Future<Integer>> futures = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            futures.add(executorService.submit(new CallableTask(i + 1)));
        }
        //get result time
        Long getResultStart = System.currentTimeMillis();
        System.out.println("Getting joined result starting time: " + new Date());
        while (futures.size() > 0){
            Iterator<Future<Integer>> iterator = futures.iterator();
            while (iterator.hasNext()){
                Future<Integer> future = iterator.next();
                if (future.isDone() && !future.isCancelled()){
                    Integer i = future.get();
                    System.out.println("Task " + i + " executing finish, and removing it from future list! " + new Date());
                    list.add(i);
                    iterator.remove();
                }else {
                    Thread.sleep(1);
                }
            }
        }
        executorService.shutdown();
        System.out.println("Result list:" + list);
        System.out.println("Total spending time: " + (System.currentTimeMillis() - start) + ", getting joined result spending time:" + (System.currentTimeMillis() - getResultStart));
    }

    /**
     * FutureTask
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void test2() throws InterruptedException, ExecutionException {
        //creating a fixed thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //future task list
        List<FutureTask<Integer>> list = new ArrayList<>(10);
        //result list
        List<Integer> integerList = new ArrayList<>(10);
        //main thread starting time
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++){
            FutureTask<Integer> futureTask = new FutureTask<>(new CallableTask(i + 1));
            executorService.submit(futureTask);
            list.add(futureTask);
        }
        //get joined result starting time
        long getResultStart = System.currentTimeMillis();
        while (list.size() > 0){
            Iterator<FutureTask<Integer>> iterator = list.iterator();
            while (iterator.hasNext()){
                FutureTask<Integer> futureTask = iterator.next();
                if (futureTask.isDone() && !futureTask.isCancelled()){
                    Integer i = futureTask.get();
                    integerList.add(i);
                    iterator.remove();
                }else {
                    Thread.sleep(1);
                }
            }
        }
        executorService.shutdown();
        System.out.println("Result list:" + integerList);
        System.out.println("Total spending time: " + (System.currentTimeMillis() - start) + ", getting joined result spending time:" + (System.currentTimeMillis() - getResultStart));
    }

    /**
     * CompletableService
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        Long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Integer> list = new ArrayList<>(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            futureList.add(completionService.submit(new CallableTask(i+1)));
        }
        //getting joined result method one
        for (Future<Integer> future : futureList){
            Integer result = future.get();
            list.add(result);
        }
        //getting joined result method two
        for (int i = 0; i < 10; i++){
            Integer result = completionService.take().get();
            list.add(result);
        }
        executorService.shutdown();
    }

    /**
     * CompletableFuture
     */
    @Test
    public void test4(){
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<CompletableFuture<String>> completableFutures = new ArrayList<>();
        // final List<Integer> taskList = Lists
        // HashSet
    }

}

/**
 * CallableTask
 */
class CallableTask implements Callable<Integer>{
    private Integer i;
    public CallableTask(Integer i){
        super();
        this.i = i;
    }

    @Override
    public Integer call() throws Exception {
        if (i == 1){
            Thread.sleep(3000);
        }else if (i == 5){
            Thread.sleep(5000);
        }else {
            Thread.sleep(1000);
        }
        System.out.println("Task thread: " + Thread.currentThread().getName() + " executing task :" + i + ", finishing at time: " + new Date());
        return i;
    }
}

