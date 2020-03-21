package CodingPractice.produceAndConsume;
/**
 * 使用wait和notify/notifyAll来实现生产者/消费者模型
 * 两个个线程练习
 */

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


public class _1waitNotifyDemo<T> {
    private LinkedList<T> arrayList = new LinkedList<>();


    //消费者
    public synchronized T get() {
        //容器为空线程等待,这里可以用if,不过还是建议用while
        while (arrayList.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notify();

        return arrayList.removeFirst();

    }

    //生产者
    public synchronized void put(T t) {
        while (arrayList.size() == 10) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        arrayList.add(t);
        System.out.println("生产者线程生产 " + t);
        this.notifyAll();
    }

    public static void main(String[] args) {
        _1waitNotifyDemo<String> w = new _1waitNotifyDemo();
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("消费者线程消费了 " + w.get());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    w.put("A");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
