package chapter6;

import java.util.concurrent.*;

public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle+1, end);
            leftTask.fork();
            rightTask.fork();
            int left = leftTask.join();
            int right = rightTask.join();
            sum = left + right;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 4);
        Future<Integer> submit = forkJoinPool.submit(countTask);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {

        }
    }
}
