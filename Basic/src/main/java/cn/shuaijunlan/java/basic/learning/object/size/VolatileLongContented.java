package cn.shuaijunlan.java.basic.learning.object.size;

import sun.misc.Contended;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:12 AM 5/24/19.
 */
@Contended
public class VolatileLongContented {
    public volatile long value = 0L;
    public long p1, p2, p3, p4, p5, p6; // 填充，可以注释后对比测试
}
