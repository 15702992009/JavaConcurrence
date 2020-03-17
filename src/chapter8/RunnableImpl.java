package chapter8;

public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println(i);
        }
    }
}
