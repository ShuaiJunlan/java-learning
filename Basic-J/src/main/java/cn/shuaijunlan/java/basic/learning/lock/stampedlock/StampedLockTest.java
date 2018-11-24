package cn.shuaijunlan.java.basic.learning.lock.stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:21 PM 11/24/18.
 *
 * TODO: http://ifeve.com/jdk8%E4%B8%ADstampedlock%E5%8E%9F%E7%90%86%E6%8E%A2%E7%A9%B6/
 *  http://www.importnew.com/14941.html
 *  https://colobu.com/2016/06/01/Java-8-StampedLocks-vs-ReadWriteLocks-and-Synchronized/
 */
public class StampedLockTest {
    private double x, y;

    private final StampedLock stampedLock = new StampedLock();

    void move(double deltaX, double deltaY){
        long stamp = stampedLock.writeLock();
        try {
            y += deltaY;
            x += deltaX;
        }finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin(){
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x, currentY = y;

        if (!stampedLock.validate(stamp)){
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            }finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(Math.pow(currentX, 2) + Math.pow(currentY, 2));
    }

    void moveIfAtOrigin(double newX, double newY){
        long stamp = stampedLock.readLock();

        try {
            while (x == 0.0 && y == 0.0){
                long ws = stampedLock.tryConvertToWriteLock(stamp);

                if (ws != 0L){
                    stamp = ws;
                    x = newX;
                    y = newY;
                    break;
                }else {
                    stampedLock.unlockRead(stamp);
                    stamp = stampedLock.writeLock();
                }
            }
        }finally {
            stampedLock.unlock(stamp);
        }
    }
}
