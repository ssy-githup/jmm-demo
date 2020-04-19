package ai.ssy.reentrantLockTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:ssy
 * @Date:
 * @Description: ReentrantLock公平锁/非公平锁
 */
public class ReentrantLockTest {
    //默认是公平锁：强调公平性；线程执行按照先来后到的
 //   private static Lock lock = new ReentrantLock();
//    private static Lock lock = new ReentrantLock(true);
    //false：非公平锁；执行lock()时直接抢占锁；提高了系统的吞吐量和并发性
    //可能会造成：线程优先级颠倒和线程饥饿
  private static Lock lock = new ReentrantLock(false);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo(i)).start();
        }
    }

    static class ThreadDemo implements Runnable {
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 2; i++) {
                lock.lock();
                System.out.println("获得锁的线程：" + id);
                lock.unlock();
            }
        }
    }
}


/**
 * 公平锁的结果：
 * 获得锁的线程：3
 * 获得锁的线程：2
 * 获得锁的线程：0
 * 获得锁的线程：1
 * 获得锁的线程：4
 * 获得锁的线程：3
 * 获得锁的线程：2
 * 获得锁的线程：0
 * 获得锁的线程：1
 * 获得锁的线程：4
 *
 * 非公平锁的结果：
 * 获得锁的线程：3
 * 获得锁的线程：3
 * 获得锁的线程：1
 * 获得锁的线程：1
 * 获得锁的线程：0
 * 获得锁的线程：0
 * 获得锁的线程：2
 * 获得锁的线程：2
 * 获得锁的线程：4
 * 获得锁的线程：4
 */

