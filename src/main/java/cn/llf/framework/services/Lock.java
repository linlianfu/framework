package cn.llf.framework.services;

import cn.eleven.common.exception.BasicRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author eleven
 * @date 2018/10/13
 * @description  zookeeper分布式锁
 */
@Slf4j
@Service
public class Lock implements InitializingBean{


    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3,2);

    private CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181",2000,2000, retryPolicy);

    @Override
    public void afterPropertiesSet() throws Exception {

        client.start();
        log.info(">>>>>启动分布式锁的zk创建");
    }

    /**
     * 获取指定节点的锁
     * @return
     */
    public InterProcessMutex getInterProcessMutex(String node){

        if (StringUtils.isBlank(node))
            throw new BasicRuntimeException("锁节点不能为空");

        return new InterProcessMutex(client, "/curator/lock"+"/"+node);
    }


}
