package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author eleven
 * @date 2019/3/23
 * @description 对zookeeper的封装客户端ZkClient学习
 *
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:common.xml")
public class ZkClientDemo {


    private  ZooKeeper zooKeeper;
    private  String zkTestPath = "/zk-test";
    private  String path = "/zk-test-auth";
    private  String pathSon = "/zk-test-auth/test";
    private  CountDownLatch countDownLatch = new CountDownLatch(1);
    private  String connectString = "127.0.0.1";
    private  int sessionTimeout = 5000;
    private  String auth = "foo:true";
    private  String scheme = "digest";
    private ZkClient client = null;



    @Before
    public void initClient(){
        client = new ZkClient(connectString,5000);
        log.info("客户端会话建立成功");
    }

    /**
     * 基本API操作
     */

    @Test
    public void basicOperation(){
        //湖区子节点
        List<String> children = client.getChildren(zkTestPath);
        log.info(">>>>>【{}】子节点【{}】",zkTestPath,children);

        String path = client.create("/test-zk-client-create", "init", CreateMode.PERSISTENT);
        log.info("创建成功的节点间:【{}】",path);

        client.createPersistent("/parent/son/money",true);
        log.info(">>>>>递归创建节点");

        Object o = client.readData("/test-zk-client-create");
        log.info("读取到的节点数据:{}",o.toString());
    }

    /**
     * 获取子节点并监听子节点变化
     */
    @Test
    public void getChildrenAndSubscribeChildChanges(){
        log.info("测试获取子节点同时并绑定监听");
        List<String> children = client.getChildren(zkTestPath);
        log.info("【{}】的子节点列表",children);

        CountDownLatch countDownLatch = new CountDownLatch(2);
        client.subscribeChildChanges(zkTestPath,(parentPath,currentChildList)->{
            //currentChildList:当前的子节点列表
            log.info("收到节点【{}】的子节点变化通知，当前子节点:【{}】",parentPath,currentChildList);
            countDownLatch.countDown();
        });

        client.createPersistent(zkTestPath+"/clientCreate2","i'm data");
        log.info("创建子节点【{}】成功","clientCreate2");



        client.delete(zkTestPath+"/son2");
        log.info("成功删除子节点【son2】");



        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
