/**
 * 分析一下这个程序的输出
 * THREAD1 count = 7
 * THREAD0 count = 7
 * THREAD2 count = 7
 * THREAD4 count = 6
 * THREAD3 count = 5
 * @author mashibing
 *
 */

package 并发编程实战练习.MSBConcurrency.c_005;

public class T2_volatile implements Runnable {

	private /*volatile*/ int count = 0;
	
	public /*synchronized*/ void run() {
		for (int i = 0; i < 200; i++) {
			count++;
			System.out.println(Thread.currentThread().getName() + " count = " + count);
		}

	}
	
	public static void main(String[] args) throws InterruptedException {
		T2_volatile t = new T2_volatile();
		for(int i=0; i<500; i++) {
			Thread thread = new Thread(t, "THREAD" + i);
			thread.start();
		}
		while (Thread.activeCount()>2);
		System.out.println(t.count);
	}
	
}
