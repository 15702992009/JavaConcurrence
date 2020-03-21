/**
 * synchronized关键字
 * 对某个对象加锁
 * @author mashibing
 */

package CodingPractice.yxxy.c_004;

public class T {

	private static int count = 10;
	
	public synchronized static void m() { //这里等同于synchronized(yxxy.c_004.T.class)
		count--;
		System.out.println(Thread.currentThread().getName() + " count = " + count);
	}
	
	public static void mm() {
		synchronized(T.class) { //考虑一下这里写synchronized(this)是否可以？ 不可以,static方法锁定的是类,this是指向实例对象的指针,这里没有实例对象.只能锁定类
			count --;
		}
	}

}
