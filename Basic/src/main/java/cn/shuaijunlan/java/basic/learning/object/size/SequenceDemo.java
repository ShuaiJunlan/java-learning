package cn.shuaijunlan.java.basic.learning.object.size;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:14 AM 5/24/19.
 */
class LhsPadding
{
    protected long p1, p2, p3, p4, p5, p6, p7;
}

class Value extends LhsPadding
{
    protected volatile long value;
}

class RhsPadding extends Value
{
    protected long p9, p10, p11, p12, p13, p14, p15;
}
public class SequenceDemo extends RhsPadding{
    static final long INITIAL_VALUE = -1L;
    private static final Object OBJECT = new Object();
    private static final long VALUE_OFFSET = 1L;
}
