package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.*;

/**
 * @author eleven
 * @date 2019/3/
 * @description
 *
 *   master轮流选举待研究文章：https://blog.csdn.net/qq_34021712/article/details/82880062
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class CuratorFrameworkDemo {

    private static String connectString = "127.0.0.1";
    private static String masterPath = "/curator_master_path";


    /**
     * 待解决问题：如何实现集群中的一个机器执行完之后，整个任务就算完成，不用再去轮询其他机器
     *
     * 选举实现的大概流程：
     *
     * 1：当多台机器同时像zookeeper执行创建同一个path的节点时候，利用zookeeper的特性，只有一台会创建成功；
     *      原因：当节点已经存在的时候，如果再创建，则zookeeper会抛出NodeExistsException(具体异常待再次确定)，创建失败
     * 2：创建成功的机器就是作为master
     * 3：该代码中主要创建了一个LeaderSelector实例，该实例封装了所有master选举的逻辑，包括和所有zookeeper服务器的交互；
     * 4：LeaderSelector实例还要求传一个回调函数，当，master选举成功之后，Curator会回调该函数，实现需要的业务逻辑
     * 5：当一旦执行完回调函数{@link LeaderSelectorListener#takeLeadership(org.apache.curator.framework.CuratorFramework)},
     *    则当前机器将释放master权力，然后开始新一轮的master选举
     * 6：如果当前机器需要参加下一次的master选举，则需要调用{@link LeaderSelector#autoRequeue}，该机器才会参加下一此的master选举
     * 7：当master选举完成之后，其他所有机器都会进入等待队列，等待下一次的master选举
     *
     */
    @Test
    public void masterSelect(){

        log.info("测试master选举，当leader产生之后，则其他的线程队列变成follower，等待leader放弃领导权，这时候，其他线程队列依次选举为master");

        CountDownLatch countDownLatch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0;i <3;i++ ){
            String name = "线程:"+i;
            executorService.execute(() -> {
                CuratorFramework client = CuratorFrameworkFactory.builder()
                        .connectString(connectString)
                        .namespace("curatorFramework")
                        .retryPolicy(new ExponentialBackoffRetry(1000,3))
                        .build();
                client.start();
                LeaderSelector leaderSelector = new LeaderSelector(client, masterPath, new LeaderSelectorListenerAdapter() {
                    @Override
                    public void takeLeadership(CuratorFramework client) throws Exception {
                        log.info("【{}】开始执行leader任务",name);
//                        TimeUnit.SECONDS.sleep(3);
                        log.info("【{}】任务执行结束",name);
                        countDownLatch.countDown();
                    }
                });
                leaderSelector.autoRequeue();
                leaderSelector.start();
            });
        }

//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //通过CountDownLatch实现只要有一个机器执行了任务，就结束整个流程，不在等待其他机器再次执行任务，因为任务只要执行一次，不用重复执行
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("程序执行完成");

    }


    /**
     * 单机环境下，利用JDK自带的{@link CountDownLatch}和{@link CyclicBarrier}是俺多线程同步
     */
    @Test
    public void CyclicBarrierDemo(){

        int threadCount = 5;

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        CyclicBarrier barrier = new CyclicBarrier(threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0 ; i < threadCount ; i++ ) {

            int currentThreadNumber = i;
            executorService.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(currentThreadNumber);
                    log.info("线程【{}】准备好了",currentThreadNumber);
                    //等待threadCount个线程都进入等待状态，才同步执行接下去的代码
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.info("线程【{}】开始执行任务",currentThreadNumber);
                countDownLatch.countDown();
            });
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("【{}】个线程都执行完毕，结束主线程",threadCount);

    }
}
