package cn.shuaijunlan.java.basic.learning.ipv6;

import org.junit.Test;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 8:21 PM 11/14/18.
 */
public class Ipv6Test {
    @Test
    public void test1() {
        System.setProperty("java.net.preferIPv6Addresses", "true");
        try {
            InetAddress address = Inet6Address.getLocalHost();
            System.out.println(address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        InetAddress localIP;
        try {
            localIP = InetAddress.getLocalHost();
            if (localIP instanceof Inet6Address) {
                System.out.println("IPV6");
            } else if (localIP instanceof Inet4Address) {
                System.out.println("IPV4");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() throws UnknownHostException {
        System.setProperty("java.net.preferIPv6Addresses", "true");
        System.out.println(InetAddress.getByName("www.google.com"));
        System.out.println(InetAddress.getByName("www.google.com"));
        System.setProperty("java.net.preferIPv6Addresses", "true");
        System.out.println(InetAddress.getByName("www.hust.edu.cn"));
        System.out.println(InetAddress.getByName("0x1996.org"));
        System.out.println(InetAddress.getByName("www.baidu.com"));
    }
}
