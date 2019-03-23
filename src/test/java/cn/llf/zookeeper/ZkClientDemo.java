package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author eleven
 * @date 2019/3/23
 * @description 对zookeeper的封装客户端ZkClient学习
 *
 */
@Slf4j
public class ZkClientDemo {


    private static ZooKeeper zooKeeper;
    private static String zkTestPath = "/zk-test";
    private static String path = "/zk-test-auth";
    private static String pathSon = "/zk-test-auth/test";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "127.0.0.1";
    private static  int sessionTimeout = 5000;
    private static String auth = "foo:true";
    private static String scheme = "digest";



    public static void main(String[] arg){
        //会话建立
        ZkClient client = new ZkClient(connectString,5000);
        log.info(">>>>>会话建立");
        //湖区子节点
//        List<String> children = client.getChildren(zkTestPath);
//        log.info(">>>>>【{}】子节点【{}】",zkTestPath,children);

//        String path = client.create("/test-zk-client-create", "init", CreateMode.PERSISTENT);
//        log.info("创建成功的节点间:【{}】",path);

//        client.createPersistent("/parent/son/money",true);
//        log.info(">>>>>递归创建节点");

        Object o = client.readData("/test-zk-client-create");
        log.info("读取到的节点数据:{}",o.toString());
    }
}
