package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2019/3/22
 * @description 测试java api 操作zookeeper
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


    public static void main(String[] arg){
        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse());
            try {
                countDownLatch.await();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            long sessionId = zooKeeper.getSessionId();
            byte[] sessionPasswd = zooKeeper.getSessionPasswd();

            //测试错误的sessionId连接失败
            //监听时间输出：收到server的监听事件：WatchedEvent state:Expired type:None path:null
            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse(),1L,sessionPasswd);

            //测试正确的sessionId和sessionPasswd可以复用连接成功
            zooKeeper = new ZooKeeper(connectString,sessionTimeout,new ZookeeperUse(),sessionId,sessionPasswd);

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void process(WatchedEvent event) {
        log.info("收到server的监听事件：{}",event);
        if (event.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }


    //************************会话复用 start**************************************
}
