package future;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by mazhenhua on 2017/3/8.
 */
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = reentrantReadWriteLock.readLock();
    private static Lock writeLock = reentrantReadWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException{
        try{
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException{
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
            System.out.println(value);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunable = new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println(demo.handleRead(readLock));
                    //demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        final Runnable writeRunable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(writeLock, new Random().nextInt());
                    //demo.handleWrite(lock, new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        for (int i = 0; i < 6; i ++){
            new Thread(readRunable).start();
        }

        for (int i = 6; i < 10; i ++){
            new Thread(writeRunable).start();
        }
    }

}
