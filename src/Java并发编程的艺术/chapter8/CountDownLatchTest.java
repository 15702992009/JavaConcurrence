package TheArtOfJavaConcurrentProgrammingDemo.chapter8;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch c=new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                c.countDown();
                System.out.println("2");
                c.countDown();
            }
        }).start();
        c.await(); //阻塞主线程 通过c.await()
        System.out.println("3");
    }
}
