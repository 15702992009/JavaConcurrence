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

package CodingPractice.MSBConcurrency.c_005;

public class T implements Runnable {

	private int count = 10;
	
	public /*synchronized*/ void run() {
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void main(String[] args) throws InterruptedException {
		T t = new T();
		for(int i=0; i<5; i++) {
			Thread thread = new Thread(t, "THREAD" + i);
			thread.start();
		}
		while (Thread.activeCount()>2);
		System.out.println(t.count);
	}
	
}
