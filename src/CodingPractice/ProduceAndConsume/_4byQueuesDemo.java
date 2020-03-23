package CodingPractice.ProduceAndConsume;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 基于阻塞队列的生产者消费者模型
 */
public class _4byQueuesDemo<E> {
    LinkedBlockingQueue<E> linkedBlockingQueue = new LinkedBlockingQueue<>(10);

    public void produce(E e) {
        while (true) {
            try {

                linkedBlockingQueue.put(e);
                System.out.println(Thread.currentThread().getName() + " 生产了一个");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void consume() {
        while (true) {

            try {
                E poll = linkedBlockingQueue.poll();
                if (poll!=null)
                System.out.println(Thread.currentThread().getName() + " 消费了一个");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int value = 0;
        _4byQueuesDemo<Integer> object_4byQueuesDemo = new _4byQueuesDemo<>();
        //消费者线程
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                object_4byQueuesDemo.consume();
            }, "消费者线程" + i).start();
        }
        //生产者线程
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //随便生产
                    object_4byQueuesDemo.produce(6);
                }
            }, "生产者线程" + (100 - i)).start();
        }
    }
}
