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

import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2019/3/24
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
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curatorFramework")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        client.start();
        LeaderSelector leaderSelector = new LeaderSelector(client, masterPath, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                log.info("开始执行leader任务");
                TimeUnit.SECONDS.sleep(3);
                log.info("任务执行结束");
            }
        });
//        leaderSelector.autoRequeue();
        leaderSelector.start();
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
