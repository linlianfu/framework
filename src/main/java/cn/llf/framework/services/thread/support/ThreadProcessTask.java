package cn.llf.framework.services.thread.support;

import cn.llf.framework.model.mybatis.UserInfoPO;
import cn.llf.framework.services.user.south.IUserManagerService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author eleven
 * @date 2019/3/18
 * @description
 * 一个cpu执行双线程，本机电脑4核cpu，最多可执行8个线程
 *
 * 线程池的4中拒绝策略：
 * ExecutorConfigurationSupport
 * {@link java.util.concurrent.ThreadPoolExecutor.AbortPolicy}:抛出java.util.concurrent.RejectedExecutionException异常
 * {@link java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy}:让调用者线程执行被拒绝的任务，如果执行程序已关闭，则会丢弃该任务
 * {@link java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy}:该策略将丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务
 *      为什么丢弃最老的队列？ 是因为队列的先进先出原则，所以队列的头元素肯定是最老的一个请求，而DiscardOldestPolicy策略就是把最老的请求废除，把这个机会（相当于抢了最老的请求的吃到嘴边的肉）留给当前新提交到线程池里面的线程（这个时候线程池已经满了，队列也已经满了，如果不满的话，也不会有拒绝策略了！））
 * {@link java.util.concurrent.ThreadPoolExecutor.DiscardPolicy}:用于被拒绝任务的处理程序，默认情况下它将丢弃被拒绝的任务。
 *
 */
@Slf4j
public class ThreadProcessTask implements Runnable {


    @Setter
    private int currentThreadCount;
    @Autowired
    private IUserManagerService userManagerService;

    @Override
    public void run() {

        log.info(">>>>>线程[{}]线程号【{}】开始执行任务",Thread.currentThread().getName(),currentThreadCount);
        UserInfoPO memory = userManagerService.memory("11111");
        log.info("调用用户服务返回结果:{}",memory);
//        try {
////            TimeUnit.SECONDS.sleep(10);
//
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int i = 0;
        while (true){
            i++;
            if (i >=100)
                break;
//            log.info(">>>>>线程【{}】统计中",Thread.currentThread().getName());
        }
        log.info(">>>>>线程[{}]执行结束",Thread.currentThread().getName());
    }
}
