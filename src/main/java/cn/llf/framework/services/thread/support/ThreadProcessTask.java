package cn.llf.framework.services.thread.support;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eleven
 * @date 2019/3/18
 * @description
 */
@Slf4j
public class ThreadProcessTask implements Runnable {

    @Override
    public void run() {

        log.info(">>>>>线程任务开始执行",Thread.currentThread().getName());
    }
}
