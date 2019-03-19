package cn.llf.framework.services.thread.support;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eleven
 * @date 2019/3/18
 * @description
 * 一个cpu执行双线程，本机电脑4核cpu，最多可执行8个线程
 */
@Slf4j
public class ThreadProcessTask implements Runnable {

    @Override
    public void run() {

        log.info(">>>>>线程[{}]开始执行任务",Thread.currentThread().getName());
    }
}
