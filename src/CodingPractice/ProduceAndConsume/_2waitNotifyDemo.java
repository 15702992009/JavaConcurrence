package CodingPractice.ProduceAndConsume;
/**
 * 使用wait和notify/notifyAll来实现生产者/消费者模型
 * 多个生产消费者线程练习
 */

import java.util.LinkedList;


public class _2waitNotifyDemo<T> {
    private LinkedList<T> arrayList = new LinkedList<>();


    //消费者
    public synchronized T get() {
        //容器为空线程等待
        while (arrayList.size() == 0) {  //用while不用if是因为,假如消费者线程2阻塞在this.await之后,生产者线程生产后,消费者线程3被唤醒并消费了产品,
            // 此时容器还是0,然后此时消费者线程2又重新争夺到锁,但此时容器为空,再执行get操作是,就会产生异常.while的作用在于,当该线程被挂起后重新获得执行权时需要重新判断条件,因为生产者或消费者最后调用的notify()会通知所有
            //在实例对象上挂起的线程,此时并不知道谁被唤醒,换句话说,例如,消费者线程A 判断容器为空挂起,消费者线程B进来,也判断为空挂起,生产者线程进来生产->消费者线程B获得执行权,消费容器->消费者A线程又进来(此时容器为空),所以需要while从新判断条件
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        System.out.println(Thread.currentThread().getName() + "消费");
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
        System.out.println(Thread.currentThread().getName()+ t);
        this.notifyAll();
    }

    public static void main(String[] args)   {
        _2waitNotifyDemo<String> w = new _2waitNotifyDemo<String>();
        //消费者线程创建
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                        w.get();
                }
            }, "消费者线程" + i).start();
        }

        //生产者
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                        w.put("生产");
                }
            }, "生产者线程" + i).start();
        }

    }
}
