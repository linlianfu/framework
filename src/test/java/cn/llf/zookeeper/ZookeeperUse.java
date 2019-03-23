package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
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

//    private static ZooKeeper zooKeeper = null;
//
//    private static String parentPath = "/zk-test";
//
//    public static void main(String[] arg){
//
//        try {
//            zooKeeper = getZooKeeper();
//
//
//            countDownLatch.await();
//
//
//            //获取子节点信息，并同时注册该节点的监听
//            List<String> children = zooKeeper.getChildren(parentPath, true);
//            log.info("节点【{}】下的子节点列表【{}】",parentPath,children);
//
//
//
//            //创建节点
////            String testPathParent = zooKeeper.create(parentPath+"temp", "test-data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//
//
//
//            //延时1S，完成监听注册在继续走流程
//            TimeUnit.SECONDS.sleep(1);
//
//            //创建子节点,此时会触发节点监听通知机制
//            String testPathSon = zooKeeper.create(parentPath+"/son", "test-data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            //第二此创建节点，因为第一次监听到节点创建之后，有重新绑定监听，所以第二次创建节点已然可以监听到
//             testPathSon = zooKeeper.create(parentPath+"/son2", "test-data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//
//            //延时2S，等待收到节点变化通知之后再结束流程
//            TimeUnit.SECONDS.sleep(2);
//
//
//        } catch (InterruptedException | KeeperException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Override
//    public void process(WatchedEvent event) {
//        log.info("收到server监听通知:{}",event);
//        if (Event.KeeperState.SyncConnected == event.getState()){
//            if (event.getType() == Event.EventType.NodeChildrenChanged){
//                try {
//                    //收到通知之后，重新获取监听节点的数据，同时重新绑定监听事件，所以可以持续监听节点的子节点变化
//                    log.info(">>>>>收到子节点变化通知,尝试重新获取节点信息:【{}】",zooKeeper.getChildren(event.getPath(),true));
//                } catch (KeeperException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }else if (event.getType() == Event.EventType.None){
//                log.info("收到zookeeper创建连接监听通知");
//                countDownLatch.countDown();
//            }
//
//        }
//    }

    //************************获取节点，并测试监听机制end**************************************



    //************************获取节点数据并更新数据，同时绑定监听机制 start**************************************

    //异步实现

    private static ZooKeeper zooKeeper = null;
    private static String path = "/zk-test";
//    public static void main(String[] arg){
//
//        try {
//            Stat stat = new Stat();
//            zooKeeper = getZooKeeper();
//            countDownLatch.await();
//
//            //该方法为异步通知机制，也可以适用同步机制
//            //异步机制，当注册监听器之后，默认收到一个数据变化通知
//            zooKeeper.getData(path,true,new DataCallBackImpl(),"主线程发起获取数据");
//
//            TimeUnit.SECONDS.sleep(2);
//            log.info(">>>>>开始变更数据");
//            stat = zooKeeper.setData(path, "66666666666666".getBytes(), -1);
//            stat = zooKeeper.setData(path, "77777777777777777".getBytes(), -1);
//            log.info(">>>>>二次变成完成");
////            log.info("数据变成完成");
////            log.info(">>>>>数据变更之后返回的元信息:{}",stat);
//            TimeUnit.SECONDS.sleep(3);
//
//
//            log.info("==========================");
////            log.info(">>>>>数据二次变更");
////            stat = zooKeeper.setData(path, "data change".getBytes(), -1);
////            log.info(">>>>>数据二次变更完成");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        catch (KeeperException e) {
//            e.printStackTrace();
//        }
//
//    }
//    @Override
//    public void process(WatchedEvent event) {
////        log.info("收到server监听事件:{}",event);
//        if (event.getState() == Event.KeeperState.SyncConnected){
//            if (Event.EventType.None == event.getType()){
////                log.info(">>>>>收到zk节点创建通知");
//            }else if(Event.EventType.NodeDataChanged == event.getType()){
//                log.info(">>>>>主线程收到节点数据变化通知，重新获取数据并绑定监听事件");
//                zooKeeper.getData(event.getPath(),true,new DataCallBackImpl(),"from 主线程回调发起");
//            }
//            countDownLatch.countDown();
//        }
//    }



    //同步实现
//
    public static void main(String arg[]){
        try {
            String path = "/zk-test";
            Stat stat = new Stat();
            zooKeeper = getZooKeeper();
            byte[] data = zooKeeper.getData(path, true, stat);
            log.info("数据变更之前获取到的数据:{},",new String(data));
            log.info("数据变更之前获取到的元数据:czxid:【{}】,mzxid:【{}】,version:【{}】,",stat.getCzxid(),stat.getMzxid(),stat.getVersion());

            stat = zooKeeper.setData(path, "automic change1".getBytes(), 5);
            log.info("数据变更之后获取到的元数据:czxid:【{}】,mzxid:【{}】,version:【{}】,",stat.getCzxid(),stat.getMzxid(),stat.getVersion());
            stat = zooKeeper.setData(path, "automic change2".getBytes(), 5);

            //必须设置此延时函数，否则主线程结束之后，要是回调函数还没通知，则接收不到通知
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected){
            if (event.getType() == Event.EventType.None){
                log.info("client连接成功通知");
            }else if(event.getType() == Event.EventType.NodeDataChanged){
                try {
                    Stat stat = new Stat();
                    log.info("【回调函数】节点数据变化通知,手动获取新数据{}",new String(zooKeeper.getData(path,true,stat)));
                    log.info("【回调函数】节点数据变化通知,最新元数据:czxid:【{}】,mzxid:【{}】,version:【{}】,",stat.getCzxid(),stat.getMzxid(),stat.getVersion());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //************************获取节点数据并更新数据，同时绑定监听机制 end**************************************








    /**
     * 通用的实现类
//     * @param event
     */

//    @Override
//    public void process(WatchedEvent event) {
////        log.info("收到server监听事件:{}",event);
//        if (event.getState() == Event.KeeperState.SyncConnected){
//            if (Event.EventType.None == event.getType()){
////                log.info(">>>>>收到zk节点创建通知");
//            }
//            countDownLatch.countDown();
//        }
//    }





    public static ZooKeeper getZooKeeper(){
        try {
            zooKeeper =  new ZooKeeper(connectString,sessionTimeout,new ZookeeperAuth());
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
