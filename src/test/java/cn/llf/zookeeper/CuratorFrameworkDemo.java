package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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


    @Test
    public void masterSelect(){

        log.info("测试master选举，当leader产生之后，则其他的线程队列变成follower，等待leader放弃领导权，这时候，其他线程队列依次选举为master");
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
                        TimeUnit.SECONDS.sleep(3);
                        log.info("【{}】任务执行结束",name);
                    }
                });
                leaderSelector.autoRequeue();
                leaderSelector.start();
            });
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
