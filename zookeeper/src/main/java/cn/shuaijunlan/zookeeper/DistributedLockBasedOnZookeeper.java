package cn.shuaijunlan.zookeeper;


import com.google.common.base.Strings;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 15:37 2018/3/31.
 */
public class DistributedLockBasedOnZookeeper {
    private String hostPort = "139.199.210.120:2182,139.199.210.120:2181,139.199.210.120:2183";
    private String lockNameSpace = "/myLock";
    private String nodeString = lockNameSpace + "/test1";
    private ZooKeeper zk;

    public  DistributedLockBasedOnZookeeper(){
        try {
            zk = new ZooKeeper(hostPort, 6000, event -> {
                System.out.println("Receive event " + event);
                if (Watcher.Event.KeeperState.SyncConnected == event.getState()){
                    System.out.println("Connection is established...");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ensureRootPath() throws InterruptedException {
        try {
            if (zk.exists(lockNameSpace, true) == null){
                zk.create(lockNameSpace, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private void watchNode(String nodeString, final Thread thread){
        try {
            zk.exists(nodeString, event -> {
                System.out.println("==" + event.toString());
                if (event.getType() == Watcher.Event.EventType.NodeDeleted){
                    System.out.println("There is a Thread released lock.....");
                    thread.interrupt();
                }
                try {
                    zk.exists(nodeString, true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * get Lock
     * @return
     */
    public boolean getLock() throws InterruptedException {
        String path = null;
        ensureRootPath();
        watchNode(nodeString, Thread.currentThread());
        while (true){
            try {
                path = zk.create(nodeString, "".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (KeeperException e) {
                System.out.println(Thread.currentThread().getName() + "getting Lock but can not get");
                Thread.sleep(5000);
            }
            if (!Strings.nullToEmpty(path).trim().isEmpty()){
                System.out.println(Thread.currentThread().getName() + " get Lock...");
                return true;
            }
        }
    }

    /**
     * release Lock
     */
    public void unlock(){
        try {
            zk.delete(nodeString, -1);
            System.out.println(Thread.currentThread().getName() + " release Lock...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService service = new ThreadPoolExecutor(10, 10, 1000L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 4; i++){
            service.execute(() -> {
                DistributedLockBasedOnZookeeper lockBasedOnZookeeper = new DistributedLockBasedOnZookeeper();
                try {
                    lockBasedOnZookeeper.getLock();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lockBasedOnZookeeper.unlock();
            });
        }
        service.shutdown();
    }
}
