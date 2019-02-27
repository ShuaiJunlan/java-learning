package cn.shuaijunlan.java.basic.learning.volatilet;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:35 PM 2/27/19.
 */
public class Volatile1Test {
    public static volatile int race = 0;
    public static  void increase(){
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[ THREADS_COUNT ];
        for(int i=0; i<THREADS_COUNT; i++){
            threads[i] = new Thread(() -> {
                for(int j=0; j<10000; j++) {
                    increase();
                }
            });

            threads[i].start();
        }

        for (int j = 0; j < THREADS_COUNT; j++){
            threads[j].join();
        }
        System.out.println(race);
    }
}
