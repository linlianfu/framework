package cn.llf.mq;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 创建者：   linlf
 * 创建时间： 2017/12/29
 * 描述：
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/mq.xml")
public class Producer {


    @Test
    public void testLog(){
        log.info("2342222222");
//        System.out.println("123213");
    }

    @Test
    public void producerTest(){

        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("192.168.3.19:9876");
        try {
            producer.start();
            for (int i = 0 ; i< 3 ; i++){
                Message msg = new Message("PushTopic",
                        "push",
                         i+"",
                        "Just for test.".getBytes());

                SendResult result = producer.send(msg);
                System.out.println("id:" + result.getMsgId() +
                        " result:" + result.getSendStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            producer.shutdown();
        }
    }

}
