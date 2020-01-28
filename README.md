# jmm-demo
## JMM代码示例
### 1.volatile 关键字无法保证原子性；
ai.ssy.VolatileAtomicSample;
十个线程对counter进行增加操作，最后结果是10000说明能保证原子性
### 2.测试在代码执行的时候存在指令重排
ai.ssy.VolatileReOrderSample;
