package cn.llf.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eleven
 * @date 2018/12/25
 * @description
 */
@Slf4j
public class AsyncThread {

    public static final Object lock = new Object();

//    public static void main(String[] arg){
//        Object lock = new Object();
//        MyThread myThread = new MyThread(lock,"t1");
//        synchronized (myThread){
//            myThread.start();
//            try {
//                myThread.wait(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("主线程继续执行");
//        }
//    }

    public static void main(String[] args){
        MyThread t1 = new MyThread("t1");
        //启动“线程1”
        log.info("主线程启动异步线程");
        t1.start();
        synchronized (lock) {

            try {
                lock.wait(3000);
                log.info("主线程继续执行");
                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
