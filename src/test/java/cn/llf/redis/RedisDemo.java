package cn.llf.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

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
            log.warn(">> key:{}",key);
        }
        Set<String> allKeySet = redisTemplate.keys("*");
        for (String key : allKeySet) {
            log.warn(">> key:{}",key);
        }

    }
}
