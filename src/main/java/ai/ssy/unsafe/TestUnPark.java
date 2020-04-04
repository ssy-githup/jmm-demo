package ai.ssy.unsafe;

import ai.ssy.volatileDemo.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * @ClassName TestUnPark
 * @Description: TODO
 * @Author Administrator
 * @Date 2020/4/4
 * @Version V1.0
 **/
public class TestUnPark {
    public static void main(String[] args) {
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
        System.out.println("执行开始");


        TestUnsafe testUnsafe = new TestUnsafe();
        Thread currThread =  new Thread(testUnsafe);
        unsafe.unpark(currThread);
        unsafe.unpark(currThread);
        unsafe.unpark(currThread);
        unsafe.park(true,1000L);
        currThread.start();



        System.out.println("执行结束");
    }
}
class TestUnsafe implements Runnable{

    @Override
    public void run() {

        System.out.println("testUnsafe执行开始");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testUnsafe执行结束");
    }
}