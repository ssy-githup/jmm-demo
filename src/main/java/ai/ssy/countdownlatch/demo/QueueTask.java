package ai.ssy.countdownlatch.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:
 * @Date: 2020-04-19
 * @Description: 信号量的使用
 */
public class QueueTask implements Runnable{

    private CountDownLatch countDownLatch;

    public QueueTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(CountDownLatchDemo.printDate() + " 开始排队，进入队列等待");
            Thread.sleep(10000);
            System.out.println(CountDownLatchDemo.printDate() + " 排队结束，可以开始交费");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
