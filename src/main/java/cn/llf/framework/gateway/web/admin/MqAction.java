package cn.llf.framework.gateway.web.admin;

import cn.eleven.basic.data.rocketmq.client.consumer.ConsumerFactory;
import cn.eleven.basic.data.rocketmq.client.dto.MQMessage;
import cn.eleven.basic.data.rocketmq.client.producer.ProducerFactory;
import com.alibaba.rocketmq.client.producer.SendStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.llf.ability.course.south.dto.CourseDto;

import java.util.UUID;

/**
 * 创建者：   linlf
 * 创建时间： 2018/1/2
 * 描述：
 */
@Slf4j
@RestController
@RequestMapping("mqTest")
public class MqAction {

    @Autowired
    ProducerFactory producerFactory;

    @Autowired
    ConsumerFactory consumer;
    /**
     * 测试消息生产
     * @return
     */
    @GetMapping("produceMessage")
    public SendStatus produceMessage(){

        CourseDto courseDto = new CourseDto();
        courseDto.setName("mq学习课程");
        courseDto.setAbouts("2018.09.04学习");
        courseDto.setId(UUID.randomUUID().toString());
        MQMessage message =new MQMessage(courseDto.toString());
        message.setFrom("平台");
        message.setTo("基础数据服务收");
        return producerFactory.sendMessage(message);
    }


}
