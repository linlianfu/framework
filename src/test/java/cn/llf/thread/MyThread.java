package cn.llf.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2018/12/25
 * @description
 */
@Slf4j
public class MyThread extends Thread {

    Object lock;

    String threadName;

    MyThread(String threadName){
        this.threadName = threadName;
    }

    @Override
    public void run() {
        log.info("异步任务【{}】开始执行，设置延时5S",threadName);
//        while (true);
        synchronized (this){
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           notify();
        }
        log.info("异步任务结束，唤醒主线程");
    }
}
