package cn.shuaijunlan.disruptor.learning;

import com.lmax.disruptor.EventFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:25 PM 5/21/19.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
