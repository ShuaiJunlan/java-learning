package cn.shuaijunlan.guava.learning.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:15 AM 6/3/19.
 */
public class GuavaCacheTest {
    @Test
    public void test1() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();

        try {
            System.out.println(cache.get("name", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "ShuiJunlan";
                }
            }));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                return "ShuaiJunlan";
                            }
                        }
                );
        try {
            System.out.println(cache.get("name"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
