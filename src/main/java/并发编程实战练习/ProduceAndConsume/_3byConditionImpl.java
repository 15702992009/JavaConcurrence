package 并发编程实战练习.ProduceAndConsume;
/**
 * 生产消费者
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重点  线程争夺cpu权限的时候并不知道自己是消费者/生产者线程.每个线程争夺CPU机会是相同的所以在一个消费者线程消费完容器所有元素后,即使notifyAll生产者线程,但是并不能保证下一个获取CPU调度的线程是生产者,也有可能还是消费者
 * @param <T>
 */
public class _3byConditionImpl<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            // *重点
            // 线程争夺cpu权限的时候并不知道自己是消费者/生产者线程.每个线程争夺CPU机会是相同的所以在一个消费者线程消费完容器所有元素后,即使notifyAll生产者线程,但是并不能保证下一个获取CPU调度的线程是生产者,也有可能还是消费者
            while (lists.size() == MAX) { //想想为什么用while而不是用if？
                producer.await();
            }

            lists.add(t);
            ++count;
            consumer.signalAll(); //通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                consumer.await();
            }
            t = lists.removeFirst();
            count --;
            producer.signalAll(); //通知生产者进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        _3byConditionImpl<String> c = new _3byConditionImpl<>();
        //启动消费者线程
        for(int i=0; i<10; i++) {
            new Thread(()->{
                for(int j=0; j<5; j++) System.out.println(c.get());
            }, "c" + i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //启动生产者线程
        for(int i=0; i<2; i++) {
            new Thread(()->{
                for(int j=0; j<25; j++) c.put(Thread.currentThread().getName() + " " + j);
            }, "p" + i).start();
        }
    }
}
