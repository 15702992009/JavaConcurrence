package Java并发编程的艺术.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    private int i;

    public static void main(String[] args) {
        final Counter cas=new Counter();
        List<Thread> ts=new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        for (Thread t : ts) {
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicInteger);
        System.out.println(System.currentTimeMillis()-start);
    }
    /**
     * 线程安全
     */
    private void safeCount(){
        for (;;){
            int k=atomicInteger.get();
            boolean suc=atomicInteger.compareAndSet(k,++k);
            if (suc){
                break;
            }
        }
    }
    /**
     * 非线程安全
     */
    private void count(){
        i++;
    }
}
