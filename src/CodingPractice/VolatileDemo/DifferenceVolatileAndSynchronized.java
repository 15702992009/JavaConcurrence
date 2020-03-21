package CodingPractice.VolatileDemo;
/**
 * 对比上一个程序，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性
 * @author mashibing
 */

import java.util.ArrayList;
import java.util.List;


public class DifferenceVolatileAndSynchronized {
	/*volatile*/ int count = 0;

	synchronized void m() { 
		for (int i = 0; i < 10000; i++)
			count++;
	}

	public static void main(String[] args) {
		DifferenceVolatileAndSynchronized t = new DifferenceVolatileAndSynchronized();

		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m, "thread-" + i));
		}

		threads.forEach((o) -> o.start());

		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.count);

	}

}
