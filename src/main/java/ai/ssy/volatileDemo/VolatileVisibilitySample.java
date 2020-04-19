package ai.ssy.volatileDemo;

/**
 *
 */
public class VolatileVisibilitySample {
    private boolean initFlag = false;
    static Object object = new Object();

    public void refresh(){
        this.initFlag = true; //普通写操作，(volatile写)
        String threadname = Thread.currentThread().getName();
        System.out.println("线程："+threadname+":修改共享变量initFlag");
    }

    public void load(){
        String threadname = Thread.currentThread().getName();
        int i = 0;
        while (!initFlag){
            synchronized (object){
                i++;
            }
            //i++;
        }
        System.out.println("线程："+threadname+"当前线程嗅探到initFlag的状态的改变"+i);
    }

    public static void main(String[] args){
        VolatileVisibilitySample sample = new VolatileVisibilitySample();
        Thread threadA = new Thread(()->{
            sample.refresh();
        },"threadA");

        Thread threadB = new Thread(()->{
            sample.load();
        },"threadB");

        threadB.start();
        try {
             Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadA.start();
    }

}
