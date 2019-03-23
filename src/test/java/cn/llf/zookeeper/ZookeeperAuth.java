package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2019/3/23
 * @description
 */
@Slf4j
public class ZookeeperAuth implements Watcher{




    private static ZooKeeper zooKeeper;
    private static String path = "/zk-test-auth";
    private static String pathSon = "/zk-test-auth/test";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "127.0.0.1";
    private static  int sessionTimeout = 5000;
    private static String auth = "foo:true";
    private static String scheme = "digest";

    /**
     * 测试zk数据权限
     * @param arg
     */

//    public static void main(String[] arg){
//
//        try {
//            zooKeeper = new getAuthZooKeeper(connectString, sessionTimeout, null);
//            zooKeeper.addAuthInfo("digest",auth.getBytes());
//            String path = zooKeeper.create(ZookeeperAuth.path, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 测试获取由待权限控制的节点数据
     * @param arg
     */
//    public static void main(String[] arg){
//        try {
//            zooKeeper = getAuthZooKeeper();
//            zooKeeper.addAuthInfo("digest","".getBytes());
//            byte[] data = zooKeeper.getData(path, false, new Stat());
//            log.info("获取到的受权限控制的节点数据:{}",new String(data));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 测试删除带权限的节点
     * @param arg
     */
    public static void main(String[] arg){

        zooKeeper = getAuthZooKeeper();
        try {
//            zooKeeper.delete(path,-1);

//            String result = zooKeeper.create(path, "create".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
//            result = zooKeeper.create(pathSon, "create".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);


            zooKeeper = getooKeeper();
            zooKeeper.delete(path,-1);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void process(WatchedEvent event) {
        log.info(">>>>>收到监听回调");
        countDownLatch.countDown();
    }


    private static ZooKeeper getAuthZooKeeper(){
        try {
            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperAuth());
            zooKeeper.addAuthInfo(scheme,auth.getBytes());
            countDownLatch.await();
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ZooKeeper getooKeeper(){
        try {
            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperAuth());
            countDownLatch.await();
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
