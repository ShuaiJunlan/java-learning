package cn.shuaijunlan.java.basic.learning.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 7:28 PM 2018/08/04.
 */
public class Main {
    private static ServiceLoader<ILogService> services = ServiceLoader.load(ILogService.class);

    public static void main(String[] args) {
        Iterator<ILogService> iterator = services.iterator();
        while (iterator.hasNext()){
            iterator.next().warn("Hello SPI");
        }
    }
}
