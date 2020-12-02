package Java并发编程的艺术.chapter8;

import java.util.concurrent.*;

public class CallableImpl implements Callable {
    @Override
    public Object call() throws Exception {
        int sum=0;
        for (int i = 0; i < 10; i++) {
            sum+=i;
        }
        return sum;
    }

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Future submit = threadPoolExecutor.submit(new CallableImpl());
        try {
            Object o = submit.get();
           System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();

    }
}
