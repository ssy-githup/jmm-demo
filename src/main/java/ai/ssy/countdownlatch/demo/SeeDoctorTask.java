package ai.ssy.countdownlatch.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:
 * @Date: 2020-04-19
 * @Description: 信号量的使用
 */
public class SeeDoctorTask implements Runnable{

    private CountDownLatch countDownLatch;

    public SeeDoctorTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println(CountDownLatchDemo.printDate() + " 开始看大夫，大夫开始诊脉");
            Thread.sleep(5000);
            System.out.println(CountDownLatchDemo.printDate() + " 看大夫结束，大夫开药方子");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}
