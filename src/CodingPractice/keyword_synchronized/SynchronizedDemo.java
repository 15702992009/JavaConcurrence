package CodingPractice.keyword_synchronized;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * synchronized关键字的使用说明
 * 1. 方法锁:
 * 1. 静态方法
 * 2. 普通方法
 * 2. 代码块锁: 1. 也就是锁对象 synchronized(锁对象){}  线程必须获得锁对象才能继续执行,代码块锁和方法锁的区别
 */
public class SynchronizedDemo {
    Object object = new Object();

    //锁代码块,指定锁对象
    public void m() {

        System.out.println("ThreadName  " + Thread.currentThread().getName());
        synchronized (object) { //任何线程要执行下面的代码，必须先拿到object对象锁
            while (true) ;
        }

    }

    //锁定this对象
    public void m2() {
        synchronized (this) {

        }
    }

    //普通方法锁,锁this对象
    public synchronized void m3() {
    }

    //静态方法锁,锁this对象
    public static synchronized void m4() {
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                synchronizedDemo.m();
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            System.out.println(thread.getName() + " 的status " + thread.getState());
        }
    }

}
