package cn.llf.framework.gateway.web.admin;

import cn.eleven.basic.data.rocketmq.client.consumer.DefaultMQConsumer;
import cn.eleven.basic.data.rocketmq.client.dto.MQMessage;
import cn.eleven.basic.data.rocketmq.client.producer.ProducerFactory;
import com.alibaba.rocketmq.client.producer.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import priv.llf.ability.course.south.dto.CourseDto;

import java.util.UUID;

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
    ProducerFactory producerFactory;

    @Autowired
    DefaultMQConsumer consumer;
    /**
     * 测试消息生产
     * @return
     */
    @ResponseBody
    @RequestMapping("produceMessage")
    public SendStatus produceMessage(){

        CourseDto courseDto = new CourseDto();
        courseDto.setName("mq学习课程");
        courseDto.setAbouts("2018.09.04学习");
        courseDto.setId(UUID.randomUUID().toString());
        MQMessage message =new MQMessage(courseDto.toString());
        return producerFactory.sendMessage(message);
    }


}
