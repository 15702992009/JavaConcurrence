/**
 * ThreadLocal线程局部变量
 * 
 * @author 马士兵
 */
package CodingPractice.ThreadLocalDemo;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
	//volatile保证线程可见性
	volatile static Person p = new Person();
	
	public static void main(String[] args) {
				
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//线程查看改变
			System.out.println(p.name);
		}).start();

		//本线程改变p.name值看另外一个线程能否感知到改变
		new Thread(()->{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p.name = "lisi";
		}).start();
	}
}

class Person {
	String name = "zhangsan";
}
