package Java并发编程的艺术.chapter4;

import java.util.concurrent.TimeUnit;

public class Shutdown {
    public static void main(String[] args) throws Exception{

        Runner one=new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two=new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
        while (on&&!Thread.currentThread().isInterrupted()){
            i++;
        }
            System.out.println("Count i="+i);
        }
        public void cancel(){
            on=false;
        }
    }
}
