package cn.shuaijunlan.disruptor.learning;


import com.lmax.disruptor.EventHandler;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:26 PM 5/21/19.
 */
public class LongEventHandler implements EventHandler<LongEvent> {


    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("Thread: " + Thread.currentThread().getName() + ", Event: " + longEvent);
    }
}
