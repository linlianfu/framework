package cn.llf.framework.mq.producer;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 创建者：   linlf
 * 创建时间： 2017/12/29
 * 描述：
 */
@Slf4j
@Service("producer")
public class Producer {

    Producer(){
//         produceMessage();
    }

    public boolean produceMessage(){
        log.info(">>>>>开始生产消息。。。。");
        boolean success = false;
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.25.242:9876");
        try {
            producer.start();

            for (int i = 0 ; i< 3 ; i++){
                Message msg = new Message("PushTopic",
                        "push",
                        i+"",
                        "Just for test.".getBytes());

                SendResult result = producer.send(msg);
                log.info("id【{}】,result【{}】，context【{}】",result.getMsgId(),result.getSendStatus(),new String(msg.getBody()));
            }
            log.info(">>>>>消息发送完毕.....");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            producer.shutdown();
        }

        return success;
    }

//    public static void main(String[] arg){
//
//        DefaultMQProducer producer = new DefaultMQProducer("Producer");
//        producer.setNamesrvAddr("192.168.25.242:9876");
//        try {
//            producer.start();
//
//            Message msg = new Message("PushTopic",
//                    "push",
//                    "1",
//                    "Just for test.".getBytes());
//
//            SendResult result = producer.send(msg);
//            System.out.println("id:" + result.getMsgId() +
//                    " result:" + result.getSendStatus());
//
//            msg = new Message("PushTopic",
//                    "push",
//                    "2",
//                    "Just for test.".getBytes());
//
//            result = producer.send(msg);
//            System.out.println("id:" + result.getMsgId() +
//                    " result:" + result.getSendStatus());
//
//            msg = new Message("PullTopic",
//                    "pull",
//                    "1",
//                    "Just for test.".getBytes());
//
//            result = producer.send(msg);
//            System.out.println("id:" + result.getMsgId() +
//                    " result:" + result.getSendStatus());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            producer.shutdown();
//        }
//    }

}
