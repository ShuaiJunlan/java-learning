package cn.shuaijunlan.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 11:05 2018/3/29.
 */
public class Master implements Watcher {
    ZooKeeper zooKeeper;
    String hostPort;

    Master(String hostPort){
        this.hostPort = hostPort;
    }
    void startZk() throws IOException {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }
    @Override
    public void process(WatchedEvent event) {
        System.out.println("event:" + event);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Master master = new Master("139.199.210.120:2182,139.199.210.120:2181,139.199.210.120:2183");
        master.startZk();

        Thread.sleep(60000);

    }
}
