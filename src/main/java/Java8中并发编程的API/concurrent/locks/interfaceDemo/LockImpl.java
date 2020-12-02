package Java8中并发编程的API.concurrent.locks.interfaceDemo;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockImpl {
    private static Lock lock = new ReentrantLock();

    /**
     *
     */
    @Test
    public void lock() {
        lock.lock();
        try {
            //业务逻辑
        } finally {
            lock.unlock();
        }
    }

}
