package Jdk8ConcurrentApi.concurrent.thread_interrupted;

/**
 * 综上所述，我们分别介绍了不同种线程的不同状态下对于中断请求的反应。
 * NEW和TERMINATED对于中断操作几乎是屏蔽的，
 * RUNNABLE和BLOCKED类似，对于中断操作只是设置中断标志位并没有强制终止线程，对于线程的终止权利依然在程序手中。
 * WAITING/TIMED_WAITING状态下的线程对于中断操作是敏感的，他们会抛出异常并清空中断标志位。
 */
public class MyThread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        // 1 NEW
        /*Thread thread = new MyThread();
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(thread.isInterrupted());*/

        // 2 TERMINATED
        /*Thread thread = new MyThread();
        thread.start();
        thread.join();
        System.out.println(thread.getState());
        thread.interrupt();
        System.out.println(thread.isInterrupted());*/
        //从上述的两个例子来看，对于处于new和terminated状态的线程对于中断是屏蔽的，也就是说中断操作对这两种状态下的线程是无效的


        // 3 RUNNABLE
        //可以看到在我们启动线程之后，线程状态变为RUNNABLE，中断之后输出中断标志，显然中断位已经被标记，但是当我们再次输出线程状态的时候发现，线程仍然处于RUNNABLE状态。很显然，处于RUNNBALE状态下的线程即便遇到中断操作，也只会设置中断标志位并不会实际中断线程运行。那么问题是，既然不能直接中断线程，我要中断标志有何用处？
        /*Thread thread = new MyThread();
        thread.start();
        System.out.println(thread.getState());
        thread.interrupt();
        Thread.sleep(1000);//等到thread线程被中断之后
        System.out.println(thread.isInterrupted());
        System.out.println(thread.getState());*/

        // 4 BLOCKED
        //当线程处于BLOCKED状态说明该线程由于竞争某个对象的锁失败而被挂在了该对象的阻塞队列上了。那么此时发起中断操作不会对该线程产生任何影响，依然只是设置中断标志位
        /*Thread thread1=new MyThreadBLOCKED();
        thread1.start();
        Thread thread2=new MyThreadBLOCKED();
        thread2.start();
        Thread.sleep(1000);
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
        thread2.interrupt();
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.getState());*/

        //5 WAITING/TIMED_WAITING


    }






    /*@Override
    public void run() {
        while (true) {
            //do nothing
        }
    }*/
    //修改MyThread类的run方法
    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("exit MyThread"); //线程退出后中断位置位false
                System.out.println("线程Running时中断位还存在, TERMINATED后中断位就清除了");
                break;
            }
        }
    }



    static class MyThreadBLOCKED extends Thread{

        public synchronized static void doSomething(){
            while(true){
                //do something
            }
        }
        @Override
        public void run(){
            doSomething();
        }
    }
}

