package cn.llf.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2018/12/25
 * @description
 */
@Slf4j
public class MyThread extends Thread{

    String name;
    Object lock;

    MyThread(String name,Object lock){
        this.name = name;
        this.lock = lock;
    }

    /**
     * 测试线程和唤醒
     */
//    public void run(){
//        log.info("异步线程【{}】开始执行,延时5s",name);
//        try {
//            TimeUnit.SECONDS.sleep(10);
//            log.info("通知唤醒等待的线程");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //把代码块也整合到同步代块内，会导致该子线程会一直占用lock锁，
//        //导致主线程无法获取lock的对象锁，所以主线程设置lock.wait(5000)5S超时会无效
//        synchronized (lock){
//            log.info("异步线程执行结束");
//            //wait和notify等方法必须拥有的对象锁，否则会抛出 IllegalMonitorStateException异常，
//            // 表示当前线程没有拥有该对象锁，确调用过了wait和notify等方法
//            lock.notify();
//        }
//    }


    /**
     * 测试线程中断 - 正常中断
     */
//    @Override
//    public void run() {
//        log.info(">>>>>【子线程】开始执行");
//
//        for (int i=0;i<10000;i++){
//            if (!this.isInterrupted())
//            log.info(i+"");
//        }
//        log.info(">>>>>【子线程】运行结束");
//
//    }
    /**
     * 测试线程中断 - 阻塞中断异常
     *
     * 主线程试图停止一个正处于阻塞的子线程，系统除了会抛出InterruptedException异常外，还会调用interrupted()函数，
     * 调用时能获取到中断状态是true的状态，调用完之后会复位中断状态为false，
     * 所以异常抛出之后通过isInterrupted()是获取不到中断状态是true的状态，从而不能退出循环
     */
//    @Override
//    public void run() {
//        log.info(">>>>>【子线程】开始执行");
//        log.info("【子线程启动睡眠等待】");
////        for (int i=0; i<100;i++ ){
////            log.info(""+i);
////        }
//        while (!this.isInterrupted()){
//            //模拟子线程处于阻塞状态
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
////                break;
//            }
//        }
//        log.info(">>>>>【子线程】运行结束");
//    }

    /**
     * 测试线程中断-阻塞中断正常
     */
    @Override
    public void run() {
        log.info(">>>>>【子线程】开始执行");
        log.info(">>>>>【子线程】启动睡眠等待");
//        for (int i=0; i<100;i++ ){
//            log.info(""+i);
//        }
        while (!this.isInterrupted()){
            //模拟子线程处于阻塞状态
            try {
                logInfo();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                //try-catch之后，通过break正确停止子线程
//                e.printStackTrace();
                log.error(">>>>>阻塞中断异常,通过break正确停止子线程");
                break;
            }
        }
        log.info(">>>>>【子线程】运行结束");
    }


    private void logInfo() throws InterruptedException {
        log.info("测试不同方法同属一个线程id");
        TimeUnit.SECONDS.sleep(3);
    }
}
