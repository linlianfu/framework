package cn.llf.framework.services.thread.impl;

import cn.llf.framework.services.thread.IStatisticsService;
import cn.llf.framework.services.thread.support.ThreadProcessTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author eleven
 * @date 2019/3/18
 * @description
 *
 * 线程此关闭参考文章：https://blog.csdn.net/u010456982/article/details/62887529
 */
@Slf4j
@Service("statisticsService")
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;


    @Override
    public String print(String message) {
        log.info("有活动的线程数:{}",taskExecutor.getActiveCount());
        for (int i = 0;i<=29;i++){
            taskExecutor.execute(new ThreadProcessTask() );
        }
        long completedTaskCount = taskExecutor.getThreadPoolExecutor().getCompletedTaskCount();
        log.info("已经执行完成的任务数:{}",completedTaskCount);
        log.info("有活动的线程数:{}",taskExecutor.getActiveCount());

        return "执行完毕";
    }
}
