package Jdk8ConcurrentApi.concurrent.locks.interfaceDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition:监视器接口类似于Object中的监视器方法(wait(),notify(),notifyAll())
 */
public class ConditionImpl {
    static Lock lock = new ReentrantLock();
    //一般会将condition作为成员变量
    Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            condition.await();
        } finally {
            lock.unlock();
        }
    }

    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
