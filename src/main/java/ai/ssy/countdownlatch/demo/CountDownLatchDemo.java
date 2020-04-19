package ai.ssy.countdownlatch.demo;
import	java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:
 * @Date: 2020-04-19
 * @Description: 信号量的使用
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception{
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        // 建议使用ThreadPoolExecutor创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            executorService.execute(new SeeDoctorTask(countDownLatch));
            executorService.execute(new QueueTask(countDownLatch));
            // 等待其他线程完成各自的工作后再执
            countDownLatch.await();
            System.out.println(printDate() + " 搞定，回家！！！总共耗时:"+(System.currentTimeMillis()-now));
        } finally {
            executorService.shutdown();
        }
    }

    public static String printDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date()) + " ";
    }
}
