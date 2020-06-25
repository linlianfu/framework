package cn.llf.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author eleven
 * @date 2020/6/25
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/redis.xml")
public class RedisDemo {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Test
    public void getAllKey(){
        BoundHashOperations<String, Object, Object> java = redisTemplate.boundHashOps("java");
        Set<Object> keys = java.keys();
        for (Object key : keys) {
            log.warn(">> key:{},value:{}",key,java.get(key));
        }
        Set<String> allKeySet = redisTemplate.keys("*");
        for (String key : allKeySet) {
            log.warn(">> key:{}",key);
        }
    }

    @Test
    public void expire(){
        String key = "expireKey";
        BoundValueOperations<String, String> valueOperations = redisTemplate.boundValueOps(key);
        valueOperations.set("expireValue");
        valueOperations.expire(5, TimeUnit.SECONDS);
        Boolean exist = redisTemplate.hasKey(key);
        log.warn(">>{}存在？：{}",key,exist);
        try {
            TimeUnit.SECONDS.sleep(6);
            exist = redisTemplate.hasKey(key);
            log.warn(">>{}存在？：{}",key,exist);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void queue(){
        BoundListOperations<String, String> queue = redisTemplate.boundListOps("queue");
        queue.leftPush("222");
        queue.rightPop();
        List<String> valueList = queue.range(0, -1);
        for (String s : valueList) {
            log.warn("item:{}",s);
        }
    }
}
