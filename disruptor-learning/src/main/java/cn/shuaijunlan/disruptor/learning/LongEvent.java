package cn.shuaijunlan.disruptor.learning;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:25 PM 5/21/19.
 */
public class LongEvent {
    private long value;

    public void set(long value)
    {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
