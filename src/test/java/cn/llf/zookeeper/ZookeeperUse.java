package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2019/3/22
 * @description 测试java api 操作zookeeper
 *
 * 对应笔记参考:
 *      1:<zookeeper系统学习笔记>
 *      1:<zookeeper API操作及watch监听机制>
 */
@Slf4j
public class ZookeeperUse implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "127.0.0.1";
    private static  int sessionTimeout = 5000;


    //************************基本的会话建立了的demo start**************************************
//    public static void main(String[] arg) throws IOException {
//        /**
//         * connectString：可以指定访问的目录，叫做Chroot，如127.0.0.1:2181/cn，则客户端对zookeeper的所有操作都是基于该目录
//         * watcher：传入自己本身，接受zookeeper服务端的会话建立成功的通知
//         *
//         *
//         * client和server的会话建立是一个异步的过程，即：该构造方法调用之后并没有真正的建立好一个可用的会话
//         * 在会话的生命周期处于一个CONNECT状态{@link Event}
//         *
//         * 当会话建立真正成功之后，zookeeper服务端会回想会话对应的客户端发送一个时间通知，这时候才是真正的建立了会话
//         *
//         */
//        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,new ZookeeperUse());
//        log.info("连接状态：{}",zooKeeper.getState());
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info("zookeeper session会话建立");
//    }
//
//
//    @Override
//    public void process(WatchedEvent event) {
//        log.info("收到server的监听事件：{}",event);
//        log.info("释放主程序");
//        countDownLatch.countDown();
//    }

    //************************基本的会话建立了的demo end**************************************




    //************************会话复用 start**************************************


//    public static void main(String[] arg){
//        try {
//            ZooKeeper zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse());
//            try {
//                countDownLatch.await();
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//            long sessionId = zooKeeper.getSessionId();
//            byte[] sessionPasswd = zooKeeper.getSessionPasswd();
//
//            //测试错误的sessionId连接失败
//            //监听时间输出：收到server的监听事件：WatchedEvent state:Expired type:None path:null
//            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse(),1L,sessionPasswd);
//
//            //测试正确的sessionId和sessionPasswd可以复用连接成功
//            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse(),sessionId,sessionPasswd);
//
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Override
//    public void process(WatchedEvent event) {
//        log.info("收到server的监听事件：{}",event);
//        if (event.getState() == Event.KeeperState.SyncConnected){
//            countDownLatch.countDown();
//        }
//    }


    //************************会话复用 end**************************************




    //************************适用api 创建节点start**************************************


//    public static void main(String[] arg){
//        try {
//            ZooKeeper zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse());
//            countDownLatch.await();
//
//            //返回创建成功的path
//            String path = zooKeeper.create("/zk-test-", "测试api创建节点数据".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//            log.info("成功创建临时节点znode:{}",path);
//
//            //创建临时有序的节点，server会默认加上一个数字床
//            String temp = zooKeeper.create("/zk-test-", "测试api创建节点数据".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
//            log.info("成功创建临时有序的节点znode:{}",temp);
//
//
//            //通过延时，可以查看zk节点上是否这边指定的节点，因为是临时节点，一旦client断开连接，节点将自动删除
//            TimeUnit.SECONDS.sleep(10);
//
//
//            //上述是同步创建方案，zookeeper还提供了异步方法，提供一个回调接口即可
//            /**
//             * {@link ZooKeeper#create(java.lang.String, byte[], java.util.List, org.apache.zookeeper.CreateMode, org.apache.zookeeper.AsyncCallback.StringCallback, java.lang.Object) }
//             **/
//
//
//
//            //测试创建永久节点
//            String persistentPath = zooKeeper.create("/test-delete", "im data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            log.info(">>>>>成功创建永久节点:{}",persistentPath);
//        } catch (IOException | InterruptedException | KeeperException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void process(WatchedEvent event) {
//        log.info("收到server监听时间:{}",event);
//        if (event.getState() == Event.KeeperState.SyncConnected){
//            countDownLatch.countDown();
//        }
//    }


    //************************适用api 创建节点end**************************************

    //************************删除节点start**************************************



//    public static void main(String[] arg){
//        ZooKeeper zooKeeper = null;
//        try {
//            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse());
//            //数据版本
//            zooKeeper.delete("/test-delete",0);
//        } catch (IOException | InterruptedException | KeeperException e) {
//            e.printStackTrace();
//        }
//    }



    //************************删除节点end**************************************



    //************************获取节点，并测试监听机制start**************************************

    private static ZooKeeper zooKeeper = null;

    private static String parentPath = "/zk-test";

    public static void main(String[] arg){

        try {
            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse());


            countDownLatch.await();
            //创建节点
            String testPathParent = zooKeeper.create(parentPath, "test-data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //获取子节点信息，并同时注册该节点的监听
            List<String> children = zooKeeper.getChildren(parentPath, new ZookeeperUse());
            log.info("节点【{}】下的子节点列表【{}】",parentPath,children);

            //延时1S，完成监听注册在继续走流程
            TimeUnit.SECONDS.sleep(1);

            //创建子节点,此时会触发节点监听通知机制
            String testPathSon = zooKeeper.create(parentPath+"/son", "test-data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            //延时2S，等待收到节点变化通知之后再结束流程
            TimeUnit.SECONDS.sleep(2);


        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void process(WatchedEvent event) {
        log.info("收到server监听通知:{}",event);
        if (Event.KeeperState.SyncConnected == event.getState()){
            if (event.getType() == Event.EventType.NodeChildrenChanged){
                try {
                    log.info(">>>>>收到子节点变化通知,尝试重新获取节点信息:【{}】",zooKeeper.getChildren(event.getPath(),true));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                log.info("收到zookeeper创建连接监听通知");
                countDownLatch.countDown();
            }

        }
    }

    //************************获取节点，并测试监听机制end**************************************








    /**
     * 通用的实现类
     * @param event
     */

//    @Override
//    public void process(WatchedEvent event) {
//        log.info("收到server监听时间:{}",event);
//        if (event.getState() == Event.KeeperState.SyncConnected){
//            countDownLatch.countDown();
//        }
//    }
}
