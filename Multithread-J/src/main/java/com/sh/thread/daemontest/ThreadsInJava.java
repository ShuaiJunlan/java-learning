package com.sh.thread.daemontest;

/**
 * @author Junlan Shuai[shuaijunlan@gmail.com].
 * @date Created on 10:32 AM 2018/07/06.
 */
class UserThread extends Thread {
    @Override
    public void run() {
        System.out.println("This is a user thread.....");
    }
}

class DaemonThread extends Thread {
    public DaemonThread() {
        setDaemon(true);
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("This is daemon thread....." + i);
        }
    }
}

public class ThreadsInJava {
    public static void main(String[] args) {
        DaemonThread daemon = new DaemonThread();   //Creating the DaemonThread

        daemon.start();                 //Starting the daemon thread

        UserThread userThread = new UserThread();   //Creating the UserThread

        userThread.start();          //Starting the user thread
    }
}
