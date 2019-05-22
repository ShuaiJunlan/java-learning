package cn.shuaijunlan.disruptor.learning;


import com.lmax.disruptor.WorkHandler;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:26 PM 5/21/19.
 */
public class LongEventConsumer implements WorkHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event) throws Exception {
        System.out.println("Thread: " + Thread.currentThread().getName() + ", Event: " + event);
    }
}
