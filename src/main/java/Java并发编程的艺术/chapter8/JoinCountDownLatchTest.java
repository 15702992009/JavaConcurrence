package Java并发编程的艺术.chapter8;

import java.util.concurrent.TimeUnit;

public class JoinCountDownLatchTest {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread2.start();
        long l = System.currentTimeMillis();
        thread.join();
        System.out.println(" parser1 finish");
        thread2.join();
        System.out.println(" parser2 finish");
        System.out.println(System.currentTimeMillis()-l);
    }
}
