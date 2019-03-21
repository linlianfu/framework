package cn.llf.framework.services.thread.impl;

import cn.llf.framework.services.thread.IStatisticsService;
import cn.llf.framework.services.thread.support.ThreadProcessTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class StatisticsServiceImpl implements IStatisticsService, ApplicationContextAware {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    ApplicationContext applicationContext;
    @Override
    public String print(String message) {
        log.info("有活动的线程数:{}",taskExecutor.getActiveCount());
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        for (int i = 0;i<=10;i++){
            log.info(">>>>>>>>>>开始创建第【{}】线程",i);
//            log.info("当前正在执行任务的线程数:{}",taskExecutor.getActiveCount());
//            log.info("核心线程数:{}",taskExecutor.getCorePoolSize());
//            log.info("最大线程数:{}",taskExecutor.getMaxPoolSize());
//            log.info("当前线程存在的总线程数:{}",taskExecutor.getPoolSize());
//            log.info("线程队列处理长度:{}",taskExecutor.getThreadPoolExecutor().getQueue().size());

            ThreadProcessTask bean = (ThreadProcessTask) autowireCapableBeanFactory.createBean(ThreadProcessTask.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
            bean.setCurrentThreadCount(i);
            taskExecutor.execute(bean);
            log.info(">>>>>>>>>>第【{}】线程创建完成",i);
            log.info("");
        }
        long completedTaskCount = taskExecutor.getThreadPoolExecutor().getCompletedTaskCount();
        log.info("已经执行完成的任务数:{}",completedTaskCount);
        log.info("有活动的线程数:{}",taskExecutor.getActiveCount());


        //如果线程执行shutdown之后，后续线程不会再次执行任务，如果线程池的话，需要考虑是否调用shutdown
        //shutdownNow：后续线程不会再执行任务
        //shutdown：
        log.info("执行线程shutdown");
        taskExecutor.getThreadPoolExecutor().shutdownNow();
        return "执行完毕";
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
