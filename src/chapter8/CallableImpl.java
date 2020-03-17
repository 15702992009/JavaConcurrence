package chapter8;

import java.util.concurrent.*;

public class CallableImpl implements Callable {
    @Override
    public Object call() throws Exception {
        int sum=0;
        for (int i = 0; i < 10; i++) {
            sum+=i;
            TimeUnit.SECONDS.sleep(5);
        }
        return sum;
    }

    public static void main(String[] args) {
        new Thread(new RunnableImpl()).start();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        Future submit = threadPoolExecutor.submit(new CallableImpl());
        threadPoolExecutor.shutdown();

        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
