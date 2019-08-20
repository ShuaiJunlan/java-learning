package cn.shuaijunlan.java.basic.learning.object.size;

import sun.misc.Contended;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:09 AM 5/24/19.
 */
public class LongObjectContended {
    @Contended
    private long a = 10L;
}
