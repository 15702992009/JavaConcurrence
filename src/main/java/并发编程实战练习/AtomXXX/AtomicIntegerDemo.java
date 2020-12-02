package 并发编程实战练习.AtomXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  * 解决同样的问题的更高效的方法，使用AtomXXX类
 *  * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 */
public class AtomicIntegerDemo {
    AtomicInteger atomicInteger=new AtomicInteger(0);
    public void m(){
        for (int i = 0; i < 10000; i++) {
            atomicInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();
        List<Thread> threads = new ArrayList<Thread>(100);
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(atomicIntegerDemo::m));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(atomicIntegerDemo.atomicInteger);
    }
}
