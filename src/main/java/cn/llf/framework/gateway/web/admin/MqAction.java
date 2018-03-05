package cn.llf.framework.gateway.web.admin;

import cn.llf.framework.mq.consumer.Consumer;
import cn.llf.framework.mq.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 创建者：   linlf
 * 创建时间： 2018/1/2
 * 描述：
 */
@Slf4j
@Controller
@RequestMapping("mqTest")
public class MqAction {

    @Autowired
    Producer producer;

    @Autowired
    Consumer consumer;
    /**
     * 测试消息生产
     * @return
     */
    @ResponseBody
    @RequestMapping("produceMessage")
    public boolean produceMessage(){

        return producer.produceMessage();
    }
    /**
     * 测试消息消费
     * @return
     */
    @ResponseBody
    @RequestMapping("consumerMessage")
    public boolean consumerMessage(){

        return consumer.receiveMessage();
    }




}
