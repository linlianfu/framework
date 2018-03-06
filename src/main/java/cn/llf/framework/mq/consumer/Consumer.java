package cn.llf.framework.mq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建者：   linlf
 * 创建时间： 2017/12/29
 * 描述：
 */
@Slf4j
@Service("consumer")
public class Consumer {

    Consumer(){
        this.receiveMessage();
    }


    public boolean receiveMessage(){
        log.info(">>>>>启动消息消费监听。。。。。");
        boolean receiveSuccess = false;

        DefaultMQPushConsumer consumer =
                new DefaultMQPushConsumer("PushConsumer");
        consumer.setNamesrvAddr("192.168.3.19:9876");
        try {
            //订阅PushTopic下Tag为push的消息
            consumer.subscribe("PushTopic", "push");
//            //程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(
                    ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.registerMessageListener(
                    new MessageListenerConcurrently() {
                        public ConsumeConcurrentlyStatus consumeMessage(
                                List<MessageExt> list,
                                ConsumeConcurrentlyContext Context) {
                            log.info(">>>>>成功接收消息，开始消费。。。。。");
                            Message msg = list.get(0);
                            log.info("接收到的消息：【{}】",new String(msg.getBody()));
                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                        }
                    }
            );
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return receiveSuccess;
    }




//    public static void main(String[] args){
//        DefaultMQPushConsumer consumer =
//                new DefaultMQPushConsumer("PushConsumer");
//        consumer.setNamesrvAddr("192.168.25.242:9876");
//        try {
//            //订阅PushTopic下Tag为push的消息
//            consumer.subscribe("PushTopic", "push");
////            //程序第一次启动从消息队列头取数据
//            consumer.setConsumeFromWhere(
//                    ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
//            consumer.registerMessageListener(
//                    new MessageListenerConcurrently() {
//                        public ConsumeConcurrentlyStatus consumeMessage(
//                                List<MessageExt> list,
//                                ConsumeConcurrentlyContext Context) {
//                            Message msg = list.get(0);
//                            System.out.println(msg.toString());
//                            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//                        }
//                    }
//            );
//            consumer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
