package cn.llf.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 */
@Slf4j
public class TreadEndTest {

    public static void main(String[] arg){
        Thread thread = new Thread(new SubThread());
        thread.run();
        log.warn(">> 主线程结束");
    }
}

@Slf4j
class  SubThread implements Runnable{

    @Override
    public void run() {
        log.warn(">> 子线程开始执行");

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn(">> 子线程结束");

    }
}