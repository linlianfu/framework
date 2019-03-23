package cn.llf.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * @author eleven
 * @date 2019/3/23
 * @description 获取zk节点绑定的异步回调类，获取子节点绑定也可以类似异步回调
 */
@Slf4j
public class DataCallBackImpl implements AsyncCallback.DataCallback {


    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        log.info(">>>>>【异步任务】收到数据节点变化通知，rc:【{}】,上下文参数对象:【{}】,变化的节点path:【{}】,data:【{}】",rc,ctx.toString(),path,new String(data));
        log.info(">>>>>【异步任务】的事务id czxid:【{}】,现在最大事务id mzxid:【{}】.数据version:【{}】",stat.getCzxid(),stat.getMzxid(),stat.getVersion());
    }
}
