package cn.shuaijunlan.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 12:22 2018/3/31.
 */
public class ZookeeperTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private final static String zk_url = "139.199.210.120:2182,139.199.210.120:2181,139.199.210.120:2183";

    private final static int time_out = 5000;

    public static void main(String[] args) throws IOException, InterruptedException {
        //初始化zk
        ZooKeeper zooKeeper=new ZooKeeper(zk_url, time_out, watchedEvent -> {
            Watcher.Event.KeeperState state = watchedEvent.getState();
            Watcher.Event.EventType type = watchedEvent.getType();
            if(Watcher.Event.KeeperState.SyncConnected == state){
                if(Watcher.Event.EventType.None == type){
                    //调用此方法测计数减一
                    countDownLatch.countDown();

                }
            }
        });
        //阻碍当前线程进行,除非计数归零
        countDownLatch.await();

        try {
            Stat stat = zooKeeper.exists("/gang", false);
            if (stat != null){
                zooKeeper.delete("/gang", -1);
            }
            //创建持久化节点
            zooKeeper.create("/gang","你好".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            //获取节点数据
            byte[] data = zooKeeper.getData("/gang", false, null);
            System.out.println(new String(data));
            //修改节点数据
            zooKeeper.setData("/gang","吕金刚".getBytes(),0);
            //删除节点数据
            zooKeeper.delete("/gang",-1);
            //创建临时节点 异步创建
            zooKeeper.create("/jingang", "临时节点".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, (i, s, o, s1) -> {
                System.out.println(o);
                System.out.println(i);
                System.out.println(s1);
                System.out.println(s);
            },"a");
            //获取临时节点数据
            byte[] jingangs = zooKeeper.getData("/jingang", false, null);
            System.out.println(new String(jingangs));
            //验证节点是否存在
            Stat exists = zooKeeper.exists("/jingang", false);
            System.out.println(exists);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zooKeeper.close();
    }
}
