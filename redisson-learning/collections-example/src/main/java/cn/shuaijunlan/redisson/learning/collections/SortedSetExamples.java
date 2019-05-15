package cn.shuaijunlan.redisson.learning.collections;

import org.redisson.Redisson;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

import java.util.Arrays;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:06 AM 5/7/19.
 */
public class SortedSetExamples {
    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);

        config.useSingleServer()
                .setAddress("redis://200.100.100.00:6379");

        RedissonClient redisson = Redisson.create(config);

        RSortedSet<String> sortedSet = redisson.getSortedSet("mySortedSet");
        sortedSet.add("1");
        sortedSet.add("2");
        sortedSet.add("3");

        for (String string : sortedSet) {
            // iteration through bulk loaded values
        }

        String firstValue = sortedSet.first();
        String lastValue = sortedSet.last();

        boolean removedValue = sortedSet.remove("1");
        sortedSet.removeAll(Arrays.asList("1", "2", "3"));
        sortedSet.containsAll(Arrays.asList("4", "1", "0"));

        redisson.shutdown();
    }
}
