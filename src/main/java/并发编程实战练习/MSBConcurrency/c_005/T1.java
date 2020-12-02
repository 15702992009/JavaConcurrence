/**
 * 分析一下这个程序的输出
 没有synchronized
 THREAD0 count = 174
 THREAD1 count = 174
 THREAD4 count = 274
 THREAD5 count = 374
 THREAD8 count = 474
 THREAD9 count = 574
 THREAD2 count = 674
 THREAD7 count = 774
 THREAD3 count = 874
 THREAD6 count = 974
 974
 有synchronized
 THREAD0 count = 100
 THREAD2 count = 200
 THREAD1 count = 300
 THREAD4 count = 400
 THREAD6 count = 500
 THREAD8 count = 600
 THREAD7 count = 700
 THREAD5 count = 800
 THREAD3 count = 900
 THREAD9 count = 1000
 1000
 * @author mashibing
 */

package 并发编程实战练习.MSBConcurrency.c_005;

public class T1 implements Runnable {

	private int count = 0;
	
	public /*synchronized*/ void run() {
		for (int i = 0; i < 100; i++) {
			count++;
		}
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) throws InterruptedException {
		T1 t = new T1();
		for(int i=0; i<10; i++) {
			Thread thread = new Thread(t, "THREAD" + i);
			thread.start();
		}
		while (Thread.activeCount()>2);
		System.out.println(t.count);
	}
	
}
