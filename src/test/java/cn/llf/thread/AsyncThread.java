package cn.llf.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2018/12/25
 * @description
 */
@Slf4j
public class AsyncThread {

    static Object lock = new Object();



//    @Test

    /**
     * 测试线程和唤醒
     */
//    public  static void main(String[] arg){
//        MyThread myThread = new MyThread("线程1",lock);
//        myThread.start();
//
//        synchronized (lock){
//            log.info("主线程执行");
//            try {
//                lock.wait(4000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info(">>>>>主线程继续执行");
//        }
//        Thread.currentThread().interrupt();
//    }

    /**
     * 测试线程中断 - 正常中断
     * @param arg
     */
//    public static void main(String[] arg){
//        Object lock = new Object();
//        MyThread myThread = new MyThread("线程中断测试",lock);
//
//        log.info(">>>>>【主线程】启动【子线程】");
//        myThread.start();
//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info(">>>>>【主线程】中断【子线程】");
//        myThread.interrupt();
//        log.info(">>>>>主线程运行结束");
//    }

    /**
     * 测试线程中断 - 阻塞中断异常
     * @param arg
     */
//    public static void main(String[] arg){
//        Object lock = new Object();
//        MyThread myThread = new MyThread("线程中断测试",lock);
//
//        log.info(">>>>>【主线程】启动【子线程】");
//        myThread.start();
//        try {
//            TimeUnit.MILLISECONDS.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info(">>>>>【主线程】中断【子线程】");
//        myThread.interrupt();
//        log.info("子线程异常停止之后，判断子线程是否还是活动状态，myThread.isAlive()：【{}】",myThread.isAlive());
//
//        if (myThread.isAlive()){
//            log.info("主线程调用中断子线程之后，子线程没有正常停止，异常发生！！！！！！！");
//        }
//        log.info(">>>>>主线程运行结束");
//    }
    /**
     * 测试线程中断 - 阻塞中断正常
     * @param arg
     */
    public static void main(String[] arg){
        Object lock = new Object();
        MyThread myThread = new MyThread("线程中断测试",lock);

        log.info(">>>>>【主线程】启动【子线程】");
        myThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(">>>>>【主线程】中断【子线程】");
        myThread.interrupt();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("子线程异常停止之后，判断子线程是否还是活动状态，myThread.isAlive()：【{}】",myThread.isAlive());

        if (myThread.isAlive()){
            log.info("主线程调用中断子线程之后，子线程没有正常停止，异常发生！！！！！！！");
        }
        log.info(">>>>>主线程运行结束");
    }
}
