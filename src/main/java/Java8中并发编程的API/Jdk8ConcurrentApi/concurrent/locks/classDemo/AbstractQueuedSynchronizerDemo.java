package Java8中并发编程的API.Jdk8ConcurrentApi.concurrent.locks.classDemo;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 队列同步器是用来构建锁或者其他同步组件的基础框架,它是用一个int成员变量来表示同步状态,通过内置的FIFO队列来完成资源获取线程的排队工作
 * 队列同步器的主要使用方式:
 * 创建一个内部类继承AQS,实现AQS的抽象方法来管理同步状态.在抽象方法的实现过程中免不了要改变同步状态,这时就需要同步器提供的3个方法(getState(),setState(),compareAndSetState())来进行操作
 */
public class AbstractQueuedSynchronizerDemo {
    private class Sync extends AbstractQueuedSynchronizer {
        protected Sync() {
            super();
        }

        /**
         * 独占式获取同步状态,实现该方法需要查询当前状态并判断同步状态是否符合预期,然后在进行CAS设置同步状态
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                //将当前线程设置为同步器的独占线程,同步器被当前线程所持有
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }else {
                return false;
            }
        }

        /**
         * 独占式释放同步状态,等待获取同步状态的线程将有机会获取同步状态
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        /**
         * 共享式获取同步状态
         * @param arg
         * @return
         */
        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        /**
         * 共享式释放同步状态
         * @param arg
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        /**
         * 判断同步器是否在独占模式下被线程占用,一般该方法表示是否被当前线程所独占
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }
    }

}
